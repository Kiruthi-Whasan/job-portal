package servlets;

import utils.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/WithdrawApplicationServlet")
public class WithdrawApplicationStatus extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int applicationId = Integer.parseInt(request.getParameter("applicationId"));

        try (Connection con = DBConnection.getConnection()) {
            String sql = "UPDATE job_portal.applications SET status = 'Withdrawn' WHERE applicationId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, applicationId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirect back to the job seeker dashboard
//        response.sendRedirect("jsp/jobSeekerDashboard.jsp");
        response.sendRedirect(request.getContextPath() + "/jobSeekerDashboard");
    }
}
