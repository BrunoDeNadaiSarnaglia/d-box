package db;

import rowClasses.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Bruno on 4/24/2015.
 */
public class IdByEmail {

    public static Integer getId(String email){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionConfigure.getConnection();
            preparedStatement = connection.prepareStatement("SELECT id FROM users WHERE email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                return resultSet.getInt("id");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally  {
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
                    resultSet.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
