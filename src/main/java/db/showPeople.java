package db;

import rowClasses.User;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

/**
 * Created by Bruno on 4/24/2015.
 */
public class showPeople {

    public static HashSet<User> show(String like){
        HashSet<User> setOfUser = new HashSet<User>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            System.out.println("ENTROU NO TRY" + like);
            connection = ConnectionConfigure.getConnection();
            preparedStatement = connection.prepareStatement("SELECT email, name, password, id FROM users WHERE name LIKE ?");
            preparedStatement.setString(1, "%" + like + "%");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                setOfUser.add(new User(resultSet.getString("email"), resultSet.getString("name"), resultSet.getInt("id")));
            }
            System.out.println("ACAAABOOOOOOOUUUUU " + setOfUser.size());
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
        return setOfUser;
    }

}
