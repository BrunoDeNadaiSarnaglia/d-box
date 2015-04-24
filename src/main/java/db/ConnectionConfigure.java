package db;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.*;

/**
 * @author Bruno de Nadai Sarnaglia <denadai2@illinois.edu>
 * @version 1.0
 */
public class ConnectionConfigure {

    public static Connection getConnection() throws URISyntaxException, SQLException, ClassNotFoundException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        int port = dbUri.getPort();

        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + port + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
    }
}
