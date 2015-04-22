<%-- 
    Document   : welcome
    Author     : Cassio
--%>

<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <title>Welcome,
        <%
            String name = (String) request.getAttribute("name");
            String email = (String) request.getAttribute("email");
            out.print(name);
        %>
        !</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap core CSS -->
    <link href="style/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="style/dashboard.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">DBox</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="settings.jsp">Settings</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <form class="nav-sidebar">
                <button class="btn navbar-btn" action="submit" style="width: inherit;">Show my friends</button>
                <button class="btn navbar-btn" action="submit" style="width: inherit;">Add a friend</button>
                <button class="btn navbar-btn" action="submit" style="width: inherit;">Logout</button>
                <!--<ul class="nav nav-sidebar">
                    <li class="active"><a href="#">My DBox<span class="sr-only">(current)</span></a></li>
                    <li>
                        <button action="submit">Show my friends</button>
                    </li>
                    <li class="active"><a href="#">Add a friend...</a></li>
                    <li><a href="#">Logout</a></li>
                </ul>-->
            </form>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Hello, <% out.print(name);%>!</h1>

            <div class="row placeholders">
                <div class="col-md-6 placeholder">
                    <span class="glyphicon glyphicon-file" aria-hidden="true" style="font-size: 300%;"></span>
                    <h4>Add a file</h4>
                </div>
                <div class="col-md-6 placeholder">
                    <span class="glyphicon glyphicon-folder-open" aria-hidden="true" style="font-size: 300%;"></span>
                    <h4>Add a folder</h4>
                </div>
            </div>

            <h2 class="sub-header">Your files</h2>

            <div class="table-responsive">
                <form>
                    <table class="table table-striped">
                        <col width="100px">
                        <col width="30%">
                        <col>
                        <col width="100px">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>URL</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1,001</td>
                            <td>File</td>
                            <td>simple_url.com</td>
                            <td class="danger" style="text-align: center;">DELETE</td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- jQuery library -->
<script src="js/jquery-1.11.2.min.js"></script>
<!-- Bootstrap JS library -->
<script src="js/bootstrap.min.js"></script>
<!-- Loads head elements -->
<script src="js/header.js"></script>
</body>
</html>