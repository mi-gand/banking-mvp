package ru.outofmemoryguru.calculator.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static ru.outofmemoryguru.calculator.testdata.ServiceModelTestData.loanStatementServiceModel1;

@SpringBootTest
@RequiredArgsConstructor
public class ScoringServiceTest {

    private final ScoringService scoringService;

    @Test
    public void testGenerateLoanOffers(){
        scoringService.preScoringLoanOffers(loanStatementServiceModel1);
    }

/*    @Test
    void testPreScoringLoanOffers_returnsFourCombinations() {
        when(loanStatementServiceModel1.getBaseRate()).thenReturn(BigDecimal.valueOf(10));
        when(loanStatementServiceModel1.getInsuranceDiscount()).thenReturn(BigDecimal.valueOf(1));
        when(loanStatementServiceModel1.getSalaryClientDiscount()).thenReturn(BigDecimal.valueOf(2));
        when(loanStatementServiceModel1.getInsuranceCost()).thenReturn(BigDecimal.valueOf(6000));

        List<LoanOfferServiceModel> offers = scoringService.preScoringLoanOffers(loanStatement);

        assertEquals(4, offers.size());
        assertTrue(offers.stream().anyMatch(o -> o.isInsuranceEnabled() && o.isSalaryClient()));
    }*/

}
