package ru.outofmemoryguru.statement.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {
    private final DealProperties dealProperties;

    @Bean
    public RestClient restClient(RestClient.Builder builder) {

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        JdkClientHttpRequestFactory requestFactory =
                new JdkClientHttpRequestFactory(httpClient);


        return builder
                .requestFactory(requestFactory)
                .baseUrl(dealProperties.getBaseUrl())
                .build();
    }
}
