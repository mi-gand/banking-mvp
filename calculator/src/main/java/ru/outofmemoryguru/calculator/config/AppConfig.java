package ru.outofmemoryguru.calculator.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LoanOfferProperties.class)
public class AppConfig {
}
