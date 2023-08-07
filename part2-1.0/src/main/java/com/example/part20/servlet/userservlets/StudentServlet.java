package com.example.part20.servlet.userservlets;


import com.example.part20.dao.CoursesOperations;
import com.example.part20.dao.GradeOperations;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@WebServlet(urlPatterns = "/student.do")
public class StudentServlet extends HttpServlet {
    private GradeOperations gradeOperations;
private CoursesOperations coursesOperations;
    @Override
    public void init() throws ServletException {
        gradeOperations = new GradeOperations();
        coursesOperations=new CoursesOperations();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("scores".equals(action)) {
            // Handle "Show My Grades" form
            int userIdInt = (Integer) request.getSession().getAttribute("ID");
            List<String> studentGrades = null; // Implement this method to fetch student grades
            try {
                studentGrades = gradeOperations.showGrades(String.valueOf(userIdInt));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("studentGrades", studentGrades);
            request.getRequestDispatcher("studentProfile.jsp").forward(request, response);
        } else if ("stat".equals(action)) {
            // Handle "Show Statistics" form
            String courseName = request.getParameter("courseName");
            Map<String,Double> statistics = null; // Implement this method to fetch course statistics
            try {
                statistics = coursesOperations.showCourse(courseName);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("statistics", statistics);
            request.getRequestDispatcher("scores.jsp").forward(request, response);
        } else {
            // Handle other cases or unexpected actions
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidate the session to sign out the user
        HttpSession session = request.getSession();
        session.invalidate();

        // Redirect the user to the login page
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

}
