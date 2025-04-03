package servlets;

import utils.DBConnection;
import utils.EmailUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/NewJobPostedServlet")
public class NewJobPostedServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jobTitle = request.getParameter("title");
        String location = request.getParameter("location");
        String company = request.getParameter("company");
        String salary = request.getParameter("salary");

        try (Connection conn = DBConnection.getConnection()) {
            // Get users subscribed to matching job alerts
            String sql = "SELECT email FROM job_portal.job_alerts WHERE (keywords LIKE ? OR location LIKE ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + jobTitle + "%");
            stmt.setString(2, "%" + location + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String email = rs.getString("email");

                // Send job alert email
                String subject = "New Job Alert: " + jobTitle;
                String content = "A new job matching your preferences has been posted:\n\n"
                               + "Job Title: " + jobTitle + "\n"
                               + "Company: " + company + "\n"
                               + "Location: " + location + "\n"
                               + "Salary: " + salary + "\n\n"
                               + "Apply now on our job portal!";

                EmailUtil.sendEmail(email, subject, content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("jobList");
    }
}
