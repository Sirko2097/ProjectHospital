<%--
  Created by IntelliJ IDEA.
  User: sirko
  Date: 27.05.18
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf8" language="java" %>
<html>
<head>
    <title>Add New Patient</title>
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
        <h2>Add information about new patient</h2>
    </div>

    <div class="jumbotron">
        <form action="addPatient" method="post">
            <div class="form-group">
                <label>Passport Number:</label>
                <input type="text" title="** 000000" class="form-control" id="passNumber" name="passNumber" placeholder="Enter patient's passport number" pattern="[A-Z,a-z,А-я]{2} [0-9]{6}" required>
            </div>
            <div class="form-group">
                <label>First Name:</label>
                <input type="text" class="form-control" name="firstName" placeholder="Enter patient's first name" required>
            </div>
            <div class="form-group">
                <label>Second Name:</label>
                <input type="text" class="form-control" name="secondName" placeholder="Enter patient's second name" required>
            </div>
            <div class="form-group">
                <label>Last Name:</label>
                <input type="text" class="form-control" name="lastName" placeholder="Enter patient's last name" required>
            </div>
            <div class="form-group">
                <label>Patient's birthday:</label>
                <input type="date" class="form-control" name="birthday" placeholder="Enter patient's birthday" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" required>
            </div>
            <div class="form-group">
                <label>Card number:</label>
                <input type="text" class="form-control" title="Nine numbers" name="cardNumber" placeholder="Enter patient's card number" pattern="^[1-9][0-9]{8}" required>
            </div>
            <input type="submit" class="btn btn-info" value="Add patient"/>
        </form>
    </div>

    <div class="footer">
        <input type="button" class="btn btn-info" value="Back to Main Menu" onclick="location='jsp/mainMenu.jsp';"/>
        <input type="submit" class="btn btn-danger" value="Log Out" onclick="location.href='/logout'" align="right"/>
    </div>
</body>
</html>
