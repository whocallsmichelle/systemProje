package Model.Session;

import Model.User.User;
import java.sql.*;
import Config.DBconn;

public class SessionOps {


    public static Session createSession(User user) {
        String insertQuery = "INSERT INTO sessions (userId, userType, active) VALUES (?, ?, 1)";

        try (Connection conn = DBconn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, user.getUserId());
            stmt.setInt(2, user.getUserType());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int sessionId = rs.getInt(1);
                    return new Session(sessionId, user.getUserId(), user.getUserType(), true);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error creating session: " + e.getMessage());
        }

        return null;
    }


    public static Session getSession(int sessionID){
        String query = "SELECT * FROM sessions WHERE id = ?";

        try (Connection conn = DBconn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, sessionID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("userId");
                int userType = rs.getInt("userType");
                boolean active = rs.getBoolean("active");
                return new Session(sessionID, userId, userType, active);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving session: " + e.getMessage());
        }

        return null;
    }

    public static boolean deactivateSession(int sessionID) {
        String updateQuery = "UPDATE sessions SET active = 0 WHERE id = ?";

        try (Connection conn = DBconn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setInt(1, sessionID);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error deactivating session: " + e.getMessage());
        }

        return false;
    }
    public static boolean sessionKontrol(int userId){
        String query = "SELECT * FROM sessions WHERE userId = ? AND active = 1";

        try (Connection conn = DBconn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            return rs.next(); // If a session exists, return true

        } catch (SQLException e) {
            System.out.println("Error checking session: " + e.getMessage());
        }

        return false; // No active session found
    }
}
