package servlets;

import model.User;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.*;

public class RegisterServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		
		User user = new User(name, email, password, role);
		boolean userCreated = UserDAO.registerUser(user);
		
		if(userCreated) {
			response.sendRedirect("jsp/login.jsp?msg=Registration Successful! Please login.");
		}
		else {
			response.sendRedirect("jsp/register.jsp?msg=Error in Registration!");
		}
	}
}
