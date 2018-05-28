<%--
  Created by IntelliJ IDEA.
  User: sirko
  Date: 18.05.18
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authentication</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body class="container" >
    <div class="jumbotron">
        <h2>Input your license number and password</h2>

        <form action="login" method="post">
            <div class="form-group">
                <label for="license">License:</label>
                <input type="text" class="form-control" id="license" name="license" placeholder="Enter your license" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" required>
            </div>

            <input type="submit" class="btn btn-success" value="Log In" formmethod="post"/>
        </form>
    </div>
</body>
</html>
