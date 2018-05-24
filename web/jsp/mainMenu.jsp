<%--
  Created by IntelliJ IDEA.
  User: sirko
  Date: 19.05.18
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body class="container">
    <h1>Log in successful!</h1>
    <br>
    <%
        if(request.getAttribute("lastName") != null) {
            out.println("Hello, " + request.getAttribute("lastName"));
        }
    %>
    <br>
    <input type="submit" class="btn btn-danger" value="Log Out" onclick="location.href='/logout'"/>
    <input type="button" class="btn btn-info" value="New patients" onclick="location.href='/newPatients'"/>
    <input type="button" class="btn btn-info" value="All patients" onclick="location.href='/allPatients'"/>
</body>
</html>
