package servlets;

import model.Job;
import model.User;
import dao.JobDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;

public class PostJobServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null || !user.getRole().equals("Employer")) {
			response.sendRedirect("jsp/dashboard.jsp?msg=Unauthorized Access");
			return;
		}
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String location = request.getParameter("location");
		double salary = Double.parseDouble(request.getParameter("salary"));
		String company = request.getParameter("company");

		Job job = new Job(user.getId(), title, description, location, salary, company);
		boolean success = JobDAO.postJob(job);

		if (success) {

			request.setAttribute("title", title);
			request.setAttribute("location", location);
			request.getRequestDispatcher("NewJobPostedServlet").forward(request, response);
		} else {
			response.sendRedirect("jsp/dashboard.jsp?msg=Error posting Job!");
		}
	}
}
