<%@ page import="model.Patient" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sirko
  Date: 24.05.18
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Patients</title>
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
    <div align="center">
        <div class="page-header">
            <h2>All Patients in the Hospital</h2>
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
                <th>Card Number</th>
            </tr>
            </thead>
            <tbody>
                <%
                    List<Patient> patients = (List<Patient>) request.getAttribute("patientsInTheHospital");
                    if (patients != null && !patients.isEmpty()) {
                        for (Patient patient : patients) {
                            out.println("<tr class=\"info\">" +
                                    "<td>" + patient.getPassportNumber() + "</td>" +
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
    </div>
    <div class="footer">
        <input type="button" class="btn btn-primary" value="Patients without a doctor" onclick="location.href='/choosePatient'">
        <input type="button" class="btn btn-info" value="Back to Main Menu" onclick="location='jsp/mainMenu.jsp';"/>
        <input type="submit" class="btn btn-danger" value="Log Out" onclick="location.href='/logout'" align="right"/>
    </div>
</body>
</html>
