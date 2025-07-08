package ru.outofmemoryguru.deal.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExternalServiceExceptionHandler {

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<?> handleHttpServerError(HttpServerErrorException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Calculator unavailable or returned error");
        body.put("externalError", ex.getResponseBodyAsString());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(body);
    }
}
