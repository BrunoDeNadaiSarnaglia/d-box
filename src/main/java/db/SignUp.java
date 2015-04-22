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
            preparedStatement = connection.prepareStatement("SELECT MAX(id) AS id FROM file");
            resultSet = preparedStatement.executeQuery();
            int idCount = 0;
            if(resultSet.next()) {
                idCount = resultSet.getInt("id") + 1;
            }
            preparedStatement = connection.prepareStatement("SELECT MAX(id) AS id FROM folder");
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                idCount = Math.max(resultSet.getInt("id") + 1, idCount);
            }
            preparedStatement = connection.prepareStatement("INSERT INTO users (email, name, password, id) VALUES (?,?,?,?)");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, password);
            preparedStatement.setInt(4, idCount);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("INSERT INTO folder (id, name) VALUES (?,?)");
            preparedStatement.setInt(1, idCount);
            preparedStatement.setString(2, name + "'s home folder");
            preparedStatement.executeUpdate();
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
