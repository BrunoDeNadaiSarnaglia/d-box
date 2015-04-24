<%-- 
    Document   : welcome
    Author     : Cassio
--%>

<%@ page import="rowClasses.File" %>
<%@ page import="rowClasses.Folder" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="db.IdByEmail" %>
<%@ page import="rowClasses.User" %>
<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <title>Welcome,
        <%
            String name = (String) request.getSession().getAttribute("name");
            String email = (String) request.getSession().getAttribute("email");
            Integer thisID = (Integer) request.getSession().getAttribute("id");
            HashSet<File> fileList = (HashSet<File>) request.getSession().getAttribute("fileList");
            HashSet<Folder> folderList = (HashSet<Folder>) request.getSession().getAttribute("folderList");
            Boolean showFriends = (Boolean) request.getSession().getAttribute("showFriends");
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
            <form class="navbar-form navbar-left" action="SettingsPage" method="POST">
                <input type="hidden" name="name" value="<% out.print(name); %>">
                <input type="hidden" name="email" value="<% out.print(email); %>">
                <input type="hidden" name="id" value="<% out.print(thisID); %>">
                <button class="btn btn-primary" type="submit" style="background-color: #333; border-color: #333;">
                    Settings
                </button>
            </form>
            <form class="navbar-form navbar-left">
                <input type="text" class="form-control" placeholder="Search friends...">
                <button class="btn btn-primary" type="submit" style="background-color: #333; border-color: #333;">
                    <span class="glyphicon glyphicon-search"></span> Add a friend
                </button>
            </form>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <form action="ShowFriendsRequest" method="POST">
                <input type="hidden" name="name" value="<% out.print(name); %>">
                <input type="hidden" name="email" value="<% out.print(email); %>">
                <input type="hidden" name="id" value="<% out.print(thisID); %>">
                <ul class="nav nav-sidebar">
                    <li>
                        <button class="btn btn-block btn-success" type="submit">Show my friends</button>
                    </li>
                </ul>
            </form>
            <ul class="nav nav-sidebar">
                <li>
                    <button class="btn btn-block btn-success" onclick="location.href = 'index.jsp'">Logout</button>
                </li>
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
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Add a folder</button>
                    </form>
                </div>
                <div class="col-md-2 placeholder">
                </div>
            </div>
            <% if (showFriends == null || (!showFriends)) {
            %>
            <h2 class="sub-header">Your files</h2>
            <%
                if (thisID != null && email != null && IdByEmail.getId(email) != null && !thisID.equals(IdByEmail.getId(email))) {
            %>
            <form action="OpenFolder" method="POST">
                <input type="hidden" name="name" value="<% out.print(name); %>">
                <input type="hidden" name="email" value="<% out.print(email); %>">
                <input type="hidden" name="id" value="<% out.print(IdByEmail.getId(email)); %>">
                <button class="btn btn-block btn-success" style="width: 200px;" type="submit">Go back to root</button>
            </form>
            <%
                }
            %>
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
            <h3>You have no files or folders here.</h3>
            <%
                } /* End of case when you do not show your friends */
            } else { /* Case when you do show friends */
            %>
            <h2>Your friends</h2>

            <form action="OpenFolder" method="POST">
                <input type="hidden" name="name" value="<% out.print(name); %>">
                <input type="hidden" name="email" value="<% out.print(email); %>">
                <input type="hidden" name="id" value="<% out.print(thisID); %>">
                <button class="btn btn-block btn-success" style="width: 200px;" type="submit">See your files</button>
            </form>
            <%
                ArrayList<User> userList = (ArrayList<User>) request.getSession().getAttribute("userList");
                if (userList != null && userList.size() > 0) {
            %>
            <div class="table-responsive">
                <table class="table table-striped">
                    <col width="40%">
                    <col width="40%">
                    <col>
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for (User user : userList) {
                    %>
                    <tr>
                        <td><% out.print(user.getName()); %></td>
                        <td><% out.print(user.getEmail()); %></td>
                        <td>
                            <form action="Unfriend" method="POST">
                                <input type="hidden" name="name" value="<% out.print(name); %>">
                                <input type="hidden" name="email" value="<% out.print(email); %>">
                                <input type="hidden" name="friendEmail" value="<% out.print(user.getEmail()); %>">
                                <input type="hidden" name="id" value="<% out.print(thisID); %>">
                                <button class="btn btn-block btn-danger" style="width: 200px;" type="submit">DELETE
                                </button>
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
            <%
            } else {
            %>
            <h3>You have no friends yet.</h3>
            <%
                    }
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