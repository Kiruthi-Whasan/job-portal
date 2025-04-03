<%@ page import="model.User"%>
<%@ page session="true"%>
<%
User user = (User) session.getAttribute("user");
if (user == null || !user.getRole().equals("Employer")) {
	response.sendRedirect("dashboard.jsp?msg=Unauthorized Access");
}
%>

<h2>Post a New Job</h2>
<form action="${pageContext.request.contextPath}/postJob" method="post">
	<div style="display:flex">
		<label for="jobTitle">Job Title: </label>
		<div style="color: red"> *</div>
	</div>
	<input type="text" name="title" required><br>
	<div style="display:flex">
		<label for="company">Company Name:</label>
		<div style="color: red"> *</div>
	</div>
	<input type="text" id="company" name="company" required><br>
	<div style="display:flex">
		<label for="description">Description: </label>
		<div style="color: red"> *</div>
	</div>
	<textarea name="description" required></textarea>
	<br>
	<div style="display:flex">
		<label for="location">Location: </label>
		<div style="color: red"> *</div>
	</div>
	<input type="text" name="location" required><br>
	<div style="display:flex">
		<label for="salary">Salary: </label>
		<div style="color: red"> *</div>
	</div>
	<input type="number" name="salary" step="0.01" required><br><br>
	<button type="submit">Post Job</button>
</form>
