package com.example.demo.models;

import org.springframework.http.HttpStatus;

public class ResponseWrapper<T> {
    // Status code of the response (e.g., 200 for success, 500 for error)
    private int code;

    // The actual data to be returned in the response (can be any type)
    private T data;

    // A message associated with the response (optional, e.g., error message)
    private String message;

    // Constructor to initialize the response with code, data, and message
    public ResponseWrapper(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    // Getter for the response code
    public int getCode() {
        return code;
    }

    // Setter for the response code
    public void setCode(int code) {
        this.code = code;
    }

    // Getter for the response data
    public T getData() {
        return data;
    }

    // Setter for the response data
    public void setData(T data) {
        this.data = data;
    }

    // Getter for the response message
    public String getMessage() {
        return message;
    }

    // Setter for the response message
    public void setMessage(String message) {
        this.message = message;
    }

    // Helper method to create a successful response with data
    public static <T> ResponseWrapper<T> success(T data) {
        return new ResponseWrapper<>(HttpStatus.OK.value(), data, null); // 200 is HTTP status code for OK
    }

    // Helper method to create a successful response with data and a custom message
    public static <T> ResponseWrapper<T> success(T data, String message) {
        return new ResponseWrapper<>(HttpStatus.OK.value(), data, message); // 200 is HTTP status code for OK
    }

    // Helper method to create an error response with custom code and message
    public static <T> ResponseWrapper<T> error(int code, String message) {
        return new ResponseWrapper<>(code, null, message); // Code is custom error code (e.g., 400, 500)
    }
}
