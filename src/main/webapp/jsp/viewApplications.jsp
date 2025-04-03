<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="model.Application"%>
<%@ page import="java.util.List"%>
<html>
<head>
<title>View Applications</title>
</head>
<body>
	<nav>
    <a href="jsp/postJob.jsp">Post a Job</a> |
    <a href="viewApplications">Manage Applicants</a> |
    <a href="jsp/logout.jsp">Logout</a>
</nav>
	<h2>Applications for Your Jobs</h2>
	<table border="1">
		<tr>
			<th>Job Title</th>
			<th>Job Seeker Name</th>
			<th>Status</th>
			<th>Actions</th>
		</tr>
		<%
		List<Application> applications = (List<Application>) request.getAttribute("applications");
		if (applications != null && !applications.isEmpty()) {
			for (Application app : applications) {
		%>
		<tr>
			<td><%=app.getJobTitle()%></td>
			<td><%=app.getJobSeekerName()%></td>
			<td><%=app.getStatus()%></td>
			<td><a
				href="jsp/updateApplicationStatus.jsp?applicationId=<%=app.getApplicationId()%>">Update
					Status</a></td>
		</tr>
		<%
		}
		} else {
		%>
		<tr>
			<td colspan="4">No applications received</td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>
