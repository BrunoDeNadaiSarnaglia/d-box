package servlet;

import db.ListFilesInFolder;
import db.ListFoldersInFolder;
import db.addFolder;
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
        name = "AddFolderToCurrentFolder",
        urlPatterns = {"/AddFolderToCurrentFolder"}
)
public class AddFolderToCurrentFolder extends HttpServlet {

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
        processRequest(request, response);
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
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        Integer idParent = Integer.valueOf(request.getParameter("id"));
        Integer rootID = Integer.valueOf(request.getParameter("rootID"));
        String folderName = request.getParameter("folder_name");
        addFolder.add(idParent, folderName);
        HashSet<File> fileList = null;
        HashSet<Folder> folderList = null;
        if (idParent != null) {
            fileList = ListFilesInFolder.list(idParent);
            folderList = ListFoldersInFolder.list(idParent);
        }
        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("/welcome.jsp");
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("id", idParent);
        request.setAttribute("rootID", rootID);
        request.setAttribute("fileList", fileList);
        request.setAttribute("folderList", folderList);
        dispatcher.forward(request, response);
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