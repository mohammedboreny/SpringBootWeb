package com.example.part20.dao;

import com.example.part20.DatabaseManager;
import com.example.part20.models.Course;
import com.example.part20.models.Grade;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradeOperations {
    private DatabaseManager databaseManager;

    public GradeOperations() {
        databaseManager = DatabaseManager.getInstance();
    }

    public Map<String, String> showAllGradesWithUserName() throws SQLException {
        String sql = "SELECT scores.score, course.courseName, credentials.name " +
                "FROM scores " +
                "JOIN credentials ON credentials.studentId = scores.studentId " +
                "JOIN course ON course.courseID = scores.courseID " +
                "JOIN credentials ON credentials.studentId = credentials.studentId";

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet resultSet = pstmt.executeQuery()) {

            Map<String, String> gradesMap = new HashMap<>();
            while (resultSet.next()) {
                String courseName = resultSet.getString("courseName");
                String score = resultSet.getString("score");
                String username = resultSet.getString("username");
                String message = "Course: " + courseName + " Score: " + score + " Username: " + username;
                gradesMap.put(courseName, message);
            }
            return gradesMap;
        }
    }

    public List<String> showGrades(String id) throws SQLException {
        String sql = "SELECT scores.score, course.courseName " +
                "FROM scores " +
                "JOIN credentials ON credentials.studentId = scores.studentId " +
                "JOIN course ON course.courseID = scores.courseID " +
                "WHERE scores.studentId = ?";

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, id);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                List<String> gradesList = new ArrayList<>();
                while (resultSet.next()) {
                    String courseName = resultSet.getString("courseName");
                    String score = resultSet.getString("score");
                    String message = "Course: " + courseName + " Score: " + score;
                    gradesList.add(message);
                }
                return gradesList;
            }
        }
    }






}
