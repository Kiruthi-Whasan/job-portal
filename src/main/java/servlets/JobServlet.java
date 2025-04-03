package servlets;

import model.Job;
import utils.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

@WebServlet("/JobServlet")
public class JobServlet extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String title = request.getParameter("title");
		String company = request.getParameter("company");
		String location = request.getParameter("location");
		
		List<Job> jobList = new ArrayList<>();
		
		try(Connection con = DBConnection.getConnection()){
			String query ="SELECT title, company, description, location, salary FROM job_portal.jobs WHERE 1=1 ";
			
			if(title!=null && !title.isEmpty()) {
				query+= " AND title LIKE ?";
			}
			if(company!=null && !company.isEmpty()) {
				query+= " AND company LIKE ?";
			}
			if(location!=null && !location.isEmpty()) {
				query+= " AND location LIKE ?";
			}
			
			PreparedStatement prs = con.prepareStatement(query);
			int index = 1;
			if(title!=null && !title.isEmpty()) {
				prs.setString(index++, "%" + title + "%");
			}
			if(company!=null && !company.isEmpty()) {
				prs.setString(index++, "%" + company + "%");
			}
			if(location!=null && !location.isEmpty()) {
				prs.setString(index++, "%" + location + "%");
			}
			ResultSet rs = prs.executeQuery();
			while(rs.next()) {
				Job job = new Job();
				job.setCompany(rs.getString("company"));
				job.setTitle(rs.getString("title"));
				job.setLocation(rs.getString("location"));
				job.setSalary(rs.getDouble("salary"));
				job.setDescription(rs.getString("description"));
				jobList.add(job);
			}
				
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("jobs", jobList);
        request.getRequestDispatcher("jsp/jobList.jsp").forward(request, response);
		
	}
}
