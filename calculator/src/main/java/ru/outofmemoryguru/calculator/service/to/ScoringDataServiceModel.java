package ru.outofmemoryguru.calculator.service.to;

import lombok.Data;
import ru.outofmemoryguru.calculator.controller.dto.EmploymentDto;
import ru.outofmemoryguru.calculator.model.Gender;
import ru.outofmemoryguru.calculator.model.MaritalStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ScoringDataServiceModel {
    private BigDecimal amount;
    private Integer term;
    private String firstName;
    private String lastName;
    private String middleName;
    private Gender gender;
    private LocalDate birthdate;
    private String passportSeries;
    private String passportNumber;
    private LocalDate passportIssueDate;
    private String passportIssueBranch;
    private MaritalStatus maritalStatus;
    private Integer dependentAmount;
    private EmploymentDto employment;
    private String accountNumber;
    private boolean isInsuranceEnabled;
    private boolean isSalaryClient;
}
