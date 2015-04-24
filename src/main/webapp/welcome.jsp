<%-- 
    Document   : welcome
    Author     : Cassio
--%>

<%@ page import="rowClasses.File" %>
<%@ page import="rowClasses.Folder" %>
<%@ page import="java.util.HashSet" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <title>Welcome,
        <%
            String name = (String) request.getAttribute("name");
            String email = (String) request.getAttribute("email");
            HashSet<File> fileList = (HashSet<File>) request.getAttribute("fileList");
            HashSet<Folder> folderList = (HashSet<Folder>) request.getAttribute("folderList");
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
            <form action="SettingsPage" method="POST">
                <input type="hidden" name="name" value="<% out.print(name);%>">
                <input type="hidden" name="email" value="<% out.print(email);%>">
                <button class="btn btn-primary" type="submit"
                        style="margin-top: 7px; background-color: #333; border-color: #333;">Settings
                </button>
            </form>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="#">My DBox<span class="sr-only">(current)</span></a></li>
            </ul>
            <form id="show_friends">
                <ul class="nav nav-sidebar">
                    <li><a href="#" onclick="form.submit()">Show my friends</a></li>
                </ul>
            </form>
            <form id="add_friend">
                <ul class="nav nav-sidebar">
                    <li><a href="#" onclick="form.submit()">Add a friend...</a></li>
                </ul>
            </form>
            <ul class="nav nav-sidebar">
                <li><a href="index.jsp">Logout</a></li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Hello, <% out.print(name);%>!</h1>

            <div class="row placeholders">
                <div class="col-md-2 placeholder">
                </div>
                <div class="col-md-3 placeholder">
                    <span class="glyphicon glyphicon-file" aria-hidden="true" style="font-size: 300%;"></span>
                    <br>

                    <form class="form-signin" action="AddFileToCurrentFolder" method="POST">
                        <input type="text" name="file_name" placeholder="File Name*" class="form-control" required>
                        <input type="url" name="url" placeholder="URL*" class="form-control" required>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Add a file</button>
                    </form>
                </div>
                <div class="col-md-2 placeholder">
                </div>
                <div class="col-md-3 placeholder">
                    <span class="glyphicon glyphicon-folder-open" aria-hidden="true" style="font-size: 300%;"></span>
                    <br>

                    <form class="form-signin" action="AddFolderToCurrentFolder" method="POST">
                        <input type="text" name="folder_name" placeholder="Folder Name*" class="form-control" required>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Add a folder</button>
                    </form>
                </div>
                <div class="col-md-2 placeholder">
                </div>
            </div>
            <h2 class="sub-header">Your files</h2>
            <% if (fileList != null || folderList != null) {
            %>
            <div class="table-responsive">
                <table class="table table-striped">
                    <col width="30%">
                    <col>
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>URL</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>File Name whatever</td>
                        <td>simple_url.com</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <%
            } else {
            %>
            <h3>You have no files or folders</h3>
            <%
                }
            %>
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