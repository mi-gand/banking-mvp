package ru.outofmemoryguru.statement.service.to;

import lombok.Data;
import ru.outofmemoryguru.statement.model.enumdata.EmploymentStatus;
import ru.outofmemoryguru.statement.model.enumdata.Position;

import java.math.BigDecimal;

@Data
public class EmploymentServiceModel {

    private EmploymentStatus employmentStatus;
    private String employerINN;
    private BigDecimal salary;
    private Position position;
    private Integer workExperienceTotal;
    private Integer workExperienceCurrent;
}
