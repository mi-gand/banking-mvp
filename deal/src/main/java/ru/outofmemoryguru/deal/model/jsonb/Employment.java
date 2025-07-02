package ru.outofmemoryguru.deal.model.jsonb;

import lombok.Data;
import ru.outofmemoryguru.deal.model.enumdata.EmploymentStatus;
import ru.outofmemoryguru.deal.model.enumdata.Position;

import java.math.BigDecimal;

@Data
public class Employment {
    private EmploymentStatus status;
    private String employerInn;
    private BigDecimal salary;
    private Position position;
    private Integer workExperienceTotal;
    private Integer workExperienceCurrent;
}
