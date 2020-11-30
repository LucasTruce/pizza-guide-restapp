package com.pizzaguideapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse> EntityNotFound(EntityNotFoundException ex) {
        EntityErrorResponse postErrorRes = EntityErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(postErrorRes, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String,String>> handleValidation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        List<String> list = new ArrayList<>();

        //errors.put("status", HttpStatus.BAD_REQUEST.toString());
        //errors.put("message", ex.getMessage());
        //errors.put("customErrors", ex.getConstraintViolations().toString());

        List<String> validationErrors = ex.getConstraintViolations().stream().
                map(violation -> errors.put(violation.getPropertyPath().toString(), violation.getMessage()))
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errors);

        //return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
