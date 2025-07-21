package ru.outofmemoryguru.statement.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "deal")
@Getter
@Setter
public class DealProperties {
    private String baseUrl;
}
