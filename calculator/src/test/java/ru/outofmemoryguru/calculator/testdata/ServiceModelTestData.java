package ru.outofmemoryguru.calculator.testdata;

import ru.outofmemoryguru.calculator.controller.dto.EmploymentDto;
import ru.outofmemoryguru.calculator.model.EmploymentStatus;
import ru.outofmemoryguru.calculator.model.Gender;
import ru.outofmemoryguru.calculator.model.MaritalStatus;
import ru.outofmemoryguru.calculator.model.Position;
import ru.outofmemoryguru.calculator.service.to.LoanOfferServiceModel;
import ru.outofmemoryguru.calculator.service.to.LoanStatementServiceModel;
import ru.outofmemoryguru.calculator.service.to.ScoringDataServiceModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ServiceModelTestData {

    public static final LoanStatementServiceModel loanStatementServiceModel1 = new LoanStatementServiceModel();

    static {
        loanStatementServiceModel1.setAmount(new BigDecimal("500000"));
        loanStatementServiceModel1.setTerm(12);
        loanStatementServiceModel1.setFirstName("Ivan");
        loanStatementServiceModel1.setLastName("Ivanov");
        loanStatementServiceModel1.setMiddleName("Ivanovich");
        loanStatementServiceModel1.setEmail("ivan@ya.ru");
        loanStatementServiceModel1.setBirthdate(LocalDate.of(2000, 1, 1));
        loanStatementServiceModel1.setPassportSeries("1234");
        loanStatementServiceModel1.setPassportNumber("567890");
    }

    public static final ScoringDataServiceModel scoringDataServiceModel1 = new ScoringDataServiceModel();

    static {
        scoringDataServiceModel1.setAmount(new BigDecimal("500000"));
        scoringDataServiceModel1.setTerm(12);
        scoringDataServiceModel1.setFirstName("John");
        scoringDataServiceModel1.setLastName("Doe");
        scoringDataServiceModel1.setMiddleName("Michael");
        scoringDataServiceModel1.setGender(Gender.MALE);
        scoringDataServiceModel1.setBirthdate(LocalDate.of(1990, 6, 14));
        scoringDataServiceModel1.setPassportSeries("1234");
        scoringDataServiceModel1.setPassportNumber("567890");
        scoringDataServiceModel1.setPassportIssueDate(LocalDate.of(2010, 5, 1));
        scoringDataServiceModel1.setPassportIssueBranch("Some passport office");
        scoringDataServiceModel1.setMaritalStatus(MaritalStatus.MARRIED);
        scoringDataServiceModel1.setDependentAmount(2);
        scoringDataServiceModel1.setAccountNumber("1234567890123456");
        scoringDataServiceModel1.setInsuranceEnabled(true);
        scoringDataServiceModel1.setSalaryClient(false);

        EmploymentDto employment = new EmploymentDto();
        employment.setEmploymentStatus(EmploymentStatus.BUSINESS_OWNER);
        employment.setEmployerINN("123456789012");
        employment.setSalary(new BigDecimal("120000"));
        employment.setPosition(Position.WORKER);
        employment.setWorkExperienceTotal(100);
        employment.setWorkExperienceCurrent(20);

        scoringDataServiceModel1.setEmployment(employment);
    }

    public static final LoanOfferServiceModel loanOffer1 = new LoanOfferServiceModel();
    public static final LoanOfferServiceModel loanOffer2 = new LoanOfferServiceModel();
    public static final LoanOfferServiceModel loanOffer3 = new LoanOfferServiceModel();
    public static final LoanOfferServiceModel loanOffer4 = new LoanOfferServiceModel();

    public static final List<LoanOfferServiceModel> expected4OffersModel = List.of(
            loanOffer1, loanOffer2, loanOffer3, loanOffer4
    );

    static {
        loanOffer1.setStatementId(UUID.fromString("47663b09-69a4-4380-a3e3-7d23d3cf2017"));
        loanOffer1.setRequestedAmount(new BigDecimal("500000"));
        loanOffer1.setTotalAmount(new BigDecimal("584922.76"));
        loanOffer1.setTerm(12);
        loanOffer1.setMonthlyPayment(new BigDecimal("48743.56"));
        loanOffer1.setRate(new BigDecimal("30.0"));
        loanOffer1.setInsuranceEnabled(false);
        loanOffer1.setSalaryClient(false);

        loanOffer2.setStatementId(UUID.fromString("c2a6dc33-e39a-4715-886b-396b890399f7"));
        loanOffer2.setRequestedAmount(new BigDecimal("500000"));
        loanOffer2.setTotalAmount(new BigDecimal("581975.39"));
        loanOffer2.setTerm(12);
        loanOffer2.setMonthlyPayment(new BigDecimal("48497.95"));
        loanOffer2.setRate(new BigDecimal("29.0"));
        loanOffer2.setInsuranceEnabled(false);
        loanOffer2.setSalaryClient(true);

        loanOffer3.setStatementId(UUID.fromString("76973493-5a1d-40de-a0a6-ab1aa9352b45"));
        loanOffer3.setRequestedAmount(new BigDecimal("500000"));
        loanOffer3.setTotalAmount(new BigDecimal("676104.37"));
        loanOffer3.setTerm(12);
        loanOffer3.setMonthlyPayment(new BigDecimal("56342.03"));
        loanOffer3.setRate(new BigDecimal("27.0"));
        loanOffer3.setInsuranceEnabled(true);
        loanOffer3.setSalaryClient(false);

        loanOffer4.setStatementId(UUID.fromString("e425d27f-b3cc-4ae1-b09c-6708d6ec0056"));
        loanOffer4.setRequestedAmount(new BigDecimal("500000"));
        loanOffer4.setTotalAmount(new BigDecimal("673180.79"));
        loanOffer4.setTerm(12);
        loanOffer4.setMonthlyPayment(new BigDecimal("56098.40"));
        loanOffer4.setRate(new BigDecimal("26.0"));
        loanOffer4.setInsuranceEnabled(true);
        loanOffer4.setSalaryClient(true);
    }
}
