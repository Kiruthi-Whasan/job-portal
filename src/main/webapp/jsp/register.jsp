
<form action="${pageContext.request.contextPath}/register" method="post">
	Name: <input type="text" name="name" required> <br> Email:
	<input type="email" name="email" required> <br> Password:
	<input type="password" name="password" required> <br>
	Role: <select name="role">
		<option value="JobSeeker">Job Seeker</option>
		<option value="Employer">Employer</option>
	</select><br>
	<button type="submit">Register</button>
</form>