package ru.outofmemoryguru.statement.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {
    private final DealProperties dealProperties;

    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder
                .baseUrl(dealProperties.getBaseUrl())
                .build();
    }
}
