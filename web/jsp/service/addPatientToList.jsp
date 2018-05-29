<%@ page import="java.util.List" %>
<%@ page import="model.Patient" %><%--
  Created by IntelliJ IDEA.
  User: sirko
  Date: 29.05.18
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Info About Patient</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body class="container">
<div align="center">
    <div class="page-header">
        <h2>Input patient's passport number</h2>
    </div>
    <div class="jumbotron">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Passport Number</th>
                <th>First name</th>
                <th>Second name</th>
                <th>Last name</th>
                <th>Birthday</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Patient> patients = (List<Patient>) request.getSession().getAttribute("freePatients");
                if (patients != null && !patients.isEmpty()) {
                    for (Patient patient : patients) {
                        out.println("<tr class=\"info\">" +
                                "<td>" + patient.getPassportNumber() + "</td>" +
                                "<td>" + patient.getFirstName() + "</td>" +
                                "<td>" + patient.getSecondName() + "</td>" +
                                "<td>" + patient.getLastName() + "</td>" +
                                "<td>" + patient.getBirthday() + "</td>" +
                                "</tr>");
                    }
                }
            %>
            </tbody>
        </table>
        <form method="post" action="helper">
            <div>
                <input type="text" title="** 000000" class="form-control" id="passNumber" name="passNumber" placeholder="Enter patient's passport number" pattern="[A-Z,a-z]{2} [0-9]{6}" required>
            </div>
            <input type="submit" class="btn btn-info" value="Add patient"/>
        </form>
    </div>
    <div class="footer">
        <input type="button" class="btn btn-info" value="Back to Main Menu" onclick="location='jsp/mainMenu.jsp';"/>
        <input type="submit" class="btn btn-danger" value="Log Out" onclick="location.href='/logout'" align="right"/>
    </div>

</div>
</body>
</html>
