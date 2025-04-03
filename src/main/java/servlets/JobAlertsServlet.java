package servlets;

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

@WebServlet("/jobAlerts")
public class JobAlertsServlet extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String email = request.getParameter("email");
		String keywords = request.getParameter("keywords");
		String location = request.getParameter("location");
		
		try(Connection con = DBConnection.getConnection()){
			String query = "INSERT INTO job_portal.job_alerts(email, keywords, location) VALUES (?, ?, ?);";
			PreparedStatement prs = con.prepareStatement(query);
			prs.setString(1, email);
			prs.setString(2, keywords);
			prs.setString(3, location);
			prs.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("jsp/jobAlertsConfirmation.jsp");
	}
}
