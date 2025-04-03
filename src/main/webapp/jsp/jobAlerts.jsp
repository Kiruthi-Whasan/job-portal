<%@ page contentType="text/html; charset=UTF8"%>
<HTML>
<head>
<title>Job Alerts Subscription</title>
</head>
<body>
<h2>Subscribe to Job Alerts</h2>
<form action="${pageContext.request.contextPath}/jobAlerts" method="post">
	<label for="name">Name:</label>
	<input type="text" name="name" placeholder="Example: Alen" required/> <br>
	<label for="email">Email:</label>
	<input type="email" name="email" placeholder="Example: abc@gmail.com" required/> <br>
	<label for="keywords">Job Title Keywords:</label>
	<input type="text" name="keywords" placeholder="Example: Software Engineer, HR Manager" required/> <br>
	<label for="location">Location:</label>
	<input type="text" name="location" placeholder="Example: India, New York" required/> <br>
	<button type="submit">Subscribe</button>
</form>
</body>
</HTML>
