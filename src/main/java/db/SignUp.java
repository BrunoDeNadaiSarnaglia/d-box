package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Bruno on 4/21/2015.
 */
public class SignUp {

    public static void insert(String name, String email, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionConfigure.getConnection();
            preparedStatement = connection.prepareStatement("SELECT MAX(id) AS id FROM users");
            resultSet = preparedStatement.executeQuery();
            int idCount = resultSet.getInt("id");
            preparedStatement = connection.prepareStatement("INSERT INTO users (email, name, password, id) VALUES (?,?,?,?)");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, password);
            preparedStatement.setInt(4, idCount);
            preparedStatement.executeUpdate();
            System.out.println("INSERT INTO user (email, name, password, id) VALUES (?,?,?,?)");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try{
                    connection.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (resultSet != null){
                try{
                    connection.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
