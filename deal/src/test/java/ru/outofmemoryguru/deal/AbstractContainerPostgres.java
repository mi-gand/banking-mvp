package ru.outofmemoryguru.deal;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

//todo разобраться. Если более 1 наследника, то при запуске всех тестов они игнорятся
//по одному запускаются
@SpringBootTest
@Testcontainers
@Sql(statements = "CREATE SCHEMA IF NOT EXISTS deal", executionPhase = BEFORE_TEST_CLASS)
@Sql(scripts = "classpath:schema.sql", executionPhase = BEFORE_TEST_CLASS)
@ActiveProfiles("test")
public abstract class AbstractContainerPostgres {

/*    protected WireMockServer wireMockServer;*/

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configure(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.jpa.properties.hibernate.default_schema", () -> "deal");
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

/*    static {
        postgres.start();
    }*/

/*    @BeforeEach
    void setup() {
        wireMockServer.stubFor(
                post(urlEqualTo("/calculator/offers"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{ \"some\": \"response\" }")
                        )
        );

        wireMockServer.stubFor(
                post(urlEqualTo("/calculator/calc"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{ \"some\": \"response\" }")
                        )
        );
    }*/
}
