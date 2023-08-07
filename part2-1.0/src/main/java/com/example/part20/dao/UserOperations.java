package com.example.part20.dao;

import com.example.part20.DatabaseManager;
import com.example.part20.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserOperations {
    private DatabaseManager databaseManager;

    public UserOperations() {
        databaseManager = DatabaseManager.getInstance();
    }

    public void insertUser(User user) throws SQLException {
        String query = "INSERT INTO credentials (studentPass,rank,name) VALUES (?, ?, ?)";

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(3, user.getUsername());
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getRank());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteUser(int userId) throws SQLException {
        String query = "DELETE FROM credentials WHERE studentId = ?";

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, userId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateUser(User user,int user_id) throws SQLException {
        String query = "UPDATE credentials SET name =?  , studentPass=? ,`rank`=? WHERE studentId = ?";

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getRank());
            pstmt.setInt(4, user_id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM credentials";

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String username = rs.getString("name");
                String password = rs.getString("studentPass");
                String rule = rs.getString("rank");

                User user = new User( username, password, rule);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return userList;
    }

    public User getUserByUsername(String username) throws SQLException {
        String query = "SELECT * FROM credentials WHERE name = ?";
        User user = null;

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String email = rs.getString("email");
                    String password = rs.getString("studentPass");
                    String rule = rs.getString("rule");

                    user = new User( email, password, rule);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return user;
    }

    public User getUserById(String userId) {
        String query = "SELECT * FROM credentials WHERE studentId = ?";
        User user = null;

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String username = rs.getString("name");
                    String password = rs.getString("studentPass");
                    String rule = rs.getString("rank");

                    user = new User(  username, password, rule);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    public User getUserByEmail(String rank) {
        String query = "SELECT * FROM credentials WHERE `rank` = ?";
        User user = null;

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, rank);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String username = rs.getString("name");
                    String password = rs.getString("studentPass");
                    String rule = rs.getString("rank");

                    user = new User( username, password, rule);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }


}
