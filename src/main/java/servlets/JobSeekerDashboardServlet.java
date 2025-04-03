package servlets;
import dao.JobDAO;
import model.User;
import model.Application;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/jobSeekerDashboard")
public class JobSeekerDashboardServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Ensure the user is logged in and is a Job Seeker
        if (user == null || !user.getRole().equals("JobSeeker")) {
            response.sendRedirect("login.jsp?msg=Please login as a Job Seeker to view applications.");
            return;
        }

        int jobSeekerId = user.getId(); // Get job seeker ID from session
        List<Application> applications = JobDAO.getApplicationsByJobSeeker(jobSeekerId);

        request.setAttribute("applications", applications);
        request.getRequestDispatcher("jsp/jobSeekerDashboard.jsp").forward(request, response);
    }
}
