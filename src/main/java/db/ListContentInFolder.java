package db;

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
public class ListContentInFolder {

    private static HashSet<Integer> setOfIds = new HashSet<Integer>();

    public static void add(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionConfigure.getConnection();
            preparedStatement = connection.prepareStatement("SELECT childId FROM contain WHERE parentId = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idChild = resultSet.getInt("childId");
                setOfIds.add(idChild);
            }
            for (Integer idChild : setOfIds) {

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
