package ru.outofmemoryguru.calculator.testdata;

import ru.outofmemoryguru.calculator.controller.dto.EmploymentDto;
import ru.outofmemoryguru.calculator.model.EmploymentStatus;
import ru.outofmemoryguru.calculator.model.Gender;
import ru.outofmemoryguru.calculator.model.MaritalStatus;
import ru.outofmemoryguru.calculator.model.Position;
import ru.outofmemoryguru.calculator.service.to.LoanStatementServiceModel;
import ru.outofmemoryguru.calculator.service.to.ScoringDataServiceModel;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ServiceModelTestData {

    public static final LoanStatementServiceModel loanStatementServiceModel1 = new LoanStatementServiceModel();
    static {
        loanStatementServiceModel1.setAmount(new BigDecimal("500000"));
        loanStatementServiceModel1.setTerm(12);
        loanStatementServiceModel1.setFirstName("Иван");
        loanStatementServiceModel1.setLastName("Иванов");
        loanStatementServiceModel1.setMiddleName("Иванович");
        loanStatementServiceModel1.setEmail("ivan@ya.ru");
        loanStatementServiceModel1.setBirthdate(LocalDate.of(1990, 1, 1));
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
}
