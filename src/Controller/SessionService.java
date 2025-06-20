package Controller;

import Model.Session.Session;
import Model.User.User;
import Model.Session.SessionOps;

public class SessionService {

    public static Session createSession(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        return SessionOps.createSession(user);
    }
    public static Result logOut(int sessionId) {
        boolean result = SessionOps.deactivateSession(sessionId);
        if (result) {
            return new Result("success", "Session logged out successfully", sessionId);
        } else {
            return new Result("failure", "Failed to log out session", -1);
        }
    }
    public Result yetkiKontrol(int sessionID, String  yetki) {
        if (yetki == null || yetki.isEmpty()) {
            return new Result("failure", "Permission type cannot be null or empty", -1);
        }
        int yetkiInt;
        if (yetki == "admin") {
            yetkiInt = 1; // Assuming 1 represents admin
        } else if (yetki == "user") {
            yetkiInt = 2; // Assuming 2 represents regular user
        } else {
            return new Result("failure", "Invalid permission type", -1);
        }
        Session session = SessionOps.getSession(sessionID);
        Result result;
        if (session == null) {
            return new Result("failure", "Session not found", -1);
        }
        if (session.getUserType() == yetkiInt) {
            result = new Result("success", "User has the required permissions", sessionID);
        } else {
            result = new Result("failure", "User does not have the required permissions", -1);
        }
        return result;

    }

    public static Session getSession(int sessionID) {
        return SessionOps.getSession(sessionID);
    }
    public static Result isSessionActive(User user) {
        Boolean session = SessionOps.sessionKontrol(user.getUserId());
        if (session) {
            return new Result("failure", "Session is active", -1);
        } else {
            return new Result("sucsess", "Session is not active", -1);
        }

    }
}
