package com.example.part20.servlet;

import com.example.part20.auth.AuthManager;
import com.example.part20.dao.UserOperations;
import com.example.part20.models.User;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
    private UserOperations userOperations;

    public void init() {
        userOperations = new UserOperations();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getRequestDispatcher("login.jsp").forward(
                request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("name");
        String password = request.getParameter("password");

        AuthManager authManager = new AuthManager();
        User user = null;
        try {
            user = authManager.signIn(username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("ID", user.getId());

            switch (user.getRank().toLowerCase()) {
                case "instructor":
                    request.setAttribute("User",user.getUsername());
                    response.sendRedirect(request.getContextPath() + "/InstructorPortal.jsp");
                    break;
                case "student":
                    request.setAttribute("User",user.getUsername());
                    response.sendRedirect(request.getContextPath() + "/studentProfile.jsp");
                    break;
                case "admin":
                    request.setAttribute("User",user.getUsername());

                    // Redirect to the student portal
                    response.sendRedirect(request.getContextPath() + "/AdminPortal.jsp");
                    break;
                // Add more cases for different roles if needed
                default:
                    // Handle any other roles or unexpected cases
                    request.setAttribute("User",user.getUsername());
                    response.sendRedirect(request.getContextPath() + "/studentProfile.jsp");
                    break;
            }
        } else {
            request.setAttribute("errorMessage", "Invalid Credentials!!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

}
