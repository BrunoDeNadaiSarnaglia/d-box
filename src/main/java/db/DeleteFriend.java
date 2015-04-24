package db;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Bruno on 4/24/2015.
 */
public class DeleteFriend {

    public static void delete(String email1, String email2){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionConfigure.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM friendship WHERE email1 = ? AND email2 = ?");
            preparedStatement.setString(1, email1);
            preparedStatement.setString(2, email2);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("DELETE FROM friendship WHERE email1 = ? AND email2 = ?");
            preparedStatement.setString(1, email2);
            preparedStatement.setString(2, email1);
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
        }
    }


}
