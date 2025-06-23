package ru.outofmemoryguru.deal.model.jsonb;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class AppliedOffer {     //todo аналог LoanOffer из MS-calculator
    private UUID statementId;
    private BigDecimal requestedAmount;
    private BigDecimal totalAmount;
    private Integer term;
    private BigDecimal monthlyPayment;
    private BigDecimal rate;

    private boolean insuranceEnabled;
    private boolean salaryClient;
}
