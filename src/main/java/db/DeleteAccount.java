package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Bruno on 4/24/2015.
 */
public class DeleteAccount {

    public static void delete(String email){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            System.out.println("ENTROU NO TRY " + email);
            connection = ConnectionConfigure.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM friendship WHERE email1 = ? OR email2 = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("SELECT id FROM users WHERE email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                int id = resultSet.getInt("id");
                DeleteFolder deleteFolder = new DeleteFolder();
                deleteFolder.delete(id);
            }
            preparedStatement = connection.prepareStatement("DELETE FROM users WHERE email = ?");
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
            System.out.println("DELETE FROM users WHERE email = ?");
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
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
