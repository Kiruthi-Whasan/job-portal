<% String msg = request.getParameter("msg"); %>
<% if (msg != null) { %>
    <p style="color: red;"><%= msg %></p>
<% } %>

<form action="${pageContext.request.contextPath}/login" method="post">
    Email: <input type="email" name="email" required><br>
    Password: <input type="password" name="password" required><br>
    <button type="submit">Login</button>
</form>