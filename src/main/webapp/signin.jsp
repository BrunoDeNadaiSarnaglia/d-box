<%-- 
    Document   : signin
    Author     : Cassio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap core CSS -->
        <link href="style/bootstrap.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="style/sign.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <form class="form-signin" action="RequestSignIn" method="POST">
                <% if (request.getAttribute("loginError") != null) {
                        Boolean error = (Boolean) request.getAttribute("loginError");
                        if (error) {
                %>
                <div class="alert alert-danger" style="text-align: center;" role="alert">
                    <strong>Enter a valid email address<strong>
                </div>
                <% }
                } else if (request.getAttribute("newUser") != null) {
                    Boolean newUser = (Boolean) request.getAttribute("newUser");
                    if (newUser) {
                %>
                <div class="alert alert-success" style="text-align: center;" role="alert">
                    Signed up successfully! You can now login
                </div>
                <%}
                    }%>
                <h2 class="form-signin-heading" style="text-align: center;">Login</h2>
                <input name="email" type="email" class="form-control" placeholder="Email address*" autofocus="true" required>
                <input name="password" type="password" class="form-control" placeholder="Password*" required>
                <button class="btn btn-lg btn-success btn-block" type="submit">Login</button>
                <button onclick="window.location.href = 'signup.jsp'" class="btn btn-lg btn-primary btn-block" type="button">Not a user? Sign up</button>
            </form>
        </div> <!-- /container -->
        <!-- jQuery library -->
        <script src="js/jquery-1.11.2.min.js"></script>
        <!-- Loads head elements -->
        <script src="js/header.js"></script>
    </body>
</html>