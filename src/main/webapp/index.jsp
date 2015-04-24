<%-- 
    Document   : index
    Created on : Apr 1, 2015, 8:54:19 PM
    Author     : Cassio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome!</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap core CSS -->
    <link href="style/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <img src="img/logo_dbox.png" alt="DBox logo" width="350" class="img-responsive center-block"
         style="margin-top: 30px;"/>

    <h1 style="text-align: center;"><strong>DBox</strong></h1>

    <h1 style="text-align: center;">A database cloud repository</h1>

    <div class='wrapper text-center'>
        <div class="btn btn-group">
            <button onclick="window.location.href = 'signin.jsp'" type="button" class="btn btn-default">Log in</button>
            <button onclick="window.location.href = 'signup.jsp'" type="button" class="btn btn-primary">Sign up</button>
        </div>
    </div>
</div>
<!-- jQuery library -->
<script src="js/jquery-1.11.2.min.js"></script>
<!-- Loads head elements -->
<script src="js/header.js"></script>
</body>
</html>