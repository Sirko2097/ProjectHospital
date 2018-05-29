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
    <title>Main Menu</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body class="container">
    <%
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader ("Expires", 0);
        if(session.getAttribute("license")==null)
            request.getRequestDispatcher("/login").forward(request, response);
    %>
    <div class="page-header">
        <h2 align="center">
        <%
            if(request.getSession().getAttribute("lastName") != null && request.getSession().getAttribute("position") != null) {
                out.println("Welcome, " + request.getSession().getAttribute("position")
                        + request.getSession().getAttribute("lastName") + "!");
            }
            else if (request.getSession().getAttribute("lastName") != null) {
                out.println("Welcome, " + request.getSession().getAttribute("lastName") + "!");
            }
        %>
        </h2>
    </div>
    <br><br>

    <div class="btn-group">
        <input type="button" class="btn btn-warning" value="Your New Patients" onclick="location.href='/newPatients'">
        <input type="button" class="btn btn-info" value="All Patients" onclick="location.href='/allPatients'"/>
        <input type="button" class="btn btn-primary" value="Add New Patient" onclick="location.href='/addPatient'"/>
    </div>
    <input type="submit" class="btn btn-danger" value="Log Out" onclick="location.href='/logout'" align="right"/>

</body>
</html>
