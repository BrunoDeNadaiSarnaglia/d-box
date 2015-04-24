package db;

import rowClasses.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Bruno on 4/24/2015.
 */
public class FindFriends {

    public static ArrayList<User> find(String email){
        ArrayList<User> list = new ArrayList<User>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionConfigure.getConnection();
           /* preparedStatement = connection.prepareStatement("SELECT name FROM user WHERE email "
                    + "IN (SELECT email2 from friendship WHERE email1 = ?)");*/
            preparedStatement = connection.prepareStatement("SELECT email, name, password, id FROM users, friendship WHERE email1 = ? AND email = email2");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                list.add(new User(resultSet.getString("email"), resultSet.getString("name"), resultSet.getInt("id")));
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
        return list;
    }

}
