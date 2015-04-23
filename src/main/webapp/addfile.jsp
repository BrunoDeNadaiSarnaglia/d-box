<%-- 
    Document   : index
    Created on : Apr 1, 2015, 8:54:19 PM
    Author     : Cassio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add file</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap core CSS -->
    <link href="style/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="style/sign.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <form class="form-signin" action="SignInRequest" method="POST">
        <img src="img/logo_dbox.png" alt="DBox logo" width="128" class="img-responsive center-block"/>

        <h2 class="form-signin-heading" style="text-align: center;">Add file</h2>
        <input name="url" type="url" class="form-control" placeholder="Enter file url">
        <input name="name" type="name" class="form-control" placeholder="Enter file name">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Add new file</button>
    </form>
</div>
<!-- /container -->
<!-- jQuery library -->
<script src="js/jquery-1.11.2.min.js"></script>
<!-- Loads head elements -->
<script src="js/header.js"></script>
</body>
</html>