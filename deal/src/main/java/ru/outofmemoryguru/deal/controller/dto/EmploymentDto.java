package ru.outofmemoryguru.deal.controller.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import ru.outofmemoryguru.deal.model.enumdata.EmploymentStatus;
import ru.outofmemoryguru.deal.model.enumdata.Position;

import java.math.BigDecimal;

@Data
public class EmploymentDto {
    @NotNull
    private EmploymentStatus employmentStatus;

    @Size(min = 12, max = 12)
    @Pattern(regexp = "^[0-9]+$")
    @NotNull
    private String employerINN;

    @DecimalMin(value = "20000", message = "impossible value by law")
    @DecimalMax(value = "20000000", message = "suspicious value")
    @NotNull
    private BigDecimal salary;

    @NotNull
    private Position position;

    @NotNull
    @Max(value = 720, message = "unrealistic value of work experience 60 years")
    @Min(0)
    private Integer workExperienceTotal;

    @NotNull
    @Max(value = 720, message = "unrealistic value of work experience 60 years")
    @Min(0)
    private Integer workExperienceCurrent;
}
