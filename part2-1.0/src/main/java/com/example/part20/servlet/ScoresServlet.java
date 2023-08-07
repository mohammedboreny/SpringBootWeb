//package com.example.part20.servlet;
//
//import com.example.part20.dao.GradeOperations;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.sql.SQLException;
//
//@WebServlet(urlPatterns = "/scores.do")
//public class ScoresServlet extends HttpServlet {
//    GradeOperations gradeOperations;
//
//    @Override
//    public void init() throws ServletException {
//        gradeOperations = new GradeOperations();
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        String button = request.getParameter("button");
//        if (button != null && button.equals("scores")) {
//            // Handle the form submission to show grades
//            HttpSession session = request.getSession();
//            String userId = (String) session.getAttribute("userId");
//
//            try {
//                String grades = gradeOperations.showGrades(userId);
////                String courses = gradeOperations.showCourses(userId);
//
//                request.setAttribute("grades", grades);
////                request.setAttribute("courses", courses);
//
//                request.getRequestDispatcher("studentProfile.jsp").forward(request, response);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } else if (button != null && button.equals("stats")) {
//            // Handle the form submission to show statistics
//            String courseName = request.getParameter("courseName");
//            try {
//                String stats = gradeOperations.showGrades(courseName);
//                request.setAttribute("stats", stats);
//                request.getRequestDispatcher("scores.jsp").forward(request, response);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } else {
//            // Handle other cases if needed
//        }
//    }
//
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        HttpSession session = request.getSession();
//
//        String userId =(String) session.getAttribute("userId");
//
//        try {
//            String grades = gradeOperations.showGrades(userId);
////            String courses = gradeOperations.showGrades(userId);
//
//            request.setAttribute("grades", grades);
////            request.setAttribute("courses", courses);
//
//            request.getRequestDispatcher("studentProfile.jsp").forward(request, response);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
