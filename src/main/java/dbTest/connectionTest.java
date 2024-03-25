/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbTest;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author dakinkuolie
 */
public class connectionTest {
    public static void main(String[] args) {
        Connection conn = null;
  
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Load the Sybase JDBC driver
         //   Class.forName("com.sybase.jdbc4.jdbc.SybDriver");

            // Establish the connection
            String url = "jdbc:jtds:sybase://10.70.1.142:10000/banking";
            String username = "sa";
            String password = "neptune";
            
            String excelFilePath = "C:\\Users\\dakinkuolie\\Documents\\office\\MIS\\reports\\reportIns.xls";
            conn = DriverManager.getConnection(url, username, password);

            if (conn != null) {
                System.out.println("Connected to the database.");
                // Perform database operations here
                // Create a Statement object to execute SQL queries
                stmt = conn.createStatement();

                String sql = GenericSqlStatement.insuranceQuery;
                // String sql = "SELECT * FROM banking";
                rs = stmt.executeQuery(sql);

                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("insurance-report");

                writeHeaderLine(sheet);

                writeDataLines(rs, workbook, sheet);

                FileOutputStream outputStream = new FileOutputStream(excelFilePath);
                workbook.write(outputStream);
                workbook.close();

            stmt.close();
               // System.out.println("List of databases:");
        //  System.out.println("Branch No "+"   "+" Account Number");
//               while (rs.next()) {
//                   
//                // Retrieve column values from the result set
//                // Example: int id = rs.getInt("id");
//                // Example: String name = rs.getString("name");
//                // Example: double price = rs.getDouble("price");
//                // Adjust the column names ("id", "name", "price") as per your table schema
//                // Print or process retrieved data as needed
//                  // System.out.println( rs.getString("branch_no") + "       "+  rs.getString("acct_no"));
//                   System.out.println(rs.getString("status"));
//            }
            } else {
                System.out.println("Failed to make connection.");
            }
        } catch (SQLException e) {
            System.out.println("Sybase JDBC driver not found.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Connection failed. Check output console.");
            e.printStackTrace();
        } 
 
    }
    
    private static void writeHeaderLine(XSSFSheet sheet) {
 
        Row headerRow = sheet.createRow(0);
 
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("branch_no"); 
        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("rim_no"); 
         headerCell = headerRow.createCell(2);
        headerCell.setCellValue("title_1"); 
         headerCell = headerRow.createCell(3);
         headerCell.setCellValue("acct_no");  
         headerCell = headerRow.createCell(4);
         headerCell.setCellValue("Insurance_Type");         
             headerCell = headerRow.createCell(5);
        headerCell.setCellValue("Premium_Type"); 
         headerCell = headerRow.createCell(6);
        headerCell.setCellValue("Tenor"); 
         headerCell = headerRow.createCell(7);
         headerCell.setCellValue("effective_dt");  
        headerCell = headerRow.createCell(8);
        headerCell.setCellValue("Premium_Generated"); 
         headerCell = headerRow.createCell(9);
         headerCell.setCellValue("Premium_Paid");
    }
    
    
    
    private static void writeDataLines(ResultSet result, XSSFWorkbook workbook,
            XSSFSheet sheet) throws SQLException {
        int rowCount = 1;
 
        while (result.next()) {
            String branch_no = result.getString("branch_no");
             String rim_no = result.getString("rim_no");
            String title_1 = result.getString("title_1");
              String acct_no = result.getString("acct_no");
                String Insurance_Type = result.getString("Insurance_Type");
                  String Premium_Type = result.getString("Premium_Type");
                    String Tenor = result.getString("Tenor");
                      String effective_dt = result.getString("effective_dt");
   String Premium_Generated = result.getString("Premium_Generated");
                      String Premium_Paid = result.getString("Premium_Paid");
 
            Row row = sheet.createRow(rowCount++);
 
            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(branch_no);
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(rim_no);
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(title_1);
               cell = row.createCell(columnCount++);
               
               cell.setCellValue(acct_no);
               cell = row.createCell(columnCount++);
               
               cell.setCellValue(Insurance_Type);
               cell = row.createCell(columnCount++);
               
               cell.setCellValue(Premium_Type);
               cell = row.createCell(columnCount++);
               
               cell.setCellValue(Tenor);
               cell = row.createCell(columnCount++);
               
               cell.setCellValue(effective_dt);
               cell = row.createCell(columnCount++);
                cell.setCellValue(Premium_Generated);
               cell = row.createCell(columnCount++);
                cell.setCellValue(Premium_Paid);
               cell = row.createCell(columnCount++);
                
 
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper creationHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
            cell.setCellStyle(cellStyle);
 
          //  cell.setCellValue(timestamp);
 
          //  cell = row.createCell(columnCount++);
         //   cell.setCellValue(rating);
 
        //    cell = row.createCell(columnCount);
          //  cell.setCellValue(comment);
 
        }
    }
    
    }
