package servlets;

import dao.JobDAO;
import model.Job;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import java.util.List;

@WebServlet("/jobList")
public class JobListServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Job> jobs = JobDAO.getAllJobs(); 
        request.setAttribute("jobs", jobs);   // Pass jobs to JSP
        request.getRequestDispatcher("jsp/jobList.jsp").forward(request, response);
    }
}
