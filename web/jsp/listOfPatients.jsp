<%@ page import="java.util.List" %>
<%@ page import="model.Patient" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: sirko
  Date: 22.05.18
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of patients</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body class="container">
<div class="jumbotron">
    <table>
        <thead>
            <tr>
                <th>First name</th>
                <th>Second name</th>
                <th>Last name</th>
                <th>Birthday</th>
                <th>Card Number</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Patient> patients = (List<Patient>) request.getAttribute("patients");
                if (patients != null && !patients.isEmpty()) {
                    PrintWriter writer = response.getWriter();
                    writer.println("");
                }
            %>
        </tbody>
    </table>
</div>
</body>
</html>
