package ru.outofmemoryguru.calculator.model;

import lombok.Data;
import ru.outofmemoryguru.calculator.controller.dto.LoanOfferDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
//@Entity
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
    List<LoanOfferDto> preOffers;       //условия сохраняются в заявку
}
