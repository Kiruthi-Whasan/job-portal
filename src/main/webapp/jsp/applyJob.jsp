<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Apply for Job</title>
</head>
<body>
    <h2>Confirm Job Application</h2>
    
    <% User user = (User) session.getAttribute("user");
       if (user == null || !"JobSeeker".equals(user.getRole())) { %>
       <p>Please <a href="login.jsp">login</a> as a Job Seeker to apply.</p>
    <% } else { %>
        <form action="${pageContext.request.contextPath}/applyJob" method="post">
            <input type="hidden" name="jobId" value="<%= request.getParameter("jobId") %>">
            <p>Are you sure you want to apply for this job?</p>
            <button type="submit">Apply</button>
            <a href="jsp/jobList.jsp">Cancel</a>
        </form>
    <% } %>
</body>
</html>
