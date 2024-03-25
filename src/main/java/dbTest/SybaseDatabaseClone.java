/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author dakinkuolie
 */
public class SybaseDatabaseClone {
    
    
     public static void main(String[] args) {
        // JDBC connection parameters for source and target databases
        String sourceUrl = "jdbc:sybase:Tds:10.70.1.29:22/reporting";
        String targetUrl = "jdbc:sybase:Tds:10.70.1.29:22/reporting";
        String sourceUsername = "sa";
        String sourcePassword = "neptune";
        
         String destUsername = "<username>";
        String destPassword = "<password>";

        try (
            Connection sourceConn = DriverManager.getConnection(sourceUrl, sourceUsername, sourcePassword);
            Connection targetConn = DriverManager.getConnection(targetUrl, destUsername, destPassword)
        ) {
            // Clone the database
            cloneDatabase(sourceConn, targetConn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void cloneDatabase(Connection sourceConn, Connection targetConn) throws SQLException {
        // Create the target database
        createTargetDatabase(targetConn);

        // Copy tables and data from source to target
        copyTables(sourceConn, targetConn);
    }

    private static void createTargetDatabase(Connection targetConn) throws SQLException {
        // Execute SQL statements to create the target database if it doesn't exist
        Statement statement = targetConn.createStatement();
        statement.executeUpdate("CREATE DATABASE <target_db>");
        statement.close();
    }

    private static void copyTables(Connection sourceConn, Connection targetConn) throws SQLException {
        // List all tables in the source database
        Statement statement = sourceConn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT name FROM sysobjects WHERE type = 'U'");

        while (resultSet.next()) {
            String tableName = resultSet.getString("name");
            copyTable(sourceConn, targetConn, tableName);
        }

        resultSet.close();
        statement.close();
    }

    private static void copyTable(Connection sourceConn, Connection targetConn, String tableName) throws SQLException {
        // Execute SQL statements to create the table in the target database
        Statement createTableStatement = targetConn.createStatement();
        ResultSet createTableResultSet = createTableStatement.executeQuery("SELECT * INTO " + tableName + " FROM " + tableName + " IN '<source_db>'");

        // Optionally, you can also copy indexes, constraints, and other schema objects

        createTableResultSet.close();
        createTableStatement.close();
    }
}
