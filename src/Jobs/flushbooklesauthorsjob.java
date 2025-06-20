package Jobs;

import Config.DBconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class flushbooklesauthorsjob {

    public static boolean flushAuthors() {
        String delete = "DELETE FROM `authors` WHERE authorId NOT IN(SELECT authorId FROM books); ";

        try (Connection conn = DBconn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(delete)) {
            int rows = stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error deactivating session: " + e.getMessage());
        }

        return false;
    }
}
