package ru.outofmemoryguru.deal.service;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

@Testcontainers
@Sql(statements = "CREATE SCHEMA IF NOT EXISTS deal", executionPhase = BEFORE_TEST_CLASS)
@Sql(scripts = "classpath:schema.sql", executionPhase = BEFORE_TEST_CLASS)
@ActiveProfiles("test")
public class AbstractContainerPostgres {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configure(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.jpa.properties.hibernate.default_schema", () -> "deal");
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }



    static {
        postgres.start();
    }
}
