package com.example.part20;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gradingsystem";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "admin"; // Replace with your MySQL password

    private static DatabaseManager instance;
    private Connection connection;

    private DatabaseManager() {
        // Private constructor to prevent direct instantiation
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public static Connection getConnection() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            instance.connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            return instance.connection;
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database.");
            e.printStackTrace();
        }
        return null;
    }
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
