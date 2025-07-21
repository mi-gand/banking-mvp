package ru.outofmemoryguru.statement.controller.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LoanStatementRequestDto {      //todo обратить внимание может лучше отдельно валидировать

    @DecimalMin(value = "10000", message = "min value is 10000")
    @DecimalMax(value = "20000000", message = "max value is 20000000")
    @NotNull
    private BigDecimal amount;

    @Min(value = 1, message = "min value is 1 year")
    @Max(value = 20, message = "max value is 20 years")
    @NotNull
    private Integer term;

    @Size(max = 30)
    @Pattern(regexp = "^[A-Za-z]+$")
    @NotNull
    private String firstName;

    @Size(max = 30)
    @Pattern(regexp = "^[A-Za-z]+$")
    @NotNull
    private String lastName;

    @Size(max = 30)
    @Pattern(regexp = "^[A-Za-z]+$")
    @Nullable
    private String middleName;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private LocalDate birthdate;

    @Size(max = 4, message = "must be from 1 to 4 digits")
    @Pattern(regexp = "^[0-9]+$", message = "must be only digits")
    @NotNull
    private String passportSeries;

    @Size(max = 6, message = "must be from 1 to 6 digits")
    @Pattern(regexp = "^[0-9]+$", message = "must be only digits")
    @NotNull
    private String passportNumber;

    @AssertTrue(message = "service is not provided to people over 60 years of age or younger 18")
    private boolean isBirthdateValid() {
        if (birthdate == null) {
            return false;
        }
        LocalDate today = LocalDate.now();
        LocalDate minDate = today.minusYears(60);
        LocalDate maxDate = today.minusYears(18);
        return !birthdate.isBefore(minDate) && !birthdate.isAfter(maxDate);
    }
}
