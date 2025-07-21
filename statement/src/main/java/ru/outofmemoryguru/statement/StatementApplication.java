package ru.outofmemoryguru.statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class StatementApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatementApplication.class, args);
    }
}
