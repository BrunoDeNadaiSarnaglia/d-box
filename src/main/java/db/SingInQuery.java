package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Bruno on 4/21/2015.
 */
public class SingInQuery {

    protected static String username;

    public static boolean signIn(String email, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionConfigure.getConnection();
            preparedStatement = connection.prepareStatement("SELECT password, name FROM users WHERE email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String passwordInTable = resultSet.getString("password");
                username = resultSet.getString("name");
                if(password.equals(passwordInTable)){
                    return true;
                }
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
        return false;
    }

}
