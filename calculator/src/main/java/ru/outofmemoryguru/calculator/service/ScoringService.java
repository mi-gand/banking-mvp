package ru.outofmemoryguru.calculator.service;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.outofmemoryguru.calculator.config.LoanOfferProperties;
import ru.outofmemoryguru.calculator.dto.LoanOfferDto;
import ru.outofmemoryguru.calculator.dto.LoanStatementRequestDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@EnableConfigurationProperties(LoanOfferProperties.class)
public class ScoringService {

    private final LoanOfferProperties loanOfferProperties;
    private static final Integer MONTHES_IN_YEAR = 12;

    public ScoringService(LoanOfferProperties loanOfferProperties) {
        this.loanOfferProperties = loanOfferProperties;
    }

    public List<LoanOfferDto> generateLoanOffers(LoanStatementRequestDto dtoRequest) {
        List<LoanOfferDto> responceList = new ArrayList<>();

        BigDecimal baseRate = loanOfferProperties.getBaseRate();
        BigDecimal insuranceDiscount = loanOfferProperties.getInsuranceDiscount();
        BigDecimal salaryClientDiscount = loanOfferProperties.getSalaryClientDiscount();
        BigDecimal insuranceCost = loanOfferProperties.getInsuranceCost();

        BigDecimal finalRate;

        for (boolean isInsuranceEnabled : List.of(false, true)) {
            finalRate = isInsuranceEnabled ? baseRate.subtract(insuranceDiscount) : baseRate;
            for (boolean isSalaryClient : List.of(false, true)) {
                finalRate = isSalaryClient ? finalRate.subtract(salaryClientDiscount) : finalRate;

                LoanOfferDto loanOfferDto = createLoanOfferDto(
                        dtoRequest.getAmount(),
                        dtoRequest.getTerm(),
                        finalRate.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP),
                        isInsuranceEnabled,
                        isSalaryClient,
                        insuranceCost
                );
                responceList.add(loanOfferDto);
            }
        }
        return responceList;
    }


    //логика расчета не та
    private BigDecimal calculateTotalAmount(BigDecimal amount,
                                            BigDecimal finalRate,
                                            Integer term) {
        if (term == 0) {
            return amount;
        } else {
            BigDecimal tmpAmount = amount.multiply(BigDecimal.ONE.add(finalRate));
            return calculateTotalAmount(tmpAmount, finalRate, --term);
        }
    }

    private LoanOfferDto createLoanOfferDto(BigDecimal amount,
                                            Integer term,
                                            BigDecimal rate,
                                            boolean isInsuranceEnabled,
                                            boolean isSalaryClient,
                                            BigDecimal insuranceAmount) {

        BigDecimal totalAmount = isInsuranceEnabled ?
                calculateTotalAmount(amount.add(insuranceAmount), rate, term) :
                calculateTotalAmount(amount, rate, term);

        BigDecimal monthlyPayment = totalAmount.divide(BigDecimal.valueOf(term),
                2, RoundingMode.HALF_UP);

        LoanOfferDto loanOfferDto = new LoanOfferDto();

        loanOfferDto.setRequestedAmount(amount);
        loanOfferDto.setTotalAmount(totalAmount);
        loanOfferDto.setTerm(term);
        loanOfferDto.setMonthlyPayment(monthlyPayment);
        loanOfferDto.setRate(rate);
        loanOfferDto.setIsInsuranceEnabled(isInsuranceEnabled);
        loanOfferDto.setIsSalaryClient(isSalaryClient);

        return loanOfferDto;
    }
}
