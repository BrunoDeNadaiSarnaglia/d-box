package db;


import rowClasses.Folder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Bruno de Nadai Sarnaglia <denadai2@illinois.edu>
 * @version 1.0
 */
public class ListFoldersInFolder {

<<<<<<< HEAD:src/main/java/db/ListFoldersInFolder.java
    private static HashSet<Folder> setOfFolders = new HashSet<Folder>();


    public static void query(Integer id){
=======
    private static HashSet<Integer> setOfIds = new HashSet<Integer>();

    public static void add(Integer id) {
>>>>>>> 42eaa3a2489a5ac592ff9d0c26a10a4defa8d57b:src/main/java/db/ListContentInFolder.java
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionConfigure.getConnection();
            preparedStatement = connection.prepareStatement("SELECT id, name FROM contain, folder WHERE parentId = ? AND childId = id");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
<<<<<<< HEAD:src/main/java/db/ListFoldersInFolder.java
            while(resultSet.next()) {
                int idChild= resultSet.getInt("childId");
                setOfFolders.add(new Folder(resultSet.getInt("id"), resultSet.getString("name")));
=======
            while (resultSet.next()) {
                int idChild = resultSet.getInt("childId");
                setOfIds.add(idChild);
            }
            for (Integer idChild : setOfIds) {

>>>>>>> 42eaa3a2489a5ac592ff9d0c26a10a4defa8d57b:src/main/java/db/ListContentInFolder.java
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
    }

}
