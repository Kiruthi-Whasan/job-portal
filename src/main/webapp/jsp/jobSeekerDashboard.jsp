<%@ page import="model.Application"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>My Applications</title>
</head>
<body>

	<!-- Navigation for Job Seekers -->
	<nav>
		<a href="jobList">Browse Jobs</a> | <a
			href="jobSeekerDashboard">My Applications</a> | <a
			href="jsp/logout.jsp">Logout</a>
	</nav>
	<hr>

	<h2>My Applied Jobs</h2>
	<table border="1">
		<tr>
			<th>Job Title</th>
			<th>Company</th>
			<th> Description</th>
			<th>Location</th>
			<th>Salary</th>
			<th>Status</th>
		</tr>
		<%
		List<Application> applications = (List<Application>) request.getAttribute("applications");
		if (applications != null && !applications.isEmpty()) {
			for (Application app : applications) {
		%>
		<tr>
			<td><%=app.getJobTitle()%></td>
			<td><%=app.getCompany()%></td>
			<td><%=app.getDescription() %></td>
			<td><%=app.getLocation()%></td>
			<td><%=app.getSalary()%></td>
			<td><%=app.getStatus()%></td>
			<td>
                <% if (!"Withdrawn".equals(app.getStatus())) { %>
                    <form action="WithdrawApplicationServlet" method="post">
                        <input type="hidden" name="applicationId" value="<%= app.getApplicationId() %>">
                        <button type="submit">Withdraw</button>
                    </form>
                <% } else { %>
                    <span>Withdrawn</span>
                <% } %>
            </td>
		</tr>
		<%
		}
		} else {
		%>
		<tr>
			<td colspan="4">No job applications found</td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>
