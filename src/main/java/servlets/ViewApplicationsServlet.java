package servlets;
import dao.JobDAO;
import model.Application;
import model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import java.util.List;

@WebServlet("/viewApplications")
public class ViewApplicationsServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Ensure the user is logged in and is an Employer
        if (user == null || !user.getRole().equals("Employer")) {
            response.sendRedirect("jsp/login.jsp?msg=Please login as an Employer to view applications.");
            return;
        }

        int employerId = user.getId(); // Get employer ID from session
        List<Application> applications = JobDAO.getApplicationsByEmployer(employerId);

        request.setAttribute("applications", applications);
        request.getRequestDispatcher("jsp/viewApplications.jsp").forward(request, response);
    }
}
