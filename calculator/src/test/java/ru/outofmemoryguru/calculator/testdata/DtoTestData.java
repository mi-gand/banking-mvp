package ru.outofmemoryguru.calculator.testdata;

import ru.outofmemoryguru.calculator.controller.dto.EmploymentDto;
import ru.outofmemoryguru.calculator.controller.dto.LoanStatementRequestDto;
import ru.outofmemoryguru.calculator.controller.dto.ScoringDataDto;
import ru.outofmemoryguru.calculator.model.EmploymentStatus;
import ru.outofmemoryguru.calculator.model.Gender;
import ru.outofmemoryguru.calculator.model.MaritalStatus;
import ru.outofmemoryguru.calculator.model.Position;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DtoTestData {

    public static final LoanStatementRequestDto loanStatementRequestDto1 = new LoanStatementRequestDto();

    static {
        loanStatementRequestDto1.setAmount(new BigDecimal("500000"));
        loanStatementRequestDto1.setTerm(12);
        loanStatementRequestDto1.setFirstName("Иван");
        loanStatementRequestDto1.setLastName("Иванов");
        loanStatementRequestDto1.setMiddleName("Иванович");
        loanStatementRequestDto1.setEmail("ivan@ya.ru");
        loanStatementRequestDto1.setBirthdate(LocalDate.of(1990, 1, 1));
        loanStatementRequestDto1.setPassportSeries("1234");
        loanStatementRequestDto1.setPassportNumber("567890");
    }

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
        employment.setPosition(Position.WORKER);
        employment.setWorkExperienceTotal(100);
        employment.setWorkExperienceCurrent(20);

        scoringDataDto1.setEmployment(employment);
        scoringDataDto1.setAccountNumber("1234567890123456");
        scoringDataDto1.setInsuranceEnabled(true);
        scoringDataDto1.setSalaryClient(false);
    }
}

