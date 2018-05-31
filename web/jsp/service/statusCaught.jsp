<%--
  Created by IntelliJ IDEA.
  User: sirko
  Date: 31.05.18
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
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

<h2 class="danger">There is no diagnosis for this patient.</h2>
<button type="button" class="btn btn-danger" onclick="location.href='/certificate'">Try Again</button>


</body>
</html>
