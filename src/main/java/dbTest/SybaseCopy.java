/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dakinkuolie
 */
public class SybaseCopy {
    public static void main(String[] args) {
        // JDBC connection parameters for source and target databases
        String sourceUrl = "jdbc:sybase:Tds:<source_server>:<source_port>/<source_db>";
        String targetUrl = "jdbc:sybase:Tds:<target_server>:<target_port>/<target_db>";
        String username = "<username>";
        String password = "<password>";

        try (
            Connection sourceConn = DriverManager.getConnection(sourceUrl, username, password);
            Connection targetConn = DriverManager.getConnection(targetUrl, username, password)
        ) {
            // Copy data from source to target
            copyData(sourceConn, targetConn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void copyData(Connection sourceConn, Connection targetConn) throws SQLException {
        String selectQuery = "SELECT * FROM source_table";
        String insertQuery = "INSERT INTO target_table (column1, column2, ...) VALUES (?, ?, ...)";

        try (
            PreparedStatement selectStatement = sourceConn.prepareStatement(selectQuery);
            PreparedStatement insertStatement = targetConn.prepareStatement(insertQuery)
        ) {
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                // Retrieve data from the source
                String column1Value = resultSet.getString("column1");
                int column2Value = resultSet.getInt("column2");
                // ...

                // Insert data into the target
                insertStatement.setString(1, column1Value);
                insertStatement.setInt(2, column2Value);
                // ...

                // Execute the insert statement
                insertStatement.executeUpdate();
            }
        }
    }
}
