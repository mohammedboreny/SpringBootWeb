package com.example.part20.servlet;

import com.example.part20.auth.AuthManager;
import com.example.part20.dao.UserOperations;
import com.example.part20.models.User;
import com.mysql.cj.Session;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

@WebServlet("/signup.do")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("name");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Validate the user inputs
        if (username == null  || password == null || role == null
                || username.isEmpty()  || password.isEmpty() || role.isEmpty()) {
            request.setAttribute("errorMessage", "Please fill in all the fields.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Create an instance of AuthManager
        AuthManager authManager = new AuthManager();

        try {
            int id =authManager.signUp(username, password, role);
            // Use the signUp method from AuthManager to handle sign-up logic
            if (id!=-1) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", id);
                request.setAttribute("successMessage", "User created successfully. You can now sign in.");
                request.getRequestDispatcher("studentProfile.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "User creation failed. Please check your details.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error while creating the user. Please try again later.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
