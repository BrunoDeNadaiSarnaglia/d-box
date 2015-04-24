<%-- 
    Document   : welcome
    Author     : Cassio
--%>

<%@ page import="rowClasses.File" %>
<%@ page import="rowClasses.Folder" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="db.IdByEmail" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <title>Welcome,
        <%
            String name = (String) request.getSession().getAttribute("name");
            String email = (String) request.getSession().getAttribute("email");
            Integer thisID = (Integer) request.getSession().getAttribute("id");
            Integer rootID = IdByEmail.getId(email);
            HashSet<File> fileList = (HashSet<File>) request.getSession().getAttribute("fileList");
            HashSet<Folder> folderList = (HashSet<Folder>) request.getSession().getAttribute("folderList");
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
                <input type="hidden" name="name" value="<% out.print(name); %>">
                <input type="hidden" name="email" value="<% out.print(email); %>">
                <input type="hidden" name="id" value="<% out.print(thisID); %>">
                <input type="hidden" name="rootID" value="<% out.print(rootID); %>">
                <button class="btn btn-primary" type="submit"
                        style="margin-top: 7px; background-color: #333; border-color: #333;">Settings
                </button>
            </form>
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
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
                        <input type="hidden" name="name" value="<% out.print(name); %>">
                        <input type="hidden" name="email" value="<% out.print(email); %>">
                        <input type="hidden" name="id" value="<% out.print(thisID); %>">
                        <input type="hidden" name="rootID" value="<% out.print(rootID); %>">
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
                        <input type="hidden" name="name" value="<% out.print(name); %>">
                        <input type="hidden" name="email" value="<% out.print(email); %>">
                        <input type="hidden" name="id" value="<% out.print(thisID); %>">
                        <input type="hidden" name="rootID" value="<% out.print(rootID); %>">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Add a folder</button>
                    </form>
                </div>
                <div class="col-md-2 placeholder">
                </div>
            </div>
            <h2 class="sub-header">Your files</h2>
            <% if ((fileList != null && fileList.size() > 0) || (folderList != null && folderList.size() > 0)) {
            %>
            <div class="table-responsive">
                <table class="table table-striped">
                    <col>
                    <col width="30%">
                    <col>
                    <col>
                    <thead>
                    <tr>
                        <th>Type</th>
                        <th>Name</th>
                        <th>Link</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% if (folderList != null && folderList.size() > 0) {
                        for (Folder folder : folderList) {
                    %>
                    <tr>
                        <td><span class="glyphicon glyphicon-folder-open" style="margin: 0; padding: 0;"></span></td>
                        <td><% out.print(folder.getName()); %></td>
                        <td>
                            <form action="OpenFolder" method="POST">
                                <input type="hidden" name="name" value="<% out.print(name); %>">
                                <input type="hidden" name="email" value="<% out.print(email); %>">
                                <input type="hidden" name="id" value="<% out.print(folder.getId()); %>">
                                <input type="hidden" name="rootID" value="<% out.print(rootID); %>">
                                <button class="btn btn-block btn-primary" style="width: 200px;" type="submit">OPEN
                                    FOLDER
                                </button>
                            </form>
                        </td>
                        <td>
                            <form action="DeleteFolderRequest" method="POST">
                                <input type="hidden" name="name" value="<% out.print(name); %>">
                                <input type="hidden" name="email" value="<% out.print(email); %>">
                                <input type="hidden" name="deleteID" value="<% out.print(folder.getId()); %>">
                                <input type="hidden" name="id" value="<% out.print(thisID); %>">
                                <input type="hidden" name="rootID" value="<% out.print(rootID); %>">
                                <button class="btn btn-block btn-danger" style="width: 200px;" type="submit">DELETE
                                </button>
                            </form>
                        </td>
                    </tr>
                    <%
                            }
                        }
                        if (fileList != null && fileList.size() > 0) {
                            for (File file : fileList) {
                    %>
                    <tr>
                        <td><span class="glyphicon glyphicon-file" style="margin: 0; padding: 0;"></span></td>
                        <td><% out.print(file.getName()); %></td>
                        <td><a href="<% out.print(file.getURL()); %>" target="_blank"><%
                            out.print(file.getURL()); %></a></td>
                        <td>
                            <form action="DeleteFileRequest" method="POST">
                                <input type="hidden" name="name" value="<% out.print(name); %>">
                                <input type="hidden" name="email" value="<% out.print(email); %>">
                                <input type="hidden" name="deleteID" value="<% out.print(file.getId()); %>">
                                <input type="hidden" name="id" value="<% out.print(thisID); %>">
                                <input type="hidden" name="rootID" value="<% out.print(rootID); %>">
                                <button class="btn btn-block btn-danger" style="width: 200px;" type="submit">DELETE
                                </button>
                            </form>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
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