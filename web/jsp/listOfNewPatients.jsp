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
    <div class="page-header"><h2 align="center">Your new patients</h2></div>
    <div class="jumbotron">
    <table class="table table-hover">
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
                    for (Patient patient : patients) {
                        out.println("<tr class=\"info\">" +
                                "<td>" + patient.getFirstName() + "</td>" +
                                "<td>" + patient.getSecondName() + "</td>" +
                                "<td>" + patient.getLastName() + "</td>" +
                                "<td>" + patient.getBirthday() + "</td>" +
                                "<td>" + patient.getPatientCard() + "</td>" +
                                "</tr>");
                    }
                }
            %>
        </tbody>
    </table>
</div>
<div class="footer">
    <input type="button" class="btn btn-info" value="Back to Main Menu" onclick="location='jsp/mainMenu.jsp';"/>
    <input type="submit" class="btn btn-danger" value="Log Out" onclick="location.href='/logout'" align="right"/>
</div>
</body>
</html>
