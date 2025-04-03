<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>

    <%
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp?msg=Please login first.");
            return;
        }
    %>

    <h2>Welcome, <%= user.getName() %></h2>
    <h3>Role: <%= user.getRole() %></h3>

    <% if ("Employer".equals(user.getRole())) { %>
        <h3>Employer Actions</h3>
        <ul>
            <li><a href="postJob.jsp">Post a New Job</a></li>
            <li><a href="../jobList">View All Posted Jobs</a></li>
            <li><a href="../viewApplications">Manage Applications</a></li>
        </ul>
    <% } else if ("JobSeeker".equals(user.getRole())) { %>
        <h3>Job Seeker Actions</h3>
        <ul>
            <li><a href="../jobList">Browse Jobs</a></li>
            <li><a href="../jobSeekerDashboard">View My Applications</a></li>
        </ul>
    <% } %>

    <br>
    <a href="logout.jsp">Logout</a>

</body>
</html>
