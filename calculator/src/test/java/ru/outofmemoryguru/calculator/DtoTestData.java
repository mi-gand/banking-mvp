package ru.outofmemoryguru.calculator;

import ru.outofmemoryguru.calculator.dto.LoanStatementRequestDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DtoTestData {

    public static final LoanStatementRequestDto loanStatementRequestDto1 = new LoanStatementRequestDto();
    static {
        loanStatementRequestDto1.setAmount(new BigDecimal("500000"));
        loanStatementRequestDto1.setTerm(12);
        loanStatementRequestDto1.setFirstName("Иван");
        loanStatementRequestDto1.setLastName("Иванов");
        loanStatementRequestDto1.setMiddleName("Иванович");
        loanStatementRequestDto1.setEmail("ivan@ya.ru");
        loanStatementRequestDto1.setBirthdate(LocalDate.of(1990, 1, 1));
        loanStatementRequestDto1.setPassportSeries("1234");
        loanStatementRequestDto1.setPassportNumber("567890");
    }
}
