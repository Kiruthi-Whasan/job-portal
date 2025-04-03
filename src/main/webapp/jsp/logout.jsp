<%
    session.invalidate();
    response.sendRedirect("login.jsp?msg=Logged out successfully!");
%>
