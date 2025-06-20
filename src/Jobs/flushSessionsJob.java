package Jobs;

import Config.DBconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class flushSessionsJob {
    public static boolean flushSessions() {
        String updateQuery = "UPDATE sessions SET active = 0";

        try (Connection conn = DBconn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error deactivating session: " + e.getMessage());
        }

        return false;
    }
}
