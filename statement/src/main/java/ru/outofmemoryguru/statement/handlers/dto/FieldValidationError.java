package ru.outofmemoryguru.statement.handlers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldValidationError {
    private final String field;
    private final String message;
}
