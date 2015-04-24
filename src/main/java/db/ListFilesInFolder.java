package db;

import rowClasses.File;
import rowClasses.Folder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

/**
 * Created by Bruno on 4/24/2015.
 */
public class ListFilesInFolder {


    private HashSet<File> setOfFiles = new HashSet<File>();

    public HashSet<File> list(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionConfigure.getConnection();
            preparedStatement = connection.prepareStatement("SELECT id, URL, name FROM contain, file WHERE parentId = ? AND childId = id");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                setOfFiles.add(new File(resultSet.getInt("id"), resultSet.getString("URL"),resultSet.getString("name")));
            }
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
        return setOfFiles;
    }

}
