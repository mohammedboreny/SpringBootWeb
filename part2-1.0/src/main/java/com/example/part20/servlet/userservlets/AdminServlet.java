package com.example.part20.servlet.userservlets;

import com.example.part20.dao.CoursesOperations;
import com.example.part20.dao.GradeOperations;
import com.example.part20.dao.UserOperations;
import com.example.part20.models.Course;
import com.example.part20.models.Grade;
import com.example.part20.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private CoursesOperations courseDAO;
    private GradeOperations gradeDAO;
    private UserOperations userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize your DAO classes here
        courseDAO = new CoursesOperations();
        gradeDAO = new GradeOperations();
        userDAO = new UserOperations();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("viewCourses".equals(action)) {
            // Fetch all courses
            List<Course> courses = null;
            try {
                courses = courseDAO.getAllCourses();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("courses", courses);
            request.getRequestDispatcher("/viewCourses.jsp").forward(request, response);
        } else if ("viewGrades".equals(action)) {
            // Fetch all grades
            Map<String, String> grades = null;
            try {
                grades = gradeDAO.showAllGradesWithUserName();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("grades", grades);
            request.getRequestDispatcher("/viewGrades.jsp").forward(request, response);
        } else if ("viewUsers".equals(action)) {
            // Fetch all users
            List<User> users = null;
            try {
                users = userDAO.getAllUsers();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("users", users);
            request.getRequestDispatcher("/viewUsers.jsp").forward(request, response);
        } else {
            // Handle other cases or unexpected actions
            // Redirect or display an error page
            response.sendRedirect(request.getContextPath() + "/adminDashboard.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("create".equals(action)) {
            // Handle POST requests to create new data on the server
            // ...
        } else if ("update".equals(action)) {
            // Handle PUT requests to update existing data on the server
            // ...
        } else {
            // Handle other cases or unexpected actions
            // Redirect or display an error page
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle PUT requests to update existing data on the server
        // ...
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle DELETE requests to delete data from the server
        // ...
    }
}
