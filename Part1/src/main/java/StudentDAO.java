import at.favre.lib.crypto.bcrypt.BCrypt;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.rank.Median;

import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {

    Connection connection;

    public StudentDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");
        connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/gradingsystem",
                        "root", "admin");
        System.out.println("Database connected");
    }

    public boolean testCredentials(String id, String passwd) {
        String sql = "SELECT studentPass FROM credentials WHERE studentId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String hashedPasswordFromDB = resultSet.getString(1);
                    BCrypt.Result result = BCrypt.verifyer().verify(passwd.toCharArray(), hashedPasswordFromDB);

                    if (result.verified) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            // Handle any database-related exceptions here
            e.printStackTrace();
        }

        return false;
    }

    public String showGrades(String id) throws SQLException {
        try {
            Statement statement = connection.createStatement();

            String sql = "SELECT scores.score , course.courseName\n" +
                    "FROM scores\n" +
                    "JOIN credentials ON credentials.studentId=scores.studentId \n" +
                    "JOIN course ON course.courseID =scores.courseID\n" +
                    "WHERE\n" +
                    "scores.studentId =" + id;

            ResultSet resultSet = statement.executeQuery(sql);
            String message = "";
            while (resultSet.next()) {
                message += " Course: " +
                        resultSet.getString("courseName") +
                        " Score: " + resultSet.getString("score");
            }
            return message;
        } catch (SQLException e) {
            // Handle any database-related exceptions here
            e.printStackTrace();
            return null;
        }
    }

    public String showCourse(String courseName) throws SQLException {
        try {
            Statement statement = connection.createStatement();

            String sql = "SELECT scores.score\n" +
                    "FROM scores\n" +
                    "JOIN credentials ON credentials.studentId=scores.studentId \n" +
                    "JOIN course ON course.courseID =scores.courseID\n" +
                    "WHERE\n" +
                    "    course.courseName='" + courseName + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            ArrayList<Double> scores = new ArrayList<>();
            while (resultSet.next()) {
                scores.add(Double.parseDouble(resultSet.getString("score")));
            }
            double[] scoresArray = scores.stream().mapToDouble(s -> s).sorted().toArray();
            String message = "Median: " + getMedian(scoresArray)
                    + " Mean: " + getMean(scoresArray) +
                    " Min: " + getMin(scoresArray) +
                    " Max: " + getMax(scoresArray);

            return message;
        } catch (SQLException e) {
            // Handle any database-related exceptions here
            e.printStackTrace();
            return null;
        }
    }

    public double getMean(double[] array) {
        Mean mean = new Mean();
        return mean.evaluate(array);
    }

    public double getMedian(double[] array) {
        Median median = new Median();
        return median.evaluate(array);
    }

    public double getMax(double[] array) {
        return array[array.length - 1];
    }

    public double getMin(double[] array) {
        return array[0];
    }
}
