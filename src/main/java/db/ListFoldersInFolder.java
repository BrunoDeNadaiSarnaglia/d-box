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

    private static HashSet<Folder> setOfFolders = new HashSet<Folder>();

    public static HashSet<Folder> list(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionConfigure.getConnection();
            preparedStatement = connection.prepareStatement("SELECT id, name FROM folder WHERE id IN (SELECT childId FROM contain WHERE parentID = ?)");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            System.out.println("resultset " + resultSet.toString());
            while(resultSet.next()) {
                System.out.println(id + " : " + resultSet.getInt("id"));
                setOfFolders.add(new Folder(resultSet.getInt("id"), resultSet.getString("name")));
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
        return setOfFolders;
    }

}
