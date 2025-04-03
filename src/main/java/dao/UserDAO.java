package dao;

import model.User;
import utils.DBConnection;
import java.sql.*;

public class UserDAO {

	public static boolean registerUser(User user) {
		boolean status = false;
		try {
			Connection con = DBConnection.getConnection();
			String registerQuery = "INSERT INTO job_portal.users (name, email, password, role) VALUES (?,?,?,?);";
			PreparedStatement prs = con.prepareStatement(registerQuery);
			prs.setString(1, user.getName());
			prs.setString(2, user.getEmail());
			prs.setString(3, user.getPassword());
			prs.setString(4, user.getRole());
			int rowsInserted = prs.executeUpdate();
			if (rowsInserted > 0) {
				status = true;
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static User validateUser(String email, String password) {
		User user = null;
		try {
			Connection con = DBConnection.getConnection();
			String checkUser = "SELECT id, name, email, password, role FROM job_portal.users WHERE email = ? AND password = ? AND is_active = ?;";
			PreparedStatement prs = con.prepareStatement(checkUser);
			prs.setString(1, email);
			prs.setString(2, password);
			prs.setInt(3, 1);
			ResultSet rs = prs.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setEmail(rs.getString("email"));
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return user;
	}

}
