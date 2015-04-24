package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Bruno on 4/24/2015.
 */
public class AddFriend {

    public static void add(String email1, String email2) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionConfigure.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO friendship (email1, email2) VALUES (?,?)");
            preparedStatement.setString(1, email1);
            preparedStatement.setString(2, email2);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("INSERT INTO friendship (email1, email2) VALUES (?,?)");
            preparedStatement.setString(1, email2);
            preparedStatement.setString(2, email1);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
