package com.accommodation.accommodation.controllers.exception;

import com.accommodation.accommodation.model.CustomException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MappedExceptions {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomException> handleNotFoundException(EntityNotFoundException ex) {
        CustomException errorResponse = new CustomException(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomException> handleNotFoundException(RuntimeException ex) {
        CustomException errorResponse = new CustomException(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
