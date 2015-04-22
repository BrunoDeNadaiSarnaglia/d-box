<%-- 
    Document   : index
    Created on : Apr 1, 2015, 8:54:19 PM
    Author     : Cassio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Settings</title>
    <%
        String name = (String) request.getAttribute("name");
        String email = (String) request.getAttribute("email");
    %>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap core CSS -->
    <link href="style/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="style/sign.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <% if (request.getAttribute("changedPassword") != null) {
        Boolean changedPassword = (Boolean) request.getAttribute("changedPassword");
        if (changedPassword) {
    %>
    <div class="alert alert-success" style="text-align: center;" role="alert">
        Password changed successfully!
    </div>
    <% }
    }%>
    <img src="img/logo_dbox.png" alt="DBox logo" width="128" class="img-responsive center-block"/>

    <h2 class="form-signin-heading" style="text-align: center;">Settings</h2>

    <form class="form-signin" action="RequestPassword" method="POST">
        <input type="hidden" name="name" value="<% out.print(name);%>">
        <input type="hidden" name="email" value="<% out.print(email);%>">
        <input name="password" type="password" class="form-control" placeholder="Enter new password">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Set new password</button>
    </form>
    <form class="form-signin" action="RequestDelete" method="POST">
        <input type="hidden" name="email" value="<% out.print(email);%>">
        <button class="btn btn-lg btn-danger btn-primary btn-block" type="submit">Delete my account</button>
        <button onclick="window.location.href = 'signin.jsp'" class="btn btn-lg btn-primary btn-block" type="button">
            Logout
        </button>
    </form>
</div>
<!-- /container -->
<!-- jQuery library -->
<script src="js/jquery-1.11.2.min.js"></script>
<!-- Loads head elements -->
<script src="js/header.js"></script>
</body>
</html>