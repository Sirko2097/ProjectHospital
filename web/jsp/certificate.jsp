<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sirko
  Date: 30.05.18
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Certificate</title>
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
<div class="page-header" align="center">
    <h2>Medical certificate</h2>
</div>
    <%
        List<String> parameters = (List<String>) request.getSession().getAttribute("patientsParameters");
        if (parameters != null){
            out.println("<div class=\"jumbotron\">" +
                    "<ul class=\"list-group\">\n" +
                    "    <li class=\"list-group-item\">Passport number: " + parameters.get(0) + "</li>\n" +
                    "    <li class=\"list-group-item\">First name: " + parameters.get(1) + "</li>\n" +
                    "    <li class=\"list-group-item\">Second name: " + parameters.get(2) + "</li>\n" +
                    "    <li class=\"list-group-item\">Last name: " + parameters.get(3) + "</li>\n" +
                    "    <li class=\"list-group-item\">Card number: " + parameters.get(4) + "</li>\n" +
                    "    <li class=\"list-group-item\">Medicines: " + parameters.get(5) + "</li>\n" +
                    "    <li class=\"list-group-item\">Procedures: " + parameters.get(6) + "</li>\n" +
                    "    <li class=\"list-group-item\">Diagnosis: " + parameters.get(7) + "</li>\n" +
                    "    <li class=\"list-group-item\">Date: " + parameters.get(8) + ":</li>\n" +
                    "    <li class=\"list-group-item\">Doctor: " + parameters.get(9) + "</li>\n" +
                    "</ul>" +
                    "</div>");
        }
    %>
<div class="jumbotron">
    <form action="/certificate" method="post">
        <div class="form-group">
            <label>Card number:</label>
            <input type="text" class="form-control" title="Nine numbers" name="cardNumber" placeholder="Enter patient's card number" pattern="^[1-9][0-9]{8}" required>
        </div>
        <input type="submit" class="btn btn-info" value="Show patient"/>
    </form>
</div>

<div class="footer">
    <input type="button" class="btn btn-info" value="Back to Main Menu" onclick="location='mainMenu.jsp'"/>
    <input type="submit" class="btn btn-danger" value="Log Out" onclick="location.href='/logout'" align="right"/>
</div>
</body>
</html>
