package ru.outofmemoryguru.statement.handlers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.outofmemoryguru.statement.handlers.dto.FieldValidationError;
import ru.outofmemoryguru.statement.handlers.dto.ValidationErrorResponse;

import java.util.List;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<FieldValidationError> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new FieldValidationError(error.getField(), error.getDefaultMessage()
                ))
                .toList();


        return ResponseEntity
                .badRequest()
                .body(new ValidationErrorResponse("Проверка не пройдена", errors));
    }
}



