package com.example.part20.validate;

public class Validation {
    public static boolean isValidUsername(String username) {
        // Add your username validation logic here
        // For example, you can check if it meets minimum/maximum length requirements, contains valid characters, etc.
        return username != null && username.length() >= 4 && username.matches("[a-zA-Z0-9]+");
    }

    public static boolean isValidEmail(String email) {
        // Add your email validation logic here
        // For example, you can use regular expressions to validate email format
        return email != null && email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    public static boolean isValidPassword(String password) {
        // Add your password validation logic here
        // For example, you can check if it meets minimum/maximum length requirements, contains uppercase, lowercase, and special characters, etc.
        return password != null && password.length() >= 8;
    }
}
