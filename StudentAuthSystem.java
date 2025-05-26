/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package studentauthsystem;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Professional student authentication system with enhanced features
 */
public class StudentAuthSystem {
    private final AuthManager authManager;
     private boolean userAuthenticated = false;
    private String loggedInUser = null;
    private int messagesSent = 0;
    
    public static void main(String[] args) {
        StudentAuthSystem system = new StudentAuthSystem();
        system.run();
    }
    
    public StudentAuthSystem() {
        authManager = new AuthManager();
    }
    
    public void run() {
        while (true) {
            String[] options = {"Registration", "Login","Send Message", "Close"};
            int choice = JOptionPane.showOptionDialog(null,
                "Authentication Portal\nPlease select an option:",
                "Main Menu",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);
            
            switch (choice) {
                case 0 -> registerStudent();
                case 1 -> loginStudent();
                case 2 -> {
                    if (userAuthenticated) {
                        QuickChatApp.start(loggedInUser);
                    } else {
                        showError("Please log in to access QuickChat.");
                    }
                }
                case 3, JOptionPane.CLOSED_OPTION -> {
                    if (confirmExit()) System.exit(0);
                }
            }
        }
    }
    
    private void registerStudent() {
        String firstName = getValidatedInput("Enter first name:", "name");
        if (firstName == null) return;
        
        String lastName = getValidatedInput("Enter last name:", "name");
        if (lastName == null) return;
        
        String username = getValidatedInput("Enter username (must contain _ and be ≤5 chars):", "username");
        if (username == null) return;
        
        String password = getValidatedPassword();
        if (password == null) return;
        
        String phone = getValidatedInput("Enter SA phone number (+27xxxxxxxxx):", "phone");
        if (phone == null) return;
        
        String result = authManager.registerStudent(firstName, lastName, username, password, phone);
        showMessage(result);
    }
    
    private void loginStudent() {
        String username = getValidatedInput("Enter username:", "username");
        if (username == null) return;
        
        String password = getValidatedPassword();
        if (password == null) return;
        
        String result = authManager.loginStudent(username, password);
        
        // part 2:
        if (result.equalsIgnoreCase("Login successful")) {
    userAuthenticated = true;
    loggedInUser = username;
     } else
        {
    userAuthenticated = false;
    loggedInUser = null;
        }
   showMessage(result);
    }
    
   
    // Enhanced input validation with automatic retry
    private String getValidatedInput(String prompt, String type) {
        String input;
        do {
            input = JOptionPane.showInputDialog(prompt);
            if (input == null) return null; // User cancelled
            
            String error = authManager.validateInput(input, type);
            if (error == null) return input; // Valid input
            
            showError(error); // Show error and retry
        } while (true);
    }
    
    // Secure password input with real-time strength check
    private String getValidatedPassword() {
        JPasswordField passwordField = new JPasswordField();
        JLabel strengthLabel = new JLabel("Password strength: ");
        
        passwordField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { update(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { update(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { update(); }
            
            private void update() {
                String password = new String(passwordField.getPassword());
                int strength = authManager.checkPasswordStrength(password);
                strengthLabel.setText("Password strength: " + authManager.getStrengthDescription(strength));
            }
        });
        
        while (true) {
            int option = JOptionPane.showConfirmDialog(null, 
                new Object[]{"Enter password (8+ chars, 1 uppercase, 1 number, 1 special char):", 
                             passwordField, strengthLabel},
                "Password",
                JOptionPane.OK_CANCEL_OPTION);
            
            if (option != JOptionPane.OK_OPTION) return null;
            
            String password = new String(passwordField.getPassword());
            String error = authManager.validateInput(password, "password");
            
            if (error == null) return password;
            
            showError(error);
            passwordField.setText("");
        }
    }
    
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, 
            "<html><div style='width: 200px;'>" + message + "</div></html>",
            "System Message",
            message.contains("success") ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE);
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private boolean confirmExit() {
        return JOptionPane.showConfirmDialog(null,
            "Are you sure you want to exit?",
            "Confirm Exit",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
}
