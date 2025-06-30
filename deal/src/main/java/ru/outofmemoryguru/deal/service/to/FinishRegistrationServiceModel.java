package ru.outofmemoryguru.deal.service.to;

import lombok.Data;
import ru.outofmemoryguru.deal.model.enumdata.Gender;
import ru.outofmemoryguru.deal.model.enumdata.MaritalStatus;
import ru.outofmemoryguru.deal.model.jsonb.Employment;

import java.time.LocalDate;

@Data
public class FinishRegistrationServiceModel {
    private Gender gender;
    private MaritalStatus maritalStatus;
    private Integer dependentAmount;
    private LocalDate passportIssueDate;
    private String passportIssueBranch;
    private Employment employment;
    private String accountNumber;
}
