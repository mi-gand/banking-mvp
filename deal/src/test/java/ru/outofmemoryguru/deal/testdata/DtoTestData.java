package ru.outofmemoryguru.deal.testdata;

import ru.outofmemoryguru.deal.controller.dto.FinishRegistrationRequestDto;
import ru.outofmemoryguru.deal.controller.dto.LoanOfferDto;
import ru.outofmemoryguru.deal.controller.dto.LoanStatementRequestDto;
import ru.outofmemoryguru.deal.controller.dto.ScoringDataDto;
import ru.outofmemoryguru.deal.model.Statement;
import ru.outofmemoryguru.deal.model.enumdata.*;
import ru.outofmemoryguru.deal.model.jsonb.AppliedOffer;
import ru.outofmemoryguru.deal.model.jsonb.Employment;
import ru.outofmemoryguru.deal.model.jsonb.StatusHistory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class DtoTestData {

    public static final LoanStatementRequestDto loanStatementDtoFromStatementAa11 = new LoanStatementRequestDto();

    static {
        loanStatementDtoFromStatementAa11.setAmount(new BigDecimal("500000"));
        loanStatementDtoFromStatementAa11.setTerm(12);
        loanStatementDtoFromStatementAa11.setFirstName("Ivan");
        loanStatementDtoFromStatementAa11.setLastName("Ivanov");
        loanStatementDtoFromStatementAa11.setMiddleName("Ivanovich");
        loanStatementDtoFromStatementAa11.setEmail("ivan@ya.ru");
        loanStatementDtoFromStatementAa11.setBirthdate(LocalDate.parse("1985-07-15"));
        loanStatementDtoFromStatementAa11.setPassportSeries("1234");
        loanStatementDtoFromStatementAa11.setPassportNumber("567890");
    }

    public static final String LOAN_STATEMENT_JSON_1 = """
            {
              "amount": 500000,
              "term": 12,
              "firstName": "Ivan",
              "lastName": "Ivanov",
              "middleName": "Ivanovich",
              "email": "ivan@ya.ru",
              "birthdate": "1985-07-15",
              "passportSeries": "1234",
              "passportNumber": "567890"
            }
            """;

    public static final String EXPECTED4_OFFERS_JSON = """
            [
              {
                "statementId": "fdb3f077-01ed-4606-a75b-d5bd2353138f",
                "requestedAmount": 500000,
                "totalAmount": 673180.79,
                "term": 12,
                "monthlyPayment": 56098.40,
                "rate": 26.0,
                "insuranceEnabled": true,
                "salaryClient": true
              },
              {
                "statementId": "fdb3f077-01ed-4606-a75b-d5bd2353138f",
                "requestedAmount": 500000,
                "totalAmount": 676104.37,
                "term": 12,
                "monthlyPayment": 56342.03,
                "rate": 27.0,
                "insuranceEnabled": true,
                "salaryClient": false
              },
              {
                "statementId": "fdb3f077-01ed-4606-a75b-d5bd2353138f",
                "requestedAmount": 500000,
                "totalAmount": 581975.39,
                "term": 12,
                "monthlyPayment": 48497.95,
                "rate": 29.0,
                "insuranceEnabled": false,
                "salaryClient": true
              },
              {
                "statementId": "fdb3f077-01ed-4606-a75b-d5bd2353138f",
                "requestedAmount": 500000,
                "totalAmount": 584922.76,
                "term": 12,
                "monthlyPayment": 48743.56,
                "rate": 30.0,
                "insuranceEnabled": false,
                "salaryClient": false
              }
            ]
            """;

    public static final Statement statementForDtoTest = new Statement();

    static {


        statementForDtoTest.setStatementId(UUID.fromString("aa111111-1111-1111-1111-111111111111"));
        statementForDtoTest.setClientId(UUID.fromString("bb111111-1111-1111-1111-111111111111"));
        statementForDtoTest.setCreditId(UUID.fromString("cc111111-1111-1111-1111-111111111111"));
        statementForDtoTest.setStatus(ApplicationStatus.PREAPPROVAL);
        statementForDtoTest.setCreationDate(LocalDate.now());

        AppliedOffer offer = new AppliedOffer();
        offer.setRequestedAmount(new BigDecimal("500000"));
        offer.setTotalAmount(new BigDecimal("584922.76"));
        offer.setTerm(12);
        offer.setMonthlyPayment(new BigDecimal("48743.56"));
        offer.setRate(new BigDecimal("30.0"));
        offer.setInsuranceEnabled(false);
        offer.setSalaryClient(false);
        statementForDtoTest.setAppliedOffer(offer);

        statementForDtoTest.setSignDate(null);

        StatusHistory history = new StatusHistory();
        history.setStatus(ApplicationStatus.PREAPPROVAL);
        history.setTime(LocalDateTime.now());

        statementForDtoTest.setStatusHistory(List.of(history));

    }

    public static final FinishRegistrationRequestDto finishRegistrationRequestDto1 = new FinishRegistrationRequestDto();

    static {
        finishRegistrationRequestDto1.setGender(Gender.MALE);
        finishRegistrationRequestDto1.setMaritalStatus(MaritalStatus.MARRIED);
        finishRegistrationRequestDto1.setDependentAmount(2);
        finishRegistrationRequestDto1.setPassportIssueDate(LocalDate.of(2005, 6, 20));
        finishRegistrationRequestDto1.setPassportIssueBranch("MVD");

        Employment employment = new Employment();
        employment.setSalary(BigDecimal.valueOf(80000));
        employment.setStatus(EmploymentStatus.EMPLOYED);
        employment.setPosition(Position.WORKER);
        employment.setEmployerInn("1234567890");
        employment.setWorkExperienceTotal(15);
        employment.setWorkExperienceCurrent(5);

        finishRegistrationRequestDto1.setEmployment(employment);
        finishRegistrationRequestDto1.setAccountNumber("40817810099910004312");
    }

    public static final ScoringDataDto scoringDataDtoForDtoTest = new ScoringDataDto();

    static {
        scoringDataDtoForDtoTest.setAmount(new BigDecimal("500000"));
        scoringDataDtoForDtoTest.setTerm(12);

        scoringDataDtoForDtoTest.setFirstName("Ivan");
        scoringDataDtoForDtoTest.setLastName("Ivanov");
        scoringDataDtoForDtoTest.setMiddleName("Ivanovich");

        scoringDataDtoForDtoTest.setGender(Gender.MALE);
        scoringDataDtoForDtoTest.setBirthdate(LocalDate.of(1985, 7, 15));

        scoringDataDtoForDtoTest.setPassportSeries("1234");
        scoringDataDtoForDtoTest.setPassportNumber("567890");
        scoringDataDtoForDtoTest.setPassportIssueDate(LocalDate.of(2005, 6, 20));
        scoringDataDtoForDtoTest.setPassportIssueBranch("MVD");

        scoringDataDtoForDtoTest.setMaritalStatus(MaritalStatus.MARRIED);
        scoringDataDtoForDtoTest.setDependentAmount(2);

        Employment employment = new Employment();
        employment.setSalary(BigDecimal.valueOf(80000));
        employment.setStatus(EmploymentStatus.EMPLOYED);
        employment.setPosition(Position.WORKER);
        employment.setEmployerInn("1234567890");
        employment.setWorkExperienceTotal(15);
        employment.setWorkExperienceCurrent(5);

        scoringDataDtoForDtoTest.setAccountNumber("40817810099910004312");

        scoringDataDtoForDtoTest.setInsuranceEnabled(true);
        scoringDataDtoForDtoTest.setSalaryClient(true);
    }

    public static final String SCORING_DATA_JSON = """
            {
              "amount": 500000,
              "term": 12,
              "firstName": "Ivan",
              "lastName": "Ivanov",
              "middleName": "Ivanovich",
              "gender": "MALE",
              "birthdate": "1985-07-15",
              "passportSeries": "1234",
              "passportNumber": "567890",
              "passportIssueDate": "2005-06-20",
              "passportIssueBranch": "MVD",
              "maritalStatus": "MARRIED",
              "dependentAmount": 2,
              "employment": {
                "salary": 80000,
                "status": "EMPLOYED",
                "position": "WORKER",
                "employerInn": "1234567890",
                "workExperienceTotal": 15,
                "workExperienceCurrent": 5
              },
              "accountNumber": "40817810099910004312",
              "insuranceEnabled": true,
              "salaryClient": true
            }
            """;


    public static final LoanOfferDto loanOfferDto1 = new LoanOfferDto();
    public static final LoanOfferDto loanOfferDto2 = new LoanOfferDto();
    public static final LoanOfferDto loanOfferDto3 = new LoanOfferDto();
    public static final LoanOfferDto loanOfferDto4 = new LoanOfferDto();

    public static final String LOAN_OFFER_DTO_1 = """
            {
              "amount": 500000,
              "term": 12,
              "firstName": "Ivan",
              "lastName": "Ivanov",
              "middleName": "Ivanovich",
              "email": "ivan@ya.ru",
              "birthdate": "1985-07-15",
              "passportSeries": "1234",
              "passportNumber": "567890"
            }
            """;

    public static final List<LoanOfferDto> expected4OffersDto = List.of(
            loanOfferDto1,
            loanOfferDto2,
            loanOfferDto3,
            loanOfferDto4
    );

    static {
        loanOfferDto1.setStatementId(UUID.fromString("aa111111-1111-1111-1111-111111111111"));
        loanOfferDto1.setRequestedAmount(new BigDecimal("500000"));
        loanOfferDto1.setTotalAmount(new BigDecimal("584922.76"));
        loanOfferDto1.setTerm(12);
        loanOfferDto1.setMonthlyPayment(new BigDecimal("48743.56"));
        loanOfferDto1.setRate(new BigDecimal("30.0"));
        loanOfferDto1.setInsuranceEnabled(false);
        loanOfferDto1.setSalaryClient(false);

        loanOfferDto2.setStatementId(UUID.fromString("aa111111-1111-1111-1111-111111111111"));
        loanOfferDto2.setRequestedAmount(new BigDecimal("500000"));
        loanOfferDto2.setTotalAmount(new BigDecimal("581975.39"));
        loanOfferDto2.setTerm(12);
        loanOfferDto2.setMonthlyPayment(new BigDecimal("48497.95"));
        loanOfferDto2.setRate(new BigDecimal("29.0"));
        loanOfferDto2.setInsuranceEnabled(false);
        loanOfferDto2.setSalaryClient(true);

        loanOfferDto3.setStatementId(UUID.fromString("aa111111-1111-1111-1111-111111111111"));
        loanOfferDto3.setRequestedAmount(new BigDecimal("500000"));
        loanOfferDto3.setTotalAmount(new BigDecimal("676104.37"));
        loanOfferDto3.setTerm(12);
        loanOfferDto3.setMonthlyPayment(new BigDecimal("56342.03"));
        loanOfferDto3.setRate(new BigDecimal("27.0"));
        loanOfferDto3.setInsuranceEnabled(true);
        loanOfferDto3.setSalaryClient(false);

        loanOfferDto4.setStatementId(UUID.fromString("aa111111-1111-1111-1111-111111111111"));
        loanOfferDto4.setRequestedAmount(new BigDecimal("500000"));
        loanOfferDto4.setTotalAmount(new BigDecimal("673180.79"));
        loanOfferDto4.setTerm(12);
        loanOfferDto4.setMonthlyPayment(new BigDecimal("56098.40"));
        loanOfferDto4.setRate(new BigDecimal("26.0"));
        loanOfferDto4.setInsuranceEnabled(true);
        loanOfferDto4.setSalaryClient(true);
    }
}
