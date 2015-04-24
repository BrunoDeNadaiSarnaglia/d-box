package db;

import rowClasses.File;
import rowClasses.Folder;
import rowClasses.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

/**
 * Created by Bruno on 4/24/2015.
 */
public class ShowFriendFiles {

    public static HashSet<File> show(String email){
        HashSet<File> files = new HashSet<File>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionConfigure.getConnection();
            preparedStatement = connection.prepareStatement("SELECT id FROM users WHERE email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                addFilesFromFolder(id, files);
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
        return files;
    }

    private static void addFilesFromFolder(int id, HashSet<File> files){
        HashSet<File> listOfFilesInFolder = new ListFilesInFolder().list(id);
        files.addAll(listOfFilesInFolder);
        HashSet<Folder> listOfFoldersInFolder = new ListFoldersInFolder().list(id);
        if(listOfFoldersInFolder != null && listOfFoldersInFolder.size() != 0){
            for(Folder folder : listOfFoldersInFolder){
                addFilesFromFolder(folder.getId(), files);
            }
        }
    }

}
