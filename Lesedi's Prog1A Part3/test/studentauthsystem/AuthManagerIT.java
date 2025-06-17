/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package studentauthsystem;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AuthManagerIT {
    private AuthManager authManager;
    
    @Before
    public void setUp() {
        authManager = new AuthManager();
    }

    // ========== VALIDATION TESTS ==========
    
    @Test
    public void testValidateUsername_Success() {
        assertNull(authManager.validateInput("a_b", "username"));
        assertNull(authManager.validateInput("user_", "username"));
    }
    
    @Test
    public void testValidateUsername_Failure() {
        assertNotNull(authManager.validateInput("username", "username")); // Too long
        assertNotNull(authManager.validateInput("user", "username")); // Missing underscore
        assertNotNull(authManager.validateInput("", "username")); // Empty
    }
    
    @Test
    public void testValidatePassword_Success() {
        assertNull(authManager.validateInput("Passw0rd!", "password"));
        assertNull(authManager.validateInput("A1@bcdef", "password"));
    }
    
    @Test
    public void testValidatePassword_Failure() {
        assertNotNull(authManager.validateInput("password", "password")); // No uppercase/number/special
        assertNotNull(authManager.validateInput("Password1", "password")); // Missing special char
        assertNotNull(authManager.validateInput("Pwd!", "password")); // Too short
    }
    
    @Test
    public void testValidatePhone_Success() {
        assertNull(authManager.validateInput("+27821234567", "phone"));
    }
    
    @Test
    public void testValidatePhone_Failure() {
        assertNotNull(authManager.validateInput("0821234567", "phone")); // Wrong prefix
        assertNotNull(authManager.validateInput("+27123", "phone")); // Too short
        assertNotNull(authManager.validateInput("+27abcdefgh", "phone")); // Non-digits
    }
    
    @Test
    public void testValidateName_Success() {
        assertNull(authManager.validateInput("John", "name"));
        assertNull(authManager.validateInput("Smith", "name"));
    }
    
    @Test
    public void testValidateName_Failure() {
        assertNotNull(authManager.validateInput("J", "name")); // Too short
        assertNotNull(authManager.validateInput("McDonald1", "name")); // Contains number
        assertNotNull(authManager.validateInput("", "name")); // Empty
    }
    
    // ========== PASSWORD STRENGTH TESTS ==========
    
    @Test
    public void testPasswordStrength() {
        assertEquals(1, authManager.checkPasswordStrength("password")); // Only length
        assertEquals(3, authManager.checkPasswordStrength("Password1")); // Missing special
        assertEquals(4, authManager.checkPasswordStrength("Passw0rd!")); // Complete
        assertEquals(5, authManager.checkPasswordStrength("VeryL0ngPassw0rd!")); // Long
    }
    
    @Test
    public void testStrengthDescription() {
        assertEquals("Very Weak", authManager.getStrengthDescription(0));
        assertEquals("Weak", authManager.getStrengthDescription(1));
        assertEquals("Moderate", authManager.getStrengthDescription(2));
        assertEquals("Strong", authManager.getStrengthDescription(3));
        assertEquals("Very Strong", authManager.getStrengthDescription(4));
        assertEquals("Excellent", authManager.getStrengthDescription(5));
    }
    
    // ========== REGISTRATION TESTS ==========
    
    @Test
    public void testRegisterStudent_Success() {
        String result = authManager.registerStudent(
            "John", "Doe", "j_doe", "Passw0rd!", "+27821234567");
        assertTrue(result.contains("success"));
    }
    
    @Test
    public void testRegisterStudent_Failure() {
        String result = authManager.registerStudent(
            "J", "Doe1", "username", "weak", "0821234567");
        assertTrue(result.contains("failed"));
        assertTrue(result.contains("First name"));
        assertTrue(result.contains("Last name"));
        assertTrue(result.contains("Username"));
        assertTrue(result.contains("Password"));
        assertTrue(result.contains("Phone"));
    }
    
    // ========== LOGIN TESTS ==========
    
    @Test
    public void testLogin_Success() {
        authManager.registerStudent("John", "Doe", "j_doe", "Passw0rd!", "+27821234567");
        String result = authManager.loginStudent("j_doe", "Passw0rd!");
        assertTrue(result.contains("Welcome back"));
    }
    
    @Test
    public void testLogin_Failure() {
        authManager.registerStudent("John", "Doe", "j_doe", "Passw0rd!", "+27821234567");
        String result = authManager.loginStudent("j_doe", "wrongpass");
        assertTrue(result.contains("failed"));
    }
}