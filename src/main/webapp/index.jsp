<%-- 
    Document   : index
    Created on : Apr 1, 2015, 8:54:19 PM
    Author     : Cassio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <h1>DBox main page</h1>
            <button onclick="window.location.href = 'signin.jsp'" type="button">Sign in</button>
            <button onclick="window.location.href = 'signup.jsp'" type="button">Sign up</button>
        </div>
        <!-- jQuery library -->
        <script src="js/jquery-1.11.2.min.js"></script>
        <!-- Loads head elements -->
        <script src="js/header.js"></script>
    </body>
</html>