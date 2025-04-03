<%@ page import="model.Job"%>
<%@ page import="model.User"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>Job Listings</title>
</head>
<body>
	<h2>Search for Jobs</h2>
	<form action="${pageContext.request.contextPath}/JobServlet"
		method="POST">
		<input type="text" name="title" placeholder="Job Title"> <input
			type="text" name="company" placeholder="Company Name"> <input
			type="text" name="location" placeholder="Location">
		<button type="submit">Search</button>
	</form>

	<%
	User user = (User) session.getAttribute("user");
	if (user == null) {
		response.sendRedirect("login.jsp?msg=Please login first.");
		return;
	}
	%>

	<h2>Available Jobs</h2>
	<table border="1">
		<tr>
			<th>Job Title</th>
			<th>Company Name</th>
			<th>Description</th>
			<th>Location</th>
			<th>Salary</th>
			<%
			if ("JobSeeker".equals(user.getRole())) {
			%>
			<th>Apply</th>
			<%
			}
			%>
		</tr>
		<%
		List<Job> jobs = (List<Job>) request.getAttribute("jobs");
		if (jobs != null && !jobs.isEmpty()) {
			for (Job job : jobs) {
		%>
		<tr>
			<td><%=job.getTitle()%></td>
			<td><%=job.getCompany()%></td>
			<td><%=job.getDescription()%></td>
			<td><%=job.getlocation()%></td>
			<td>$<%=job.getSalary()%></td>
			<%
			if ("JobSeeker".equals(user.getRole())) {
			%>
			<td><a href="jsp/applyJob.jsp?jobId=<%=job.getJobId()%>">Apply
					Now</a></td>
			<%
			}
			%>
		</tr>
		<%
		}
		} else {
		%>
		<tr>
			<td colspan="6">No jobs available</td>
		</tr>
		<%
		}
		%>
	</table>
	<br>
	<br>
	<%
	if ("JobSeeker".equals(user.getRole())) {
	%><a href="jsp/jobAlerts.jsp">Subscribe
		to Job Alerts</a>
	<br>
	<br>
	<a href="jsp/unsubscribeJobAlerts.jsp">Unsubscribe to Job Alerts</a>
	<%}%>
	<br>
	<br>
	<a href="jsp/dashboard.jsp">Go Back</a>
</body>
</html>
