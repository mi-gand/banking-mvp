package ru.outofmemoryguru.calculator.service.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreditServiceModel {
    private BigDecimal amount;
    private Integer term;
    private BigDecimal monthlyPayment;
    private BigDecimal rate;
    private BigDecimal psk;
    private boolean insuranceEnabled;
    private boolean salaryClient;
    private List<PaymentScheduleElementServiceModel> paymentSchedule;
}
