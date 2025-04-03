package dao;

import model.Job;
import model.Application;
import utils.DBConnection;
import java.sql.*;
import java.util.*;


public class JobDAO {

	public static boolean postJob(Job job) {
		boolean status = false;
		try {
			Connection con = DBConnection.getConnection();
			String query = "INSERT INTO job_portal.jobs(employerId, title, description, location, salary, company) VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement prs = con.prepareStatement(query);
			prs.setInt(1, job.getEmployerId());
			prs.setString(2, job.getTitle());
			prs.setString(3, job.getDescription());
			prs.setString(4, job.getlocation());
			prs.setDouble(5, job.getSalary());
			prs.setString(6, job.getCompany());
			int rowsInserted = prs.executeUpdate();
			if(rowsInserted > 0) {
				status = true;
			}
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static List<Job> getAllJobs() {
		List<Job> jobs = new ArrayList<>();
		try {
			Connection con = DBConnection.getConnection();
			System.out.println("Fetching jobs from database...");
			String query = "SELECT jobId, employerId, title, description, location, salary, company FROM job_portal.jobs;";
			PreparedStatement prs = con.prepareStatement(query);
			ResultSet rs = prs.executeQuery();
			while (rs.next()) {
				Job job = new Job();
				job.setJobId(rs.getInt("jobId"));
				job.setEmployerId(rs.getInt("employerId"));
				job.setDescription(rs.getString("description"));
				job.setLocation(rs.getString("location"));
				job.setTitle(rs.getString("title"));
				job.setSalary(rs.getDouble("salary"));
				job.setCompany(rs.getString("company"));
				jobs.add(job);
			}
			con.close();
			System.out.println("Jobs Retrieved: " + jobs.size());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return jobs;
	}
	
	public static boolean applyForJob(Application application) {
	    boolean status = false;
	    try {
	        Connection conn = DBConnection.getConnection();
	        
	        // Check if the job seeker has already applied
	        String checkQuery = "SELECT * FROM job_portal.applications WHERE jobSeekerId = ? AND jobId = ?";
	        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
	        checkStmt.setInt(1, application.getJobSeekerId());
	        checkStmt.setInt(2, application.getJobId());
	        ResultSet rs = checkStmt.executeQuery();
	        
	        if (rs.next()) {
	            conn.close();
	            return false; // Already applied
	        }

	        // Insert application if not already applied
	        String sql = "INSERT INTO job_portal.applications (jobSeekerId, jobId, status) VALUES (?, ?, ?)";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, application.getJobSeekerId());
	        stmt.setInt(2, application.getJobId());
	        stmt.setString(3, application.getStatus());

	        int rowsInserted = stmt.executeUpdate();
	        if (rowsInserted > 0) {
	            status = true;
	        }
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return status;
	}
	
	public static List<Application> getApplicationsByEmployer(int employerId) {
	    List<Application> applications = new ArrayList<>();
	    try {
	        Connection conn = DBConnection.getConnection();

	        String sql = "SELECT a.applicationId, a.jobSeekerId, a.jobId, a.status, " +
	                     "j.title, u.name AS jobSeekerName " +
	                     "FROM job_portal.applications a " +
	                     "JOIN job_portal.jobs j ON a.jobId = j.jobId " +
	                     "JOIN job_portal.users u ON a.jobSeekerId = u.id " +
	                     "WHERE j.employerId = ?";

	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, employerId);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            Application application = new Application();
	            application.setApplicationId(rs.getInt("applicationId"));
	            application.setJobSeekerId(rs.getInt("jobSeekerId"));
	            application.setJobId(rs.getInt("jobId"));
	            application.setStatus(rs.getString("status"));
	            application.setJobTitle(rs.getString("title"));
	            application.setJobSeekerName(rs.getString("jobSeekerName"));

	            applications.add(application);
	        }

	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return applications;
	}

	
	public static boolean updateApplicationStatus(int applicationId, String status) {
	    boolean result = false;
	    try {
	        Connection conn = DBConnection.getConnection();
	        String sql = "UPDATE job_portal.applications SET status = ? WHERE applicationId = ?";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setString(1, status);
	        stmt.setInt(2, applicationId);

	        int rowsUpdated = stmt.executeUpdate();
	        if (rowsUpdated > 0) {
	            result = true;
	        }
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}


	public static List<Application> getApplicationsByJobSeeker(int jobSeekerId) {
	    List<Application> applications = new ArrayList<>();
	    try {
	        Connection conn = DBConnection.getConnection();

	        String sql = "SELECT a.applicationId, a.jobId, a.status, " +
	                     "j.title, j.company, j.location, j.description, j.salary " +
	                     "FROM job_portal.applications a " +
	                     "JOIN job_portal.jobs j ON a.jobId = j.jobId " +
	                     "WHERE a.jobSeekerId = ?";

	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, jobSeekerId);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            Application application = new Application();
	            application.setApplicationId(rs.getInt("applicationId"));
	            application.setJobId(rs.getInt("jobId"));
	            application.setStatus(rs.getString("status"));
	            application.setJobTitle(rs.getString("title"));
	            application.setLocation(rs.getString("location"));
	            application.setCompany(rs.getString("company"));
	            application.setDescription(rs.getString("description"));
	            application.setSalary(rs.getDouble("salary"));
	            applications.add(application);
	        }

	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return applications;
	}

}
