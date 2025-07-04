package ru.outofmemoryguru.deal.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "calculator")
@Getter
@Setter
public class CalculatorProperties {
    private String baseUrl;

}
