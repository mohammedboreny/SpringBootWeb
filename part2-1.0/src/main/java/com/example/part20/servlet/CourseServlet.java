package com.example.part20.servlet;

import com.example.part20.dao.CoursesOperations;
import com.example.part20.models.Course;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CourseServlet extends HttpServlet {
    private CoursesOperations coursesOperations;

    @Override
    public void init() throws ServletException {
        super.init();
        coursesOperations = new CoursesOperations();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if (action == null) {
                action = "list"; // Default action is to list all courses
            }

            switch (action) {
                case "list" -> listCourses(request, response);
                default -> listCourses(request, response); // Default to listing all courses
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if (action == null) {
                action = "list"; // Default action is to list all courses
            }

            switch (action) {
                case "add" -> addCourse(request, response);
                case "update" -> updateCourse(request, response);
                case "delete" -> deleteCourse(request, response);
                default -> listCourses(request, response); // Default to listing all courses
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void listCourses(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Course> courses = coursesOperations.getAllCourses();
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/courses.jsp").forward(request, response);
    }

    private void addCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
               int courseId = Integer.parseInt(request.getParameter("id"));

        String name = request.getParameter("name");
        Course course = new Course(courseId,name);
        coursesOperations.insertCourse(course);
        response.sendRedirect(request.getContextPath() + "/course?action=list");
    }

   private void updateCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
       int courseId = Integer.parseInt(request.getParameter("id"));
       String name = request.getParameter("name");
       Course course = new Course(courseId, name); // Make sure to pass courseId to the Course constructor
       coursesOperations.updateCourse(course);
       response.sendRedirect(request.getContextPath() + "/course?action=list");
   }

    private void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int courseId = Integer.parseInt(request.getParameter("id"));
        coursesOperations.deleteCourse(courseId);
        response.sendRedirect(request.getContextPath() + "/course?action=list");
    }

    @Override
    public void destroy() {
        super.destroy();
        // Clean up resources if needed
    }
}
