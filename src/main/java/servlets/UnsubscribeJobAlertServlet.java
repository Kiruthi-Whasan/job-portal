package servlets;

import utils.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.*;

@WebServlet("/UnsubscribeJobAlertServlet")
public class UnsubscribeJobAlertServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        try (Connection con = DBConnection.getConnection()) {
            String sql = "DELETE FROM job_portal.job_alerts WHERE email = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                response.sendRedirect("jsp/unsubscribeConfirmation.jsp?msg=Unsubscribed successfully!");
            } else {
                response.sendRedirect("jsp/unsubscribeConfirmation.jsp?msg=Email not found in subscriptions.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("jsp/unsubscribeConfirmation.jsp?msg=Error occurred. Try again.");
        }
    }
}
