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

import java.io.*;

@WebServlet("/applyJob")
public class ApplyJobServlet extends HttpServlet{
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession();
	        User user = (User) session.getAttribute("user");

	        // Ensure the user is logged in and is a job seeker
	        if (user == null || !user.getRole().equals("JobSeeker")) {
	            response.sendRedirect("jsp/jobList.jsp?msg=Please login as a Job Seeker to apply.");
	            return;
	        }

	        int jobId = Integer.parseInt(request.getParameter("jobId"));
	        int jobSeekerId = user.getId();

	        Application application = new Application(jobSeekerId, jobId, "Pending"); // Default status: Pending
	        boolean success = JobDAO.applyForJob(application);

	        if (success) {
	            response.sendRedirect("jsp/jobList.jsp?msg=Application submitted successfully!");
	        } else {
	            response.sendRedirect("jsp/jobList.jsp?msg=Error submitting application.");
	        }
	    }
}
