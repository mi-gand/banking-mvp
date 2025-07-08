package ru.outofmemoryguru.calculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.outofmemoryguru.calculator.config.LoanOfferProperties;
import ru.outofmemoryguru.calculator.service.to.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScoringService {

    private final LoanOfferProperties loanOfferProperties;

    public List<LoanOfferServiceModel> preScoringLoanOffers(LoanStatementServiceModel modelService) {
        List<LoanOfferServiceModel> responceList = new ArrayList<>();

        BigDecimal baseRate = loanOfferProperties.getBaseRate();
        BigDecimal insuranceDiscount = loanOfferProperties.getInsuranceDiscount();
        BigDecimal salaryClientDiscount = loanOfferProperties.getSalaryClientDiscount();
        BigDecimal insuranceCost = loanOfferProperties.getInsuranceCost();

        BigDecimal finalRate;

        for (boolean isInsuranceEnabled : List.of(false, true)) {
            finalRate = isInsuranceEnabled ? baseRate.subtract(insuranceDiscount) : baseRate;
            for (boolean isSalaryClient : List.of(false, true)) {
                finalRate = isSalaryClient ? finalRate.subtract(salaryClientDiscount) : finalRate;

                LoanOfferServiceModel loanOfferModelService = createLoanOfferServiceModel(
                        modelService.getAmount(),
                        modelService.getTerm(),
                        finalRate,
                        isInsuranceEnabled,
                        isSalaryClient,
                        insuranceCost
                );
                responceList.add(loanOfferModelService);
            }
        }
        return responceList;
    }

    private LoanOfferServiceModel createLoanOfferServiceModel(BigDecimal amount,
                                                              Integer term,
                                                              BigDecimal rate,
                                                              boolean isInsuranceEnabled,
                                                              boolean isSalaryClient,
                                                              BigDecimal insuranceAmount) {

        BigDecimal monthlyRate = rate.divide(BigDecimal.valueOf(12 * 100), 10, RoundingMode.HALF_UP);
        BigDecimal monthlyPayment = calculateAnnuityPayment(amount, monthlyRate, term);

        if (isInsuranceEnabled) {
            BigDecimal insuranceMonthly = insuranceAmount.divide(BigDecimal.valueOf(term), 2, RoundingMode.HALF_UP);
            monthlyPayment = monthlyPayment.add(insuranceMonthly);
        }

        BigDecimal totalAmount = monthlyPayment.multiply(BigDecimal
                .valueOf(term));

        LoanOfferServiceModel loanOfferModelService = new LoanOfferServiceModel();

        loanOfferModelService.setStatementId(UUID.randomUUID());
        loanOfferModelService.setRequestedAmount(amount);
        loanOfferModelService.setTotalAmount(totalAmount.setScale(2, RoundingMode.HALF_UP));
        loanOfferModelService.setTerm(term);
        loanOfferModelService.setMonthlyPayment(monthlyPayment.setScale(2, RoundingMode.HALF_UP));
        loanOfferModelService.setRate(rate);
        loanOfferModelService.setInsuranceEnabled(isInsuranceEnabled);
        loanOfferModelService.setSalaryClient(isSalaryClient);

        return loanOfferModelService;
    }

    public CreditServiceModel calculateCredit(ScoringDataServiceModel model) {
        BigDecimal rate = calculateCreditRate(model);

        BigDecimal monthlyRate = rate.divide(BigDecimal.valueOf(12 * 100), 10, RoundingMode.HALF_UP);
        int termMonths = model.getTerm();
        BigDecimal amount = model.getAmount();

        BigDecimal monthlyPayment = calculateAnnuityPayment(amount, monthlyRate, termMonths);
        List<PaymentScheduleElementServiceModel> schedule =
                buildPaymentSchedule(amount, monthlyRate, monthlyPayment, termMonths);

        BigDecimal totalPayments = monthlyPayment.multiply(BigDecimal.valueOf(termMonths));
        BigDecimal totalOverpayment = totalPayments.subtract(amount);

        BigDecimal psk = totalOverpayment
                .divide(amount, 10, RoundingMode.HALF_UP)
                .divide(BigDecimal.valueOf(model.getTerm()), 10, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_UP);

        CreditServiceModel result = new CreditServiceModel();
        result.setAmount(amount);
        result.setTerm(model.getTerm());
        result.setMonthlyPayment(monthlyPayment.setScale(2, RoundingMode.HALF_UP));
        result.setRate(rate);
        result.setPsk(psk);
        result.setInsuranceEnabled(model.isInsuranceEnabled());
        result.setSalaryClient(model.isSalaryClient());
        result.setPaymentSchedule(schedule);

        return result;
    }

    //взял готовый расчет аннуитентного платежа на яве
    private BigDecimal calculateAnnuityPayment(BigDecimal principal, BigDecimal monthlyRate, int months) {
        BigDecimal onePlusRatePow = BigDecimal.ONE.add(monthlyRate).pow(months, MathContext.DECIMAL128);
        return principal.multiply(monthlyRate).multiply(onePlusRatePow)
                .divide(onePlusRatePow.subtract(BigDecimal.ONE), 10, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateCreditRate(ScoringDataServiceModel model) {
        BigDecimal baseRate = loanOfferProperties.getBaseRate();


        switch (model.getEmployment().getEmploymentStatus()) {
            case UNEMPLOYED -> throw new IllegalArgumentException("Denied: unemployed");
            case SELF_EMPLOYED -> baseRate = baseRate.add(BigDecimal.valueOf(2));
            case EMPLOYED -> baseRate = baseRate.add(BigDecimal.valueOf(1.5));
            case BUSINESS_OWNER -> baseRate = baseRate.add(BigDecimal.valueOf(1));
        }

        switch (model.getEmployment().getPosition()) {
            case MIDDLE_MANAGER -> baseRate = baseRate.subtract(BigDecimal.valueOf(1));
            case TOP_MANAGER -> baseRate = baseRate.subtract(BigDecimal.valueOf(2));
            case OWNER -> baseRate = baseRate.subtract(BigDecimal.valueOf(3));
        }

        switch (model.getMaritalStatus()) {
            case MARRIED -> baseRate = baseRate.subtract(BigDecimal.valueOf(3));
            case DIVORCED -> baseRate = baseRate.add(BigDecimal.valueOf(1));
        }

        BigDecimal maxSum = model.getEmployment().getSalary().multiply(BigDecimal.valueOf(24));
        if (model.getAmount().compareTo(maxSum) > 0) {
            throw new IllegalArgumentException("Denied: loan amount is more than 24 salaries");
        }

        int age = Period.between(model.getBirthdate(), LocalDate.now()).getYears();
        if (age < 18 || age > 60) {
            throw new IllegalArgumentException("Denied: Age outside the acceptable range of 18-60 years");
        }

        switch (model.getGender()) {
            case FEMALE -> {
                if (age > 32 && age < 60) {
                    baseRate = baseRate.subtract(BigDecimal.valueOf(3));
                }
            }
            case MALE -> {
                if (age > 30 && age < 55) {
                    baseRate = baseRate.subtract(BigDecimal.valueOf(3));
                }
            }
        }

        if (model.getEmployment().getWorkExperienceTotal() < 18) {
            throw new IllegalArgumentException("Denied: total work experience lower 18 months");
        }

        if (model.getEmployment().getWorkExperienceCurrent() < 3) {
            throw new IllegalArgumentException("Denied: current work experience lower 3 months");
        }

        if (model.isSalaryClient()) {
            baseRate = baseRate.subtract(BigDecimal.valueOf(2));
        }
        return baseRate.setScale(2, RoundingMode.HALF_UP);
    }

    public List<PaymentScheduleElementServiceModel> buildPaymentSchedule(BigDecimal amount,
                                                                         BigDecimal monthlyRate,
                                                                         BigDecimal monthlyPayment,
                                                                         int termMonths) {
        List<PaymentScheduleElementServiceModel> schedule = new ArrayList<>();
        BigDecimal remainingDebt = amount;
        LocalDate paymentDate = LocalDate.now();

        for (int i = 1; i <= termMonths; i++) {
            PaymentScheduleElementServiceModel element = new PaymentScheduleElementServiceModel();
            element.setNumber(i);
            element.setDate(paymentDate.plusMonths(i));

            BigDecimal interestPayment = remainingDebt.multiply(monthlyRate)
                    .setScale(2, RoundingMode.HALF_UP);

            BigDecimal debtPayment = monthlyPayment.subtract(interestPayment)
                    .setScale(2, RoundingMode.HALF_UP);
            if (i == termMonths) {
                debtPayment = remainingDebt;
                monthlyPayment = debtPayment.add(interestPayment);
            }

            remainingDebt = remainingDebt.subtract(debtPayment)
                    .setScale(2, RoundingMode.HALF_UP);

            element.setTotalPayment(monthlyPayment);
            element.setInterestPayment(interestPayment);
            element.setDebtPayment(debtPayment);
            element.setRemainingDebt(remainingDebt.compareTo(BigDecimal.ZERO) < 0
                    ? BigDecimal.ZERO
                    : remainingDebt);

            schedule.add(element);
        }

        return schedule;
    }

}