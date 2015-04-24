/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import db.IdByEmail;
import db.ListFilesInFolder;
import db.ListFoldersInFolder;
import db.SingInQuery;
import rowClasses.File;
import rowClasses.Folder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

/**
 * @author Cassio dos Santos Sousa <dssntss2@illinois.edu>
 * @version 1.0
 */
@WebServlet(
        name = "SignInRequest",
        urlPatterns = {"/SignInRequest"}
)
public class SignInRequest extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /*
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RequestSignIn</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RequestSignIn at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("welcome.jsp");
        view.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        RequestDispatcher dispatcher;
        Integer id = null;
        if (SingInQuery.check(email, password)) {
            id = IdByEmail.getId(email);
            HashSet<File> fileList = null;
            HashSet<Folder> folderList = null;
            if (id != null) {
                fileList = ListFilesInFolder.list(id);
                folderList = ListFoldersInFolder.list(id);
            }
            //dispatcher = request.getRequestDispatcher("/welcome.jsp");
            request.setAttribute("name", SingInQuery.username);
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("id", id);
            request.setAttribute("fileList", fileList);
            request.setAttribute("folderList", folderList);
            //dispatcher.forward(request, response);
            response.sendRedirect("/welcome.do?s=1");
        } else {
            dispatcher = request.getRequestDispatcher("/signin.jsp");
            request.setAttribute("loginError", true);
            dispatcher.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
