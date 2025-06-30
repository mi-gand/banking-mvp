package ru.outofmemoryguru.deal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
    private final CalculatorProperties calculatorProperties;

    public RestClientConfig(CalculatorProperties calculatorProperties) {
        this.calculatorProperties = calculatorProperties;
    }
    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder
                .baseUrl(calculatorProperties.getBaseUrl())
                .build();
    }
}
