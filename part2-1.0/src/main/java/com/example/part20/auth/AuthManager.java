package com.example.part20.auth;

import com.example.part20.DatabaseManager;
import com.example.part20.models.User;
import com.example.part20.validate.Validation;
import com.mysql.cj.Session;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpSession;
import java.sql.*;

public class AuthManager {
    private DatabaseManager databaseManager;

    public AuthManager() {
        databaseManager = DatabaseManager.getInstance();
    }

    public int signUp(String username, String password, String role) throws SQLException {
        if (!Validation.isValidUsername(username) || !Validation.isValidPassword(password)) {
            return -1; // Invalid user input
        }

        if (isUsernameOrEmailTaken(username)) {
            return -1; // Username or email is already taken
        }

        String hashedPassword = hashPassword(password); // Hash the password before saving
        String query = "INSERT INTO credentials (studentPass, `rank`, name) VALUES (?, ?, ?)";

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, hashedPassword);
            pstmt.setString(2, role);
            pstmt.setString(3, username);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Inserting user failed, no rows affected.");
            }

            // Retrieve the automatically generated ID
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Inserting user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public User signIn(String name, String password) throws SQLException {
        String query = "SELECT * FROM credentials WHERE `name` = ?";

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, name);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("studentId");
                    String username = rs.getString("name");
                    String hashedPassword = rs.getString("studentPass");
                    String rank = rs.getString("rank");

                    if (BCrypt.checkpw(password, hashedPassword)) {
                        // Passwords match, create and return the User object
                        User user = new User(username, password, rank);
                        user.setId(id);
                        return user;
                    } else {
                        // Passwords do not match, return null to indicate sign-in failure
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return null; // Return null if the user with the provided credentials is not found
    }



    private boolean isUsernameOrEmailTaken(String username) throws SQLException {
        String query = "SELECT * FROM credentials WHERE name = ? ";

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // If there is a result, username or email is already taken
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private boolean verifyPassword(String rawPassword, String hashedPassword) {
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }
}
