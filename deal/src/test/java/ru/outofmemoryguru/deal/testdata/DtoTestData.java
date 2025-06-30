package ru.outofmemoryguru.deal.testdata;

import ru.outofmemoryguru.deal.controller.dto.LoanOfferDto;

import java.math.BigDecimal;
import java.util.UUID;

public class DtoTestData {
    public static LoanOfferDto loanOfferDto1 = new LoanOfferDto();

    static {
        loanOfferDto1.setStatementId(UUID.fromString("56d14d61-b584-4715-ad42-2cdb0fc71b45"));
        loanOfferDto1.setRequestedAmount(new BigDecimal("500000"));
        loanOfferDto1.setTotalAmount(new BigDecimal("673180.79"));
        loanOfferDto1.setTerm(12);
        loanOfferDto1.setMonthlyPayment(new BigDecimal("56098.40"));
        loanOfferDto1.setRate(new BigDecimal("26.0"));
        loanOfferDto1.setInsuranceEnabled(true);
        loanOfferDto1.setSalaryClient(true);
    }
}
