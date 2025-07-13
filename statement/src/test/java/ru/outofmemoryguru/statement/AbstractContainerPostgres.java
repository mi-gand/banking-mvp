package ru.outofmemoryguru.statement;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Disabled;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

@Testcontainers
@Sql(statements = "CREATE SCHEMA IF NOT EXISTS deal", executionPhase = BEFORE_TEST_CLASS)
@Sql(scripts = "classpath:schema.sql", executionPhase = BEFORE_TEST_CLASS)
@ActiveProfiles("test")
@Disabled
public abstract class AbstractContainerPostgres {

    protected static WireMockServer wireMockServer;

    @AfterAll
    static void afterAll() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    @Container
    static GenericContainer<?> calculatorContainer = new GenericContainer<>("ms-calculator:latest")
            .withExposedPorts(8080)
            .withEnv("SPRING_PROFILES_ACTIVE", "test");


    @Container
    static GenericContainer<?> dealContainer = new GenericContainer<>("ms-deal:latest")
            .withExposedPorts(8080)
            .withEnv("SPRING_PROFILES_ACTIVE", "test");


    @DynamicPropertySource
    static void configure(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.jpa.properties.hibernate.default_schema", () -> "deal");
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("deal.base-url", () -> "http://localhost:" + dealContainer.getMappedPort(8080));
        dealContainer.withEnv("DATASOURCE_URL", postgres.getJdbcUrl())
                .withEnv("DATASOURCE_USERNAME", postgres.getUsername())
                .withEnv("DATASOURCE_PASSWORD", postgres.getPassword())
                .withEnv("CALCULATOR_BASE_URL",
                "http://" + calculatorContainer.getHost() + ":" + calculatorContainer.getMappedPort(8080));
    }

    static {
        //проверку на запущенный сервис докера сюда не вставляю, предполагаю, что он запущен
        try {
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb;

            if (os.contains("win")) {
                pb = new ProcessBuilder("cmd.exe", "/c", "build-docker-calculator-deal-test.bat");
            } else {
                pb = new ProcessBuilder("bash", "build-docker-calculator-deal-test.sh");
            }


            pb.inheritIO();
            Process process = pb.start();
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Докер MS-DEAL не собрался: " + exitCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("Докер MS-DEAL не собрался: ", e);
        }

        wireMockServer = new WireMockServer(0);
        wireMockServer.start();

        postgres.start();           //todo проверить почему в прошлые разы пришлось поставить запуск, а не заработало автоматом
        try {
            Thread.sleep(5000);         //жду пока точно поднимется база, чтоб не вылетела ошибка подкл
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        calculatorContainer.start();
        dealContainer.start();
    }

/*    @BeforeAll
    static void beforeAll() {
        postgres.start();
        calculatorContainer.start();
        String calculatorUrl = "http://" + calculatorContainer.getHost() + ":" + calculatorContainer.getMappedPort(8080);
        dealContainer.withEnv("CALCULATOR_BASE_URL", calculatorUrl);
        dealContainer.start();
    }*/


}
