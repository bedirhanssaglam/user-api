package com.example.demo.exception;

// Custom exception class to handle invalid input scenarios
public class InvalidInputException extends RuntimeException {

    // Constructor that takes a custom error message and passes it to the superclass
    public InvalidInputException(String message) {
        super(message); // Passing the message to the RuntimeException constructor
    }
}
