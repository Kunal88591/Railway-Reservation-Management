package utils;

import exceptions.ValidationException;
import java.util.regex.Pattern;

/**
 * Utility class for validating various inputs
 */
public class ValidationUtils {
    
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    
    private static final Pattern PHONE_PATTERN = 
        Pattern.compile("^[6-9]\\d{9}$"); // Indian phone number
    
    private static final Pattern USERNAME_PATTERN = 
        Pattern.compile("^[a-zA-Z0-9_]{3,20}$");
    
    /**
     * Validate email address
     */
    public static void validateEmail(String email) throws ValidationException {
        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException("Email cannot be empty");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidationException("Invalid email format");
        }
    }
    
    /**
     * Validate phone number
     */
    public static void validatePhoneNumber(String phoneNumber) throws ValidationException {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new ValidationException("Phone number cannot be empty");
        }
        if (!PHONE_PATTERN.matcher(phoneNumber).matches()) {
            throw new ValidationException("Invalid phone number. Must be a 10-digit Indian mobile number");
        }
    }
    
    /**
     * Validate username
     */
    public static void validateUsername(String username) throws ValidationException {
        if (username == null || username.trim().isEmpty()) {
            throw new ValidationException("Username cannot be empty");
        }
        if (!USERNAME_PATTERN.matcher(username).matches()) {
            throw new ValidationException("Username must be 3-20 characters long and contain only letters, numbers, and underscores");
        }
    }
    
    /**
     * Validate password
     */
    public static void validatePassword(String password) throws ValidationException {
        if (password == null || password.isEmpty()) {
            throw new ValidationException("Password cannot be empty");
        }
        if (password.length() < 6) {
            throw new ValidationException("Password must be at least 6 characters long");
        }
    }
    
    /**
     * Validate name
     */
    public static void validateName(String name) throws ValidationException {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Name cannot be empty");
        }
        if (name.trim().length() < 2) {
            throw new ValidationException("Name must be at least 2 characters long");
        }
    }
    
    /**
     * Validate age
     */
    public static void validateAge(int age) throws ValidationException {
        if (age < 1 || age > 120) {
            throw new ValidationException("Age must be between 1 and 120");
        }
    }
    
    /**
     * Validate number of seats
     */
    public static void validateSeats(int seats) throws ValidationException {
        if (seats < 1) {
            throw new ValidationException("Number of seats must be at least 1");
        }
        if (seats > 6) {
            throw new ValidationException("Maximum 6 seats can be booked at once");
        }
    }
}
