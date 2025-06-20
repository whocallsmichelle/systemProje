package Controller;

import Model.Session.Session;
import Model.User.User;
import Model.Session.SessionOps;

public class SessionService {

    public static Session createSession(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        Session s = SessionOps.createSession(user);

        return s;
    }
    public static Result logOut(int sessionId) {
        boolean result = SessionOps.deactivateSession(sessionId);
        if (result) {
            return new Result("success", "Session logged out successfully", sessionId);
        } else {
            return new Result("failure", "Failed to log out session", -1);
        }
    }
    public Result yetkiKontrol(int sessionID, int yetki) {
        Session session = SessionOps.getSession(sessionID);
        Result result;
        if (session == null) {
            return new Result("failure", "Session not found", -1);
        }
        if (session.getUserType() == yetki) {
            result = new Result("success", "User has the required permissions", sessionID);
        } else {
            result = new Result("failure", "User does not have the required permissions", -1);
        }
        return result;

    }
}
