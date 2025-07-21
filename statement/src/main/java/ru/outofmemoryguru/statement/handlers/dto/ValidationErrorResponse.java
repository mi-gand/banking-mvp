package ru.outofmemoryguru.statement.handlers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ValidationErrorResponse {
    private final String message;
    private final List<FieldValidationError> errors;
}
