<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Update Application Status</title>
</head>
<body>
    <h2>Update Application Status</h2>
    
    <form action="${pageContext.request.contextPath}/updateApplicationStatus" method="post">
        <input type="hidden" name="applicationId" value="<%= request.getParameter("applicationId") %>">
        
        <label for="status">Select Status:</label>
        <select name="status" id="status">
            <option value="Under Review">Under Review</option>
            <option value="Accepted">Accepted</option>
            <option value="Rejected">Rejected</option>
        </select>

        <button type="submit">Update Status</button>
    </form>
    <br>
    <a href="viewApplications.jsp">Back to Applications</a>
</body>
</html>
