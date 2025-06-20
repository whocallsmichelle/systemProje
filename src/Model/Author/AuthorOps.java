package Model.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Config.DBconn;

public class AuthorOps {

    public static Author searchAuthor(String name) {
        String query = "SELECT * FROM authors WHERE CONCAT(name, ' ', surname) LIKE ? limit 1";
        try (Connection conn = DBconn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + name + "%");

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Assuming Author has this constructor
                return new Author(
                        rs.getInt("authorid"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("website")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error in searchAuthor: " + e.getMessage());
        }

        return null;
    }
}
