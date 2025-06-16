package ru.outofmemoryguru.calculator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "loan.offer")
@Data
public class LoanOfferProperties {
    private BigDecimal baseRate;
    private BigDecimal insuranceDiscount;
    private BigDecimal salaryClientDiscount;
    private BigDecimal insuranceCost;
}
