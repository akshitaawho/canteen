package test;

import org.junit.Before;
import org.junit.Test;
import com.example.User;
import java.io.*;
import static org.junit.Assert.*;

public class UserTest {

    private static final String TEST_FILE = "test_user_credentials.txt";

    @Before
    public void setUp() throws Exception {
        // Use a separate test file for credentials
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE))) {
            writer.write("testuser:password123");
            writer.newLine();
        }

        User.setUserDataFile(TEST_FILE);
    }

    @Test
    public void testInvalidLogin() {
        // Invalid username
        assertNull(User.loginUser("wronguser", "password123"));

        // Invalid password
        assertNull(User.loginUser("testuser", "wrongpassword"));
    }

    @Test
    public void testValidLogin() {
        // Add valid credentials to the file
        User.registerUser("sahoo", "sahoo");

        // Attempt to log in with valid credentials
        User loggedInUser = User.loginUser("sahoo", "sahoo");
        assertNotNull("User should log in successfully", loggedInUser);
        assertEquals("sahoo", loggedInUser.getUsername());
    }

    @Test
    public void testRegisterUser() {
        // Register a new user and verify login
        User.registerUser("newuser", "newpassword");
        User loggedInUser = User.loginUser("newuser", "newpassword");
        assertNotNull("New user should log in successfully", loggedInUser);
        assertEquals("newuser", loggedInUser.getUsername());
    }
}
