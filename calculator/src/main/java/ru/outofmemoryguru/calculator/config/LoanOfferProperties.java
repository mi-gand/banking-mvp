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

    public BigDecimal getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(BigDecimal baseRate) {
        this.baseRate = baseRate;
    }

    public BigDecimal getInsuranceDiscount() {
        return insuranceDiscount;
    }

    public void setInsuranceDiscount(BigDecimal insuranceDiscount) {
        this.insuranceDiscount = insuranceDiscount;
    }

    public BigDecimal getSalaryClientDiscount() {
        return salaryClientDiscount;
    }

    public void setSalaryClientDiscount(BigDecimal salaryClientDiscount) {
        this.salaryClientDiscount = salaryClientDiscount;
    }

    public BigDecimal getInsuranceCost() {
        return insuranceCost;
    }

    public void setInsuranceCost(BigDecimal insuranceCost) {
        this.insuranceCost = insuranceCost;
    }
}
