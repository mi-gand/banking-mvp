package ru.outofmemoryguru.deal.testdata;

import ru.outofmemoryguru.deal.service.to.LoanOfferServiceModel;
import ru.outofmemoryguru.deal.service.to.LoanStatementServiceModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ServiceModelTestData {
    public static final LoanOfferServiceModel LoanOfferServiceModelFromStatementAa11 = new LoanOfferServiceModel();

    static {
        LoanOfferServiceModelFromStatementAa11.setStatementId(UUID.fromString("aa111111-1111-1111-1111-111111111111"));
        LoanOfferServiceModelFromStatementAa11.setRequestedAmount(new BigDecimal("500000"));
        LoanOfferServiceModelFromStatementAa11.setTotalAmount(new BigDecimal("673180.79"));
        LoanOfferServiceModelFromStatementAa11.setTerm(12);
        LoanOfferServiceModelFromStatementAa11.setMonthlyPayment(new BigDecimal("56098.40"));
        LoanOfferServiceModelFromStatementAa11.setRate(new BigDecimal("26.0"));
        LoanOfferServiceModelFromStatementAa11.setInsuranceEnabled(true);
        LoanOfferServiceModelFromStatementAa11.setSalaryClient(true);
    }

    public static final LoanStatementServiceModel loanStatementServiceModelFromStatementAa11 = new LoanStatementServiceModel();

    static{
        loanStatementServiceModelFromStatementAa11.setAmount(new BigDecimal("500000"));
        loanStatementServiceModelFromStatementAa11.setTerm(12);
        loanStatementServiceModelFromStatementAa11.setFirstName("Ivan");
        loanStatementServiceModelFromStatementAa11.setLastName("Ivanov");
        loanStatementServiceModelFromStatementAa11.setMiddleName("Ivanovich");
        loanStatementServiceModelFromStatementAa11.setEmail("ivan@ya.ru");
        loanStatementServiceModelFromStatementAa11.setBirthdate(LocalDate.parse("1985-07-15"));
        loanStatementServiceModelFromStatementAa11.setPassportSeries("1234");
        loanStatementServiceModelFromStatementAa11.setPassportNumber("567890");
    }

    public static final LoanOfferServiceModel loanOffer1 = new LoanOfferServiceModel();
    public static final LoanOfferServiceModel loanOffer2 = new LoanOfferServiceModel();
    public static final LoanOfferServiceModel loanOffer3 = new LoanOfferServiceModel();
    public static final LoanOfferServiceModel loanOffer4 = new LoanOfferServiceModel();

    public static final List<LoanOfferServiceModel> expected4OffersModel = List.of(
            loanOffer1, loanOffer2, loanOffer3, loanOffer4
    );

    static {
        loanOffer1.setStatementId(UUID.fromString("aa111111-1111-1111-1111-111111111111"));
        loanOffer1.setRequestedAmount(new BigDecimal("500000"));
        loanOffer1.setTotalAmount(new BigDecimal("584922.76"));
        loanOffer1.setTerm(12);
        loanOffer1.setMonthlyPayment(new BigDecimal("48743.56"));
        loanOffer1.setRate(new BigDecimal("30.0"));
        loanOffer1.setInsuranceEnabled(false);
        loanOffer1.setSalaryClient(false);

        loanOffer2.setStatementId(UUID.fromString("aa111111-1111-1111-1111-111111111111"));
        loanOffer2.setRequestedAmount(new BigDecimal("500000"));
        loanOffer2.setTotalAmount(new BigDecimal("581975.39"));
        loanOffer2.setTerm(12);
        loanOffer2.setMonthlyPayment(new BigDecimal("48497.95"));
        loanOffer2.setRate(new BigDecimal("29.0"));
        loanOffer2.setInsuranceEnabled(false);
        loanOffer2.setSalaryClient(true);

        loanOffer3.setStatementId(UUID.fromString("aa111111-1111-1111-1111-111111111111"));
        loanOffer3.setRequestedAmount(new BigDecimal("500000"));
        loanOffer3.setTotalAmount(new BigDecimal("676104.37"));
        loanOffer3.setTerm(12);
        loanOffer3.setMonthlyPayment(new BigDecimal("56342.03"));
        loanOffer3.setRate(new BigDecimal("27.0"));
        loanOffer3.setInsuranceEnabled(true);
        loanOffer3.setSalaryClient(false);

        loanOffer4.setStatementId(UUID.fromString("aa111111-1111-1111-1111-111111111111"));
        loanOffer4.setRequestedAmount(new BigDecimal("500000"));
        loanOffer4.setTotalAmount(new BigDecimal("673180.79"));
        loanOffer4.setTerm(12);
        loanOffer4.setMonthlyPayment(new BigDecimal("56098.40"));
        loanOffer4.setRate(new BigDecimal("26.0"));
        loanOffer4.setInsuranceEnabled(true);
        loanOffer4.setSalaryClient(true);
    }


}
