package db;

import rowClasses.File;
import rowClasses.Folder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashSet;

/**
 * Created by Bruno on 4/24/2015.
 */
public class DeleteFolder {

    public static void delete(Integer id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            HashSet<File> setOfFiles =  ListFilesInFolder.list(id);
            for(File file : setOfFiles){
                DeleteFile.delete(file.getId());
            }
            HashSet<Folder> setOfFolder=  ListFoldersInFolder.list(id);
            for(Folder folder : setOfFolder){
                DeleteFolder.delete(folder.getId());
            }
            connection = ConnectionConfigure.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM folder WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("DELETE FROM contain WHERE parentId = ?");
            preparedStatement.setInt(1, id);
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