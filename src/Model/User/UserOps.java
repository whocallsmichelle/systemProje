package Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Config.DBconn;

public  class UserOps {

    public static void deleteUser(User user) {
        // Logic to delete a user
    }
    public static void updateUser(User user) {
        // Logic to update user information
    }
    public static User getUser(int userId) {
        Connection connection = DBconn.getConnection();


        return new User(); // Placeholder return value
    }

    public static boolean createUser(User user) {
        String query = "INSERT INTO userinfo (username, password, user_type) VALUES (?, ?, ?)";

        try (Connection conn = DBconn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, String.valueOf(user.getPassword().hashCode())); // Simple hash
            stmt.setInt(3, user.getUserType());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("User created successfully.");
                return true;
            } else {
                System.out.println("Failed to create user.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error in createUser: " + e.getMessage());
        }
        return false;
    }

    public static User login(String username, String password) {
        String query = "SELECT userID,username,password,userType FROM userinfo WHERE username = ?";

        try (Connection conn = DBconn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                String inputHashed = String.valueOf(password.hashCode());

                 if (storedPassword.equals(inputHashed)) {
                    User newUser = new User(rs.getInt("userID"),
                                            rs.getString("username"),
                                            rs.getString("password"),
                                            rs.getInt("userType"));

                    return newUser; // Return user ID on successful login
                } else {
                    System.out.println("Incorrect password for user: " + username);
                    return new User(); // Return empty user on failed login
                 }
            }

        } catch (SQLException e) {
            System.out.println("Error in login: " + e.getMessage());
        }

        return new User();  // Return empty user if no match found or error occurred;
    }



}
