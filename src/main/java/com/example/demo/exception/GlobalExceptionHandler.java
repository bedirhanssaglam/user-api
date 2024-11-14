package com.example.demo.exception;

import com.example.demo.models.ResponseWrapper;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // This annotation marks the class as a global exception handler
public class GlobalExceptionHandler {

    // Handler for ResourceNotFoundException, typically thrown when a requested resource is not found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseWrapper<Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        // Respond with HTTP 404 (Not Found) status and a custom error message
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseWrapper.error(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    // Handler for InvalidInputException, thrown when user input is invalid
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ResponseWrapper<Object>> handleInvalidInputException(InvalidInputException ex) {
        // Respond with HTTP 400 (Bad Request) status and a custom error message
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseWrapper.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    // General exception handler for unexpected exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper<Object>> handleGeneralException(Exception ex) {
        // Respond with HTTP 500 (Internal Server Error) status and a generic error message
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseWrapper.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred"));
    }
}
