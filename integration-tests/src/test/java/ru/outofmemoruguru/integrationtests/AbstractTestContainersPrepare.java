package ru.outofmemoruguru.integrationtests;

import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.jupiter.api.Disabled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.sql.Connection;
import java.sql.SQLException;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Disabled
public abstract class AbstractTestContainersPrepare {

    private static final Logger log = LoggerFactory.getLogger(AbstractTestContainersPrepare.class);
    protected static final long START_TIME = System.currentTimeMillis();

    private static final Network dockerNet = Network.newNetwork();

    @Container
    protected PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    @Container
    protected GenericContainer<?> calculatorContainer = new GenericContainer<>("ms-calculator:test");

    @Container
    protected GenericContainer<?> dealContainer = new GenericContainer<>("ms-deal:test");

    @Container
    protected GenericContainer<?> statementContainer = new GenericContainer<>("ms-statement:test");


    {
        //todo в будущем заодно сделать проверку на запущенный процесс докер десктопа
        try {
            String os = System.getProperty("os.name").toLowerCase();
            boolean isOsWindows = os.contains("win");

            ProcessBuilder checkDocker;


            if (isOsWindows) {
                checkDocker = new ProcessBuilder("C:\\Program Files\\Git\\bin\\bash.exe"
                        , "start-docker-service.sh").inheritIO();

                Process pCheck = checkDocker.start();
                int codeCheck = pCheck.waitFor();
                if (codeCheck != 0) {
                    throw new RuntimeException("check-docker-service.sh ошибка: " + codeCheck);
                }
            }


            ProcessBuilder buildImages;
            if (isOsWindows) {
                buildImages = new ProcessBuilder("cmd.exe", "/c", "build-docker-calculator-deal-statement-test.bat");
            } else {
                buildImages = new ProcessBuilder("bash", "build-docker-calculator-deal-statement-test.sh");
            }
            buildImages.inheritIO();
            Process process = buildImages.start();
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Докеры MS-DEAL/MS-CALCULATOR/MS-STATEMENT не собрались: " + exitCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("Докеры MS-DEAL/MS-CALCULATOR/MS-STATEMENT не собрались: ", e);
        }

        postgres.withDatabaseName("test")
                .withUsername("test")
                .withPassword("test")
                .withNetwork(dockerNet)
                .withNetworkAliases("postgres")
                .withInitScript("configure-postgres-to-real-db.sql");

        postgres.start();

        try {
            Thread.sleep(2000);         //успевает подняться postgres без логики ожидания
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.debug("Запущен контейнер postgres. {}", getTimeFromStart());

        initAndPopulateDb();

        calculatorContainer
                //.withExposedPorts(8080)           //подключить для прямого доступа к МС с помощью getMappedPort
                .withNetwork(dockerNet)
                .withNetworkAliases("calculator")
                .start();

        log.debug("Запущен контейнер calculator. " + getTimeFromStart());

        dealContainer
                //.withExposedPorts(8080)           //подключить для прямого доступа к МС с помощью getMappedPort
                .withNetwork(dockerNet)
                .dependsOn(postgres, calculatorContainer)
                //.withEnv("DATASOURCE_URL", postgres.getJdbcUrl())
                .withEnv("DATASOURCE_URL", "jdbc:postgresql://postgres:5432/test")
                .withEnv("DATASOURCE_USERNAME", postgres.getUsername())
                .withEnv("DATASOURCE_PASSWORD", postgres.getPassword())
                .withEnv("CALCULATOR_BASE_URL", "http://calculator:8080")
                .start();

        log.debug("Запущен контейнер deal. {}", getTimeFromStart());

        statementContainer
                .withExposedPorts(8080)
                .withNetwork(dockerNet)
                .withNetworkAliases("statement")
                .dependsOn(dealContainer)
                .withEnv("DATASOURCE_URL", "jdbc:postgresql://postgres:5432/test")
                .withEnv("DATASOURCE_USERNAME", postgres.getUsername())
                .withEnv("DATASOURCE_PASSWORD", postgres.getPassword())
                .start();
        //.withEnv("DEAL_BASE_URL", "http://deal:8080");

        log.debug("Запущен контейнер statement. {}", getTimeFromStart());
    }

    protected static String getTimeFromStart() {
        return "С запуска прошло" + (START_TIME - System.currentTimeMillis());
    }

    protected void initAndPopulateDb() {
        try (Connection conn = postgres.createConnection("")) {
            Liquibase liquibase = new Liquibase(
                    "test-db-changelog.xml",
                    new ClassLoaderResourceAccessor(),
                    new JdbcConnection(conn)
            );
            liquibase.update("");
        } catch (SQLException | LiquibaseException e) {
            throw new RuntimeException("Ошибка с Liquibase", e);
        }
    }

}
