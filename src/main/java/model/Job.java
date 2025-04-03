package model;

public class Job {
	private int jobId;
	private int employerId;
	private String title;
	private String description;
	private String location;
	private double salary;
	private String company;
	
	public Job() {
		
	}
	
	public Job(int employerId, String title, String description, String location, double salary, String company){
		this.employerId = employerId;
		this.description = description;
		this.location = location;
		this.salary = salary;
		this.title = title;
		this.company = company;
	}
	
	public int getJobId() {
		return this.jobId;
	}
	
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	
	public int getEmployerId() {
		return this.employerId;
	}
	
	public void setEmployerId(int employerId) {
		this.employerId = employerId;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getlocation() {
		return this.location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public double getSalary() {
		return this.salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	
	public String getCompany() {
		return this.company;
	}
	
}
