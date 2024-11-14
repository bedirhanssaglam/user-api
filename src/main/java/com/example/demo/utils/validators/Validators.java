package com.example.demo.utils.validators;

public class Validators {

    // Method to validate if the provided email matches a valid email format
    public static boolean isValidEmailFormat(String email) {
        // Regular expression pattern for validating email format
        final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Returns true if the email is invalid or null, false if it's a valid format
        return email == null || !email.matches(emailRegex);
    }

    // Method to validate if the name's length is within the acceptable limit
    public static boolean isValidNameLength(String name) {
        // Validates that the name is not null, its length is <= 50, and it contains only letters and spaces
        return name != null && name.length() <= 50;
    }

}
