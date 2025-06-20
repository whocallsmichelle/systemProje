package Controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    @Test
    void login() {
        LoginController loginController = new LoginController();
        Result result = loginController.login("testUser2", "testPass2");
        System.out.println(result);
        assertEquals("success", result.status);
        assertNotEquals(-1, result.sessionId);
    }

    @Test
    void logout() {
        LoginController loginController = new LoginController();
        int sessionId = 4; // This should be replaced with a valid session ID from your test setup
        loginController.logout(sessionId);

        System.out.println("Logout successful for session ID: " + sessionId);
    }

    @Test
    void createAcount() {
        LoginController loginController = new LoginController();
        Result result = loginController.createAcount("testUser2", "testPass2", 2);
        System.out.println(result);
        assertEquals("success", result.status);

    }
}