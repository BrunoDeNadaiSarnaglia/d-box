package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Bruno on 4/21/2015.
 */
public class addFolder {

    public static void add(Integer idParent, String name){
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
            preparedStatement = connection.prepareStatement("INSERT INTO folder (id, name) VALUES (?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, idCount);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("INSERT INTO contain (parentId, childId) VALUES (?,?)");
            preparedStatement.setInt(1, idParent);
            preparedStatement.setInt(2, idCount);
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
