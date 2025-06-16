package ru.outofmemoryguru.calculator.model;

import lombok.Data;
import ru.outofmemoryguru.calculator.controller.dto.LoanOfferModelService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class LoanStatement {
    private BigDecimal amount;
    private Integer term;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private LocalDate birthdate;
    private String passportSeries;
    private String passportNumber;
    List<LoanOfferModelService> preOffers;
}
