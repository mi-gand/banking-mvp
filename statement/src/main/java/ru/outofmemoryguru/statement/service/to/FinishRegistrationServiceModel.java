package ru.outofmemoryguru.statement.service.to;

import lombok.Data;
import ru.outofmemoryguru.statement.model.enumdata.Gender;
import ru.outofmemoryguru.statement.model.enumdata.MaritalStatus;

import java.time.LocalDate;

@Data
public class FinishRegistrationServiceModel {
    private Gender gender;
    private MaritalStatus maritalStatus;
    private Integer dependentAmount;
    private LocalDate passportIssueDate;
    private String passportIssueBranch;
    private EmploymentServiceModel employment;
    private String accountNumber;
}
