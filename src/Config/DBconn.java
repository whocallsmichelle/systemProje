package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconn {
    private static final String URL = "jdbc:mysql://localhost:3333/project";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Get DB connection
    public static Connection getConnection() {
        try {
            // Optional for newer JDBC versions, required in older
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            return null;
        }
    }

    public static void main(String[] args) {
        // Test the connection
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("Database connection successful!");
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Failed to close the connection: " + e.getMessage());
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
