package ru.outofmemoryguru.calculator.testdata;

import ru.outofmemoryguru.calculator.controller.dto.*;
import ru.outofmemoryguru.calculator.model.EmploymentStatus;
import ru.outofmemoryguru.calculator.model.Gender;
import ru.outofmemoryguru.calculator.model.MaritalStatus;
import ru.outofmemoryguru.calculator.model.Position;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DtoTestData {

    public static final LoanStatementRequestDto loanStatementRequestDto1 = new LoanStatementRequestDto();

    static {
        loanStatementRequestDto1.setAmount(new BigDecimal("500000"));
        loanStatementRequestDto1.setTerm(12);
        loanStatementRequestDto1.setFirstName("Ivan");
        loanStatementRequestDto1.setLastName("Ivanov");
        loanStatementRequestDto1.setMiddleName("Ivanovich");
        loanStatementRequestDto1.setEmail("ivan@ya.ru");
        loanStatementRequestDto1.setBirthdate(LocalDate.of(1990, 1, 1));
        loanStatementRequestDto1.setPassportSeries("1234");
        loanStatementRequestDto1.setPassportNumber("567890");
    }


    public static final LoanStatementRequestDto loanStatementRequestDtoInvalid12Err = new LoanStatementRequestDto();

    static {
        loanStatementRequestDtoInvalid12Err.setAmount(new BigDecimal("20000001"));
        loanStatementRequestDtoInvalid12Err.setTerm(21);
        loanStatementRequestDtoInvalid12Err.setFirstName("QwertyQwertyQwertyQwertyQwerty1");
        loanStatementRequestDtoInvalid12Err.setLastName("QwertyQwertyQwertyQwertyQwerty2");
        loanStatementRequestDtoInvalid12Err.setMiddleName("QwertyQwertyQwertyQwertyQwerty3");
        loanStatementRequestDtoInvalid12Err.setEmail("wrongEmail");
        loanStatementRequestDtoInvalid12Err.setBirthdate(LocalDate.now().minusYears(5));
        loanStatementRequestDtoInvalid12Err.setPassportSeries("12345");
        loanStatementRequestDtoInvalid12Err.setPassportNumber("1234567");
    }

    public static final int EXPECTED_VALIDATION_ERR_12 = 12;

    public static final ScoringDataDto scoringDataDto1 = new ScoringDataDto();

    static {
        scoringDataDto1.setAmount(new BigDecimal("500000"));
        scoringDataDto1.setTerm(12);
        scoringDataDto1.setFirstName("John");
        scoringDataDto1.setLastName("Doe");
        scoringDataDto1.setMiddleName("Michael");
        scoringDataDto1.setGender(Gender.MALE);
        scoringDataDto1.setBirthdate(LocalDate.of(1990, 6, 14));
        scoringDataDto1.setPassportSeries("1234");
        scoringDataDto1.setPassportNumber("567890");
        scoringDataDto1.setPassportIssueDate(LocalDate.of(2010, 5, 1));
        scoringDataDto1.setPassportIssueBranch("Some passport office");
        scoringDataDto1.setMaritalStatus(MaritalStatus.MARRIED);
        scoringDataDto1.setDependentAmount(2);

        EmploymentDto employment = new EmploymentDto();
        employment.setEmploymentStatus(EmploymentStatus.BUSINESS_OWNER);
        employment.setEmployerINN("123456789012");
        employment.setSalary(new BigDecimal("120000"));
        employment.setPosition(Position.OWNER);
        employment.setWorkExperienceTotal(100);
        employment.setWorkExperienceCurrent(20);

        scoringDataDto1.setEmployment(employment);
        scoringDataDto1.setAccountNumber("1234567890123456");
        scoringDataDto1.setInsuranceEnabled(true);
        scoringDataDto1.setSalaryClient(false);
    }


    private static final LoanOfferModelService loanOfferDto1 = new LoanOfferModelService();
    private static final LoanOfferModelService loanOfferDto2 = new LoanOfferModelService();
    private static final LoanOfferModelService loanOfferDto3 = new LoanOfferModelService();
    private static final LoanOfferModelService loanOfferDto4 = new LoanOfferModelService();
    public static List<LoanOfferModelService> expectedOffers = List.of(loanOfferDto1,
            loanOfferDto2, loanOfferDto3, loanOfferDto4);

    static {
        loanOfferDto1.setStatementId(UUID.fromString("92b3beb9-03f0-4381-abcb-c1053ccf1b84"));
        loanOfferDto1.setRequestedAmount(new BigDecimal("500000"));
        loanOfferDto1.setTotalAmount(new BigDecimal("584922.76"));
        loanOfferDto1.setTerm(12);
        loanOfferDto1.setMonthlyPayment(new BigDecimal("48743.56"));
        loanOfferDto1.setRate(new BigDecimal("30.0"));
        loanOfferDto1.setInsuranceEnabled(false);
        loanOfferDto1.setSalaryClient(false);

        loanOfferDto2.setStatementId(UUID.fromString("f2d7893a-2f21-44e7-85ee-83f61891a2c3"));
        loanOfferDto2.setRequestedAmount(new BigDecimal("500000"));
        loanOfferDto2.setTotalAmount(new BigDecimal("581975.39"));
        loanOfferDto2.setTerm(12);
        loanOfferDto2.setMonthlyPayment(new BigDecimal("48497.95"));
        loanOfferDto2.setRate(new BigDecimal("29.0"));
        loanOfferDto2.setInsuranceEnabled(false);
        loanOfferDto2.setSalaryClient(true);

        loanOfferDto3.setStatementId(UUID.fromString("598603a6-5dc0-4524-99f4-6fbf12d7dd6d"));
        loanOfferDto3.setRequestedAmount(new BigDecimal("500000"));
        loanOfferDto3.setTotalAmount(new BigDecimal("676104.37"));
        loanOfferDto3.setTerm(12);
        loanOfferDto3.setMonthlyPayment(new BigDecimal("56342.03"));
        loanOfferDto3.setRate(new BigDecimal("27.0"));
        loanOfferDto3.setInsuranceEnabled(true);
        loanOfferDto3.setSalaryClient(false);

        loanOfferDto4.setStatementId(UUID.fromString("0e91bb60-4dd2-4df9-80ed-bce8396926a7"));
        loanOfferDto4.setRequestedAmount(new BigDecimal("500000"));
        loanOfferDto4.setTotalAmount(new BigDecimal("673180.79"));
        loanOfferDto4.setTerm(12);
        loanOfferDto4.setMonthlyPayment(new BigDecimal("56098.40"));
        loanOfferDto4.setRate(new BigDecimal("26.0"));
        loanOfferDto4.setInsuranceEnabled(true);
        loanOfferDto4.setSalaryClient(true);
    }

    public static final ScoringDataDto scoringDataDtoInvalid22Err = new ScoringDataDto();

    static {
        scoringDataDtoInvalid22Err.setAmount(new BigDecimal("20000001"));
        scoringDataDtoInvalid22Err.setTerm(21);
        scoringDataDtoInvalid22Err.setFirstName("QwertyQwertyQwertyQwertyQwerty123");
        scoringDataDtoInvalid22Err.setLastName("QwertyQwertyQwertyQwertyQwerty456");
        scoringDataDtoInvalid22Err.setMiddleName("QwertyQwertyQwertyQwertyQwerty789");
        scoringDataDtoInvalid22Err.setGender(null); // @NotNull

        scoringDataDtoInvalid22Err.setBirthdate(LocalDate.now().minusYears(10));
        scoringDataDtoInvalid22Err.setPassportSeries("12345");
        scoringDataDtoInvalid22Err.setPassportNumber("1234567");
        scoringDataDtoInvalid22Err.setPassportIssueDate(LocalDate.now().plusDays(5));
        scoringDataDtoInvalid22Err.setPassportIssueBranch("\"Very long passport issue Very long passport " +
                "issue Very long passport issue Very long passport issue");

        scoringDataDtoInvalid22Err.setMaritalStatus(null);
        scoringDataDtoInvalid22Err.setDependentAmount(31);

        EmploymentDto employment = new EmploymentDto();
        employment.setEmploymentStatus(null);
        employment.setEmployerINN("12345678901234567890");
        employment.setSalary(null);
        employment.setPosition(null);
        employment.setWorkExperienceTotal(-1);
        employment.setWorkExperienceCurrent(-5);
        scoringDataDtoInvalid22Err.setEmployment(employment);

        scoringDataDtoInvalid22Err.setAccountNumber("12345678901234567");
        scoringDataDtoInvalid22Err.setInsuranceEnabled(true);
        scoringDataDtoInvalid22Err.setSalaryClient(true);
    }

    public static final int EXPECTED_VALIDATION_ERR_23 = 23;

    public static final CreditDto expectedCreditDto = new CreditDto();

    static {
        expectedCreditDto.setAmount(new BigDecimal("500000"));
        expectedCreditDto.setTerm(12);
        expectedCreditDto.setMonthlyPayment(new BigDecimal("46797.19"));
        expectedCreditDto.setRate(new BigDecimal("22.00"));
        expectedCreditDto.setPsk(new BigDecimal("1.03"));
        expectedCreditDto.setInsuranceEnabled(true);
        expectedCreditDto.setSalaryClient(false);

        List<PaymentScheduleElementDto> schedule = new ArrayList<>();

        LocalDate now = LocalDate.now();
        int monthCounter = 0;

        PaymentScheduleElementDto p1 = new PaymentScheduleElementDto();
        p1.setNumber(1);
        p1.setDate(LocalDate.parse(now.plusMonths(++monthCounter).toString()));
        p1.setTotalPayment(new BigDecimal("46797.1898114174"));
        p1.setInterestPayment(new BigDecimal("9166.67"));
        p1.setDebtPayment(new BigDecimal("37630.52"));
        p1.setRemainingDebt(new BigDecimal("462369.48"));
        schedule.add(p1);

        PaymentScheduleElementDto p2 = new PaymentScheduleElementDto();
        p2.setNumber(2);
        p2.setDate(LocalDate.parse(now.plusMonths(++monthCounter).toString()));
        p2.setTotalPayment(new BigDecimal("46797.1898114174"));
        p2.setInterestPayment(new BigDecimal("8476.77"));
        p2.setDebtPayment(new BigDecimal("38320.42"));
        p2.setRemainingDebt(new BigDecimal("424049.06"));
        schedule.add(p2);

        PaymentScheduleElementDto p3 = new PaymentScheduleElementDto();
        p3.setNumber(3);
        p3.setDate(LocalDate.parse(now.plusMonths(++monthCounter).toString()));
        p3.setTotalPayment(new BigDecimal("46797.1898114174"));
        p3.setInterestPayment(new BigDecimal("7774.23"));
        p3.setDebtPayment(new BigDecimal("39022.96"));
        p3.setRemainingDebt(new BigDecimal("385026.10"));
        schedule.add(p3);

        PaymentScheduleElementDto p4 = new PaymentScheduleElementDto();
        p4.setNumber(4);
        p4.setDate(LocalDate.parse(now.plusMonths(++monthCounter).toString()));
        p4.setTotalPayment(new BigDecimal("46797.1898114174"));
        p4.setInterestPayment(new BigDecimal("7058.81"));
        p4.setDebtPayment(new BigDecimal("39738.38"));
        p4.setRemainingDebt(new BigDecimal("345287.72"));
        schedule.add(p4);

        PaymentScheduleElementDto p5 = new PaymentScheduleElementDto();
        p5.setNumber(5);
        p5.setDate(LocalDate.parse(now.plusMonths(++monthCounter).toString()));
        p5.setTotalPayment(new BigDecimal("46797.1898114174"));
        p5.setInterestPayment(new BigDecimal("6330.27"));
        p5.setDebtPayment(new BigDecimal("40466.92"));
        p5.setRemainingDebt(new BigDecimal("304820.80"));
        schedule.add(p5);

        PaymentScheduleElementDto p6 = new PaymentScheduleElementDto();
        p6.setNumber(6);
        p6.setDate(LocalDate.parse(now.plusMonths(++monthCounter).toString()));
        p6.setTotalPayment(new BigDecimal("46797.1898114174"));
        p6.setInterestPayment(new BigDecimal("5588.38"));
        p6.setDebtPayment(new BigDecimal("41208.81"));
        p6.setRemainingDebt(new BigDecimal("263611.99"));
        schedule.add(p6);

        PaymentScheduleElementDto p7 = new PaymentScheduleElementDto();
        p7.setNumber(7);
        p7.setDate(LocalDate.parse(now.plusMonths(++monthCounter).toString()));
        p7.setTotalPayment(new BigDecimal("46797.1898114174"));
        p7.setInterestPayment(new BigDecimal("4832.89"));
        p7.setDebtPayment(new BigDecimal("41964.30"));
        p7.setRemainingDebt(new BigDecimal("221647.69"));
        schedule.add(p7);

        PaymentScheduleElementDto p8 = new PaymentScheduleElementDto();
        p8.setNumber(8);
        p8.setDate(LocalDate.parse(now.plusMonths(++monthCounter).toString()));
        p8.setTotalPayment(new BigDecimal("46797.1898114174"));
        p8.setInterestPayment(new BigDecimal("4063.54"));
        p8.setDebtPayment(new BigDecimal("42733.65"));
        p8.setRemainingDebt(new BigDecimal("178914.04"));
        schedule.add(p8);

        PaymentScheduleElementDto p9 = new PaymentScheduleElementDto();
        p9.setNumber(9);
        p9.setDate(LocalDate.parse(now.plusMonths(++monthCounter).toString()));
        p9.setTotalPayment(new BigDecimal("46797.1898114174"));
        p9.setInterestPayment(new BigDecimal("3280.09"));
        p9.setDebtPayment(new BigDecimal("43517.10"));
        p9.setRemainingDebt(new BigDecimal("135396.94"));
        schedule.add(p9);

        PaymentScheduleElementDto p10 = new PaymentScheduleElementDto();
        p10.setNumber(10);
        p10.setDate(LocalDate.parse(now.plusMonths(++monthCounter).toString()));
        p10.setTotalPayment(new BigDecimal("46797.1898114174"));
        p10.setInterestPayment(new BigDecimal("2482.28"));
        p10.setDebtPayment(new BigDecimal("44314.91"));
        p10.setRemainingDebt(new BigDecimal("91082.03"));
        schedule.add(p10);

        PaymentScheduleElementDto p11 = new PaymentScheduleElementDto();
        p11.setNumber(11);
        p11.setDate(LocalDate.parse(now.plusMonths(++monthCounter).toString()));
        p11.setTotalPayment(new BigDecimal("46797.1898114174"));
        p11.setInterestPayment(new BigDecimal("1669.84"));
        p11.setDebtPayment(new BigDecimal("45127.35"));
        p11.setRemainingDebt(new BigDecimal("45954.68"));
        schedule.add(p11);

        PaymentScheduleElementDto p12 = new PaymentScheduleElementDto();
        p12.setNumber(12);
        p12.setDate(LocalDate.parse(now.plusMonths(++monthCounter).toString()));
        p12.setTotalPayment(new BigDecimal("46797.18"));
        p12.setInterestPayment(new BigDecimal("842.50"));
        p12.setDebtPayment(new BigDecimal("45954.68"));
        p12.setRemainingDebt(new BigDecimal("0.00"));
        schedule.add(p12);

        expectedCreditDto.setPaymentSchedule(schedule);
    }
}