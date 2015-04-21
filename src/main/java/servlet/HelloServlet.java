package servlet;

import db.ConnectionConfigure;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "MyServlet", 
        urlPatterns = {"/users"}
    )
public class HelloServlet extends HttpServlet {

    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.write("hello heroku".getBytes());
        out.flush();
        out.close();
    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getRequestURI().endsWith("/db")) {
            showDatabase(req, resp);
        } if (req.getRequestURI().endsWith("/users")) {
            showDatabaseUsers(req, resp);
        }else {
            showHome(req, resp);
        }
    }

    private void showHome(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.getWriter().print("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\"\n" +
                "   \"http://www.w3.org/TR/html4/strict.dtd\">\n" +
                "<HTML>\n" +
                "   <HEAD>\n" +
                "      <TITLE>My first HTML document</TITLE>\n" +
                "   </HEAD>\n" +
                "   <BODY>\n" +
                "      <P>Hello world!\n" +
                "   </BODY>\n" +
                "</HTML>");
    }

    private void showDatabaseUsers(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            connection = ConnectionConfigure.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users");
            String out = "Hello!\n";
            while(rs.next()){
                out += rs.getString(0) + " " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3);
            }
            resp.getWriter().print(out);
        } catch (Exception e){
            resp.getWriter().print("There was an error: " + e.getMessage());
        } finally {
            if (connection != null) {
                try{
                    connection.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try{
                    rs.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try{
                    rs.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void showDatabase(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = ConnectionConfigure.getConnection();

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
            stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
            ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

            String out = "Hello!\n";
            while (rs.next()) {
                out += "Read from DB: " + rs.getTimestamp("tick") + "\n";
            }

            resp.getWriter().print(out);
        } catch (Exception e) {
            resp.getWriter().print("There was an error: " + e.getMessage());
        } finally {
            if (connection != null) try{connection.close();} catch(SQLException e){}
        }
    }

    private Connection getConnection() throws URISyntaxException, SQLException, ClassNotFoundException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        int port = dbUri.getPort();

        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + port + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
    }
    
}
