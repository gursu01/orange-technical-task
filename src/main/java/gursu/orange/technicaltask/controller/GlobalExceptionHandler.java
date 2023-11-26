package gursu.orange.technicaltask.controller;

import gursu.orange.technicaltask.exceptions.ExchangeCurrencyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExchangeCurrencyException.class)
    public ResponseEntity<String> handleException(ExchangeCurrencyException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }
}
