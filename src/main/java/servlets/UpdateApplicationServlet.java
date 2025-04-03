package servlets;
import dao.JobDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateApplicationStatus")
public class UpdateApplicationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int applicationId = Integer.parseInt(request.getParameter("applicationId"));
        String newStatus = request.getParameter("status");

        boolean success = JobDAO.updateApplicationStatus(applicationId, newStatus);

        if (success) {
            response.sendRedirect("viewApplications?msg=Application status updated successfully!");
        } else {
            response.sendRedirect("viewApplications?msg=Error updating status.");
        }
    }
}