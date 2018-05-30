<%@ page import="model.Patient" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sirko
  Date: 28.05.18
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Treatment</title>
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
        <h2>Add information about medicines and procedures for patient</h2>
    </div>
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
        <form method="post">
            <div class="form-group">
                <label>Card number:</label>
                <input type="text" class="form-control" id="cardNumber" name="cardNumber" placeholder="Enter cardNumber" required pattern="^[1-9][0-9]{8}">
            </div>

            <div class="form-group">
                <label>Medicines:</label>
                <input type="text" class="form-control" id="medicines" name="medicines" placeholder="Enter medicines">
            </div>
            <div class="form-group">
                <label>Procedures:</label>
                <input type="text" class="form-control" name="Procedures" placeholder="Enter procedures" required>
            </div>
            <%
                if (request.getSession().getAttribute("position") != null) {
                    out.println("<div class=\"form-group\">\n" +
                            "                <div class=\"checkbox\">\n" +
                            "                    <label><input type=\"radio\" name=\"operation\" value=\"1\">Operation</label>\n" +
                            "                </div>\n" +
                            "            </div>");
                }
            %>

            <input type="submit" class="btn btn-info" value="Add treatment"/>
        </form>
    </div>
    <div class="footer">
        <input type="button" class="btn btn-info" value="Back to Main Menu" onclick="location='jsp/mainMenu.jsp';"/>
        <input type="submit" class="btn btn-danger" value="Log Out" onclick="location.href='/logout'" align="right"/>
    </div>
</body>
</html>
