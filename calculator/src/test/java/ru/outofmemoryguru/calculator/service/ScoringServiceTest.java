package ru.outofmemoryguru.calculator.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.outofmemoryguru.calculator.service.to.LoanOfferServiceModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.outofmemoryguru.calculator.testdata.ServiceModelTestData.expected4OffersModel;
import static ru.outofmemoryguru.calculator.testdata.ServiceModelTestData.loanStatementServiceModel1;

@SpringBootTest
public class ScoringServiceTest {

    @Autowired
    private ScoringService scoringService;

    @Test
    public void preScoringLoanOffers() {
        List<LoanOfferServiceModel> actualOffers = scoringService.preScoringLoanOffers(loanStatementServiceModel1);

        for (int i = 0; i < expected4OffersModel.size(); i++) {
            LoanOfferServiceModel expected = expected4OffersModel.get(i);
            LoanOfferServiceModel actual = actualOffers.get(i);

            assertEquals(expected.getRequestedAmount(), actual.getRequestedAmount());
            assertEquals(expected.getTotalAmount(), actual.getTotalAmount());
            assertEquals(expected.getTerm(), actual.getTerm());
            assertEquals(expected.getMonthlyPayment(), actual.getMonthlyPayment());
            assertEquals(expected.getRate(), actual.getRate());
            assertEquals(expected.isInsuranceEnabled(), actual.isInsuranceEnabled());
            assertEquals(expected.isSalaryClient(), actual.isSalaryClient());
        }
    }
}
