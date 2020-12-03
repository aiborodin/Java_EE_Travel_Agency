<%@ page import="com.travelagency.entity.User" %>
<%@ page import="com.travelagency.entity.Agent" %>
<%@ page import="com.travelagency.entity.Client" %>

<%
    User validatedUser = (User)session.getAttribute("validUser");
    if (validatedUser == null) {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        return;
    }
%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global_styles.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<html>
<head>
    <title>Access Denied</title>
</head>
<body>
<h3 style="color: #ea4a4a">You don't have a permission to access this resource!</h3>
<a href="${pageContext.request.contextPath}${cookie.homePage.value}">Go to Home Page</a>
</body>
</html>
