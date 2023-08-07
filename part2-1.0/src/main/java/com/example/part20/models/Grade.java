package com.example.part20.models;

public class Grade {
    private int userId;
    private int courseId;
    private String grade;
    private String courseName; // Add this property for course name

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Grade(int userId, int courseId, String grade) {
        this.userId = userId;
        this.courseId = courseId;
        this.grade = grade;
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }



// Getters and Setters (You can generate them automatically or define them manually)
}
