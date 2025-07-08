package ru.outofmemoryguru.deal.handler;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, Object>> handlePathValidation(HandlerMethodValidationException ex) {
        List<Map<String, String>> errors = new ArrayList<>();

        for (ParameterValidationResult paramResult : ex.getParameterValidationResults()) {
            paramResult.getResolvableErrors().forEach(error -> {
                Map<String, String> err = new HashMap<>();
                err.put("property", paramResult.getMethodParameter().getParameterName());
                err.put("message", error.getDefaultMessage());
                errors.add(err);
            });
        }

        Map<String, Object> body = new HashMap<>();
        body.put("message", "Validation failed");
        body.put("errors", errors);

        return ResponseEntity.badRequest().body(body);
    }
}
