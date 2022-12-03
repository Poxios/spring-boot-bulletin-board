package com.poxios.bulletin.global;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDuplicate(DataIntegrityViolationException ex) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            // Case: duplicated unique column
            return new ResponseEntity<>("Bad Request (Duplicated)", HttpStatus.BAD_REQUEST);
        } else {
            log.error("UNEXPECTED handleDuplicate RootCause: " + ex.getRootCause().getClass().getCanonicalName());
            log.error("UNEXPECTED handleDuplicate Cause: " + ex.getCause().getClass().getCanonicalName());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        if (ex.getCause() instanceof InvalidFormatException) {
            // Case: Not in enum list
            return new ResponseEntity<>("Bad Request (Enum)", HttpStatus.BAD_REQUEST);
        } else {
            log.error("UNEXPECTED handleHttpMessageNotReadable ROOTCAUSE: " + ex.getRootCause().getClass().getCanonicalName());
            log.error("UNEXPECTED handleHttpMessageNotReadable CAUSE: " + ex.getCause().getClass().getCanonicalName());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleElse(Exception ex) {
        log.error("UNEXPECTED ERROR: " + ex.getClass().getCanonicalName());
        log.error("UNEXPECTED MESSAGE: " + (ex.getCause() != null ? ex.getCause().getClass().getCanonicalName() : "NO Cause"));
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
