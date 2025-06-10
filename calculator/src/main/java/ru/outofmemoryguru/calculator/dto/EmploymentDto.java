package ru.outofmemoryguru.calculator.dto;

import lombok.Data;
import ru.outofmemoryguru.calculator.util.EmploymentStatus;
import ru.outofmemoryguru.calculator.util.Position;

import java.math.BigDecimal;

@Data
public class EmploymentDto {
    private EmploymentStatus employmentStatus;
    private String employerINN;
    private BigDecimal salary;
    private Position position;
    private Integer workExperienceTotal;
    private Integer workExperienceCurrent;
}
