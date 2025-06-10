package ru.outofmemoryguru.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import ru.outofmemoryguru.calculator.config.LoanOfferProperties;
import ru.outofmemoryguru.calculator.service.ScoringService;


import static ru.outofmemoryguru.calculator.DtoTestData.loanStatementRequestDto1;

@SpringBootTest
@EnableConfigurationProperties(LoanOfferProperties.class)
public class ScoringServiceTest {

    @Autowired
    ScoringService scoringService;

    @Test
    public void testGenerateLoanOffers(){
        scoringService.generateLoanOffers(loanStatementRequestDto1);
    }

}
