package ru.outofmemoryguru.deal.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import ru.outofmemoryguru.deal.model.enumdata.Gender;
import ru.outofmemoryguru.deal.model.enumdata.MaritalStatus;

import java.time.LocalDate;

@Data
public class FinishRegistrationRequestDto {

    @NotNull
    private Gender gender;

    @NotNull
    private MaritalStatus maritalStatus;

    @Min(0)
    @NotNull
    private Integer dependentAmount;

    @Past
    @NotNull
    private LocalDate passportIssueDate;

    @Size(max = 70, message = "too long issue branch name")
    @NotNull
    private String passportIssueBranch;

    @NotNull
    @Valid
    private EmploymentDto employment;

    @Size(min = 16, max = 16)
    @Pattern(regexp = "^[0-9]+$")
    @NotNull
    private String accountNumber;
}
