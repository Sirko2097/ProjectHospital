<%@ page import="model.Patient" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sirko
  Date: 30.05.18
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Diagnosis</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body class="container">
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
        List<Patient> patients = (List<Patient>) request.getSession().getAttribute("patients");
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
<div class="jumbotron">
    <form action="diagnosis" method="post">
        <div class="form-group">
            <label>Card number:</label>
            <input type="text" class="form-control" title="Nine numbers" name="cardNumber" placeholder="Enter patient's card number" pattern="^[1-9][0-9]{8}" required>
        </div>
        <div class="form-group">
            <label>Disease name:</label>
            <input type="text" class="form-control" title="Nine numbers" name="diagnosis" placeholder="Enter name of the sickness" required>
        </div>
        <div class="form-group">
            <label>Day of beginnig:</label>
            <input type="date" class="form-control" name="beginning" placeholder="Enter date of beginning" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" required>
        </div>
        <input type="submit" class="btn btn-primary" value="Confirm diagnosis"/>
    </form>
</div>
<div class="footer">
    <input type="button" class="btn btn-info" value="Back to Main Menu" onclick="location='jsp/mainMenu.jsp';"/>
    <input type="submit" class="btn btn-danger" value="Log Out" onclick="location.href='/logout'" align="right"/>
</div>
</body>
</html>
