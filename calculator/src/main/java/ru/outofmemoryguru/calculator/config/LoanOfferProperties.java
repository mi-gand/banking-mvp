package ru.outofmemoryguru.calculator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "loan.offer")
@Getter
@Setter
public class LoanOfferProperties {
    private BigDecimal baseRate;
    private BigDecimal insuranceDiscount;
    private BigDecimal salaryClientDiscount;
    private BigDecimal insuranceCost;
}
