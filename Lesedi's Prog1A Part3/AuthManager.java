/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentauthsystem;
import java.util.regex.*;


/**
 * Handles all authentication logic with professional enhancements
 */
public class AuthManager {
    private String registeredUsername;
    private String registeredPassword;
    private String registeredPhone;
    private String firstName;
    private String lastName;
    
    // Regex patterns for validation
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^.{1,5}_.{0,4}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+27\\d{9}$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]{2,}$");
    
    public String validateInput(String input, String type) {
        if (input == null || input.trim().isEmpty()) {
            return "Input cannot be empty";
        }
        
        return switch (type) {
            case "username" -> USERNAME_PATTERN.matcher(input).matches() ? null : 
                "Username must:\n- Contain an underscore (_)\n- Be 5 characters or less\nExample: j_doe";
            case "password" -> PASSWORD_PATTERN.matcher(input).matches() ? null :
                "Password must:\n- Be 8+ characters\n- Contain 1 uppercase letter\n- 1 number\n- 1 special character (!@#$%^&*)\nExample: Passw0rd!";
            case "phone" -> PHONE_PATTERN.matcher(input).matches() ? null :
                "SA phone must:\n- Start with +27\n- Followed by 9 digits\nExample: +27821234567";
            case "name" -> NAME_PATTERN.matcher(input).matches() ? null :
                "Name must:\n- Contain only letters\n- Be 2+ characters\nExample: John";
            default -> null;
        };
    }
    
    public int checkPasswordStrength(String password) {
        if (password == null) return 0;
        
        int strength = 0;
        if (password.length() >= 8) strength++;
        if (password.matches(".*[A-Z].*")) strength++;
        if (password.matches(".*[0-9].*")) strength++;
        if (password.matches(".*[!@#$%^&*].*")) strength++;
        if (password.length() >= 12) strength++;
        
        return strength;
    }
    
    public String getStrengthDescription(int strength) {
        String[] descriptions = {"Very Weak", "Weak", "Moderate", "Strong", "Very Strong", "Excellent"};
        return strength < descriptions.length ? descriptions[strength] : "";
    }
    
    public String registerStudent(String firstName, String lastName, 
                                String username, String password, String phone) {
        StringBuilder errors = new StringBuilder();
        
        String validation = validateInput(firstName, "name");
        if (validation != null) errors.append("First name: ").append(validation).append("\n\n");
        
        validation = validateInput(lastName, "name");
        if (validation != null) errors.append("Last name: ").append(validation).append("\n\n");
        
        validation = validateInput(username, "username");
        if (validation != null) errors.append("Username: ").append(validation).append("\n\n");
        
        validation = validateInput(password, "password");
        if (validation != null) errors.append("Password: ").append(validation).append("\n\n");
        
        validation = validateInput(phone, "phone");
        if (validation != null) errors.append("Phone: ").append(validation).append("\n\n");
        
        if (errors.length() > 0) {
            return "Registration failed:\n\n" + errors;
        }
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.registeredUsername = username;
        this.registeredPassword = password;
        this.registeredPhone = phone;
        
        return "Registration successful!\nWelcome " + firstName + " " + lastName;
    }
    
    public String loginStudent(String username, String password) {
        if (username == null || password == null) {
            return "Username and password are required";
        }
        
        if (username.equals(registeredUsername) && password.equals(registeredPassword)) {
            return "Login successful";
        }
        
        return "Login failed. Invalid username or password";
    }
}