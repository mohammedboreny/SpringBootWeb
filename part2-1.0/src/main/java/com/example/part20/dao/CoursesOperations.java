package com.example.part20.dao;

import com.example.part20.DatabaseManager;
import com.example.part20.models.Course;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.rank.Median;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoursesOperations {
    private DatabaseManager databaseManager;

    public CoursesOperations() {
        databaseManager = DatabaseManager.getInstance();
    }





    public void insertCourse(Course course) {
        String sql = "INSERT INTO course (courseName) VALUE (?)";

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, course.getName());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQLException, e.g., display an error message or log the error
        }
    }

    public void updateCourse(Course course) {
        String sql = "UPDATE course SET courseName = ? WHERE courseId = ?";

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, course.getName());
            pstmt.setInt(2, course.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQLException, e.g., display an error message or log the error
        }
    }

    public void deleteCourse(int courseId) throws SQLException {
        String query = "DELETE FROM course WHERE courseID = ?";

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, courseId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Course> getAllCourses() throws SQLException {
        List<Course> courseList = new ArrayList<>();
        String query = "SELECT * FROM course";

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int courseId = rs.getInt("courseID");
                String courseName = rs.getString("courseName");
                // Add more course properties as needed

                Course course = new Course(courseId, courseName);
                courseList.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return courseList;
    }

    public  Map<String,Double> showCourse(String courseName) throws SQLException {
        String query = "SELECT scores.score FROM scores " +
                "JOIN credentials ON credentials.studentId = scores.studentId " +
                "JOIN course ON course.courseID = scores.courseID " +
                "WHERE course.courseName = ?";

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, courseName);
            try (ResultSet rs = pstmt.executeQuery()) {
                ArrayList<Double> scores = new ArrayList<>();
                while (rs.next()) {
                    scores.add(Double.parseDouble(rs.getString("score")));
                }

                double[] scoresArray = scores.stream().mapToDouble(s -> s).sorted().toArray();

                Map<String,Double> stats=new HashMap();
                stats.put("Median",getMedian(scoresArray));
                stats.put("Mean",getMean(scoresArray));
                stats.put("Min",getMin(scoresArray));
                stats.put("Max",getMax(scoresArray));

                return stats;
            }
        }
    }
    public double getMean(double[] array){
        Mean mean = new Mean();
        return mean.evaluate(array);


    }
    public double getMedian(double[] array){
        Median median = new Median();
        return  median.evaluate(array);
    }
    public double getMax(double[] array){
        return array[array.length-1];
    }
    public double getMin(double[] array)
    {
        return array[0];
    }


    // Other methods specific to the 'users' table
}
