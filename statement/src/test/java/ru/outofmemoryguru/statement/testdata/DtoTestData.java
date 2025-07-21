package ru.outofmemoryguru.statement.testdata;

import ru.outofmemoryguru.statement.controller.dto.LoanOfferDto;
import ru.outofmemoryguru.statement.controller.dto.LoanStatementRequestDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class DtoTestData {
    public static final int EXPECTED_VALIDATION_ERR_12_NUMBER = 12;
    public static final LoanOfferDto loanOfferDto1 = new LoanOfferDto();
    public static final LoanOfferDto loanOfferDto2 = new LoanOfferDto();
    public static final LoanOfferDto loanOfferDto3 = new LoanOfferDto();
    public static final LoanOfferDto loanOfferDto4 = new LoanOfferDto();

    public static final List<LoanOfferDto> expected4OffersDto = List.of(
            loanOfferDto1,
            loanOfferDto2,
            loanOfferDto3,
            loanOfferDto4
    );

    static {
        loanOfferDto1.setStatementId(UUID.fromString("aa111111-1111-1111-1111-111111111111"));
        loanOfferDto1.setRequestedAmount(new BigDecimal("500000"));
        loanOfferDto1.setTotalAmount(new BigDecimal("584922.76"));
        loanOfferDto1.setTerm(12);
        loanOfferDto1.setMonthlyPayment(new BigDecimal("48743.56"));
        loanOfferDto1.setRate(new BigDecimal("30.0"));
        loanOfferDto1.setInsuranceEnabled(false);
        loanOfferDto1.setSalaryClient(false);

        loanOfferDto2.setStatementId(UUID.fromString("aa111111-1111-1111-1111-111111111111"));
        loanOfferDto2.setRequestedAmount(new BigDecimal("500000"));
        loanOfferDto2.setTotalAmount(new BigDecimal("581975.39"));
        loanOfferDto2.setTerm(12);
        loanOfferDto2.setMonthlyPayment(new BigDecimal("48497.95"));
        loanOfferDto2.setRate(new BigDecimal("29.0"));
        loanOfferDto2.setInsuranceEnabled(false);
        loanOfferDto2.setSalaryClient(true);

        loanOfferDto3.setStatementId(UUID.fromString("aa111111-1111-1111-1111-111111111111"));
        loanOfferDto3.setRequestedAmount(new BigDecimal("500000"));
        loanOfferDto3.setTotalAmount(new BigDecimal("676104.37"));
        loanOfferDto3.setTerm(12);
        loanOfferDto3.setMonthlyPayment(new BigDecimal("56342.03"));
        loanOfferDto3.setRate(new BigDecimal("27.0"));
        loanOfferDto3.setInsuranceEnabled(true);
        loanOfferDto3.setSalaryClient(false);

        loanOfferDto4.setStatementId(UUID.fromString("aa111111-1111-1111-1111-111111111111"));
        loanOfferDto4.setRequestedAmount(new BigDecimal("500000"));
        loanOfferDto4.setTotalAmount(new BigDecimal("673180.79"));
        loanOfferDto4.setTerm(12);
        loanOfferDto4.setMonthlyPayment(new BigDecimal("56098.40"));
        loanOfferDto4.setRate(new BigDecimal("26.0"));
        loanOfferDto4.setInsuranceEnabled(true);
        loanOfferDto4.setSalaryClient(true);
    }

    public static final LoanStatementRequestDto loanStatementDtoFromStatementAa11 = new LoanStatementRequestDto();

    static {
        loanStatementDtoFromStatementAa11.setAmount(new BigDecimal("500000"));
        loanStatementDtoFromStatementAa11.setTerm(12);
        loanStatementDtoFromStatementAa11.setFirstName("Ivan");
        loanStatementDtoFromStatementAa11.setLastName("Ivanov");
        loanStatementDtoFromStatementAa11.setMiddleName("Ivanovich");
        loanStatementDtoFromStatementAa11.setEmail("ivan@ya.ru");
        loanStatementDtoFromStatementAa11.setBirthdate(LocalDate.parse("1985-07-15"));
        loanStatementDtoFromStatementAa11.setPassportSeries("1234");
        loanStatementDtoFromStatementAa11.setPassportNumber("567890");
    }

    public static final LoanStatementRequestDto loanStatementRequestDtoInvalid12Err = new LoanStatementRequestDto();

    static {
        loanStatementRequestDtoInvalid12Err.setAmount(new BigDecimal("20000001"));
        loanStatementRequestDtoInvalid12Err.setTerm(21);
        loanStatementRequestDtoInvalid12Err.setFirstName("QwertyQwertyQwertyQwertyQwerty1");
        loanStatementRequestDtoInvalid12Err.setLastName("QwertyQwertyQwertyQwertyQwerty2");
        loanStatementRequestDtoInvalid12Err.setMiddleName("QwertyQwertyQwertyQwertyQwerty3");
        loanStatementRequestDtoInvalid12Err.setEmail("wrongEmail");
        loanStatementRequestDtoInvalid12Err.setBirthdate(LocalDate.now().minusYears(5));
        loanStatementRequestDtoInvalid12Err.setPassportSeries("12345");
        loanStatementRequestDtoInvalid12Err.setPassportNumber("1234567");
    }
}
