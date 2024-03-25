/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reportExtraction;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class ndicImpl {
    public static void main(String[] args) {
    Connection conn = null;
  
        Statement stmt = null;
        ResultSet rs = null;
        try { String fileName = ndicQuery.todayDate();
            // Load the Sybase JDBC driver
         //   Class.forName("com.sybase.jdbc4.jdbc.SybDriver");

            // Establish the connection
            String url = "jdbc:jtds:sybase://10.70.1.142:10000/banking";
            String username = "sa";
            String password = "neptune";
            
            String excelFilePath = "C:\\Users\\dakinkuolie\\Documents\\office\\MIS\\reports\\ndic"+fileName+".xls";
            conn = DriverManager.getConnection(url, username, password);

            if (conn != null) {
                System.out.println("Connected to the database.");
                // Perform database operations here
                // Create a Statement object to execute SQL queries
                stmt = conn.createStatement();

                String sql = InstantaQuery.query;
         
                rs = stmt.executeQuery(sql);

                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("ndic-report");

                writeHeaderLine(sheet);

                writeDataLines(rs, workbook, sheet);

                FileOutputStream outputStream = new FileOutputStream(excelFilePath);
                workbook.write(outputStream);
                workbook.close();

            stmt.close();
                
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
        headerCell.setCellValue("SCVID");
        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("CustomerBVN");
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("NationalIDType");
        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("NationalIDNo");
        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("AccountNumber");
        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("Account Type");
        headerCell = headerRow.createCell(6);
        headerCell.setCellValue("Category of Account");
        headerCell = headerRow.createCell(7);
        headerCell.setCellValue("LastName");
        headerCell = headerRow.createCell(8);
        headerCell.setCellValue("MIddleName");
        headerCell = headerRow.createCell(9);
        headerCell.setCellValue("FirstName");
        headerCell = headerRow.createCell(10);
        headerCell.setCellValue("AccountOwnersTitle");
        headerCell = headerRow.createCell(11);
        headerCell.setCellValue("DateOfBirth");
        headerCell = headerRow.createCell(12);
        headerCell.setCellValue("MobileNo");
        headerCell = headerRow.createCell(13);
        headerCell.setCellValue("CustomerContactAddress");
        
      
        headerCell = headerRow.createCell(14);
        headerCell.setCellValue("AccountBalance");
        headerCell = headerRow.createCell(15);
        headerCell.setCellValue("status");
        headerCell = headerRow.createCell(16);
        headerCell.setCellValue("PledgedAsCollateral");
        headerCell = headerRow.createCell(17);
        headerCell.setCellValue("TotalBalance");
        headerCell = headerRow.createCell(18);
        headerCell.setCellValue("LoanType");
        headerCell = headerRow.createCell(19);
        headerCell.setCellValue("DateGranted");
        headerCell = headerRow.createCell(20);
        headerCell.setCellValue("LoanAmount");
        headerCell = headerRow.createCell(21);
        headerCell.setCellValue("LoanOutstanding");
        headerCell = headerRow.createCell(22);
        headerCell.setCellValue("Principal");
        headerCell = headerRow.createCell(23);
        headerCell.setCellValue("Interest");
        
        headerCell = headerRow.createCell(24);
        headerCell.setCellValue("WaiverWriteOff");
        headerCell = headerRow.createCell(25);
        headerCell.setCellValue("CashBacked");
        headerCell = headerRow.createCell(26);
        headerCell.setCellValue("CashAmount");
        headerCell = headerRow.createCell(27);
        headerCell.setCellValue("Secured");
        headerCell = headerRow.createCell(28);
        headerCell.setCellValue("CollateralType");
        headerCell = headerRow.createCell(29);
        headerCell.setCellValue("CollateralValue");
        
        
        headerCell = headerRow.createCell(30);
        headerCell.setCellValue("CollateralDescription");
        headerCell = headerRow.createCell(31);
        headerCell.setCellValue("CollateralFullAddress");
        headerCell = headerRow.createCell(32);
        headerCell.setCellValue("CollateralStatus");
        headerCell = headerRow.createCell(33);
        headerCell.setCellValue("GuarantorsName");
        
        headerCell = headerRow.createCell(34);
        headerCell.setCellValue("GuarrantorsBVN");
        headerCell = headerRow.createCell(35);
        headerCell.setCellValue("GuarrantorAddNationalIDNo");
        headerCell = headerRow.createCell(36);
        headerCell.setCellValue("GuarrantorsAddress");
        headerCell = headerRow.createCell(37);
        headerCell.setCellValue("GuarrantorsPhoneNumber");
        headerCell = headerRow.createCell(38);
        headerCell.setCellValue("AggregateLoanBalance");
     
    }
    
    
    
    private static void writeDataLines(ResultSet result, XSSFWorkbook workbook,
            XSSFSheet sheet) throws SQLException {
        int rowCount = 1;
 
        while (result.next()) {
            String branch_no = result.getString("branch_no");
            String rim_no = result.getString("rim_no");
            String description = result.getString("Rim_Class");
            String acct_no = result.getString("acct_no");
            String amt = result.getString("amt");
            String status = result.getString("status");
            String first_adv_dt = result.getString("first_adv_dt");
            String closed_dt = result.getString("closed_dt");
            String cycle = result.getString("cycle");
            String acct_type = result.getString("acct_type");
               //   
               String prodDescription = result.getString("description");
            String class_code = result.getString("class_code");
            String Name = result.getString("Name");
            String name = result.getString("RSM_on_RIM");

            Row row = sheet.createRow(rowCount++);
 
            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(branch_no);
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(rim_no);
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(description);
               cell = row.createCell(columnCount++);
               
               cell.setCellValue(acct_no);
               cell = row.createCell(columnCount++);
               
               cell.setCellValue(amt);
               cell = row.createCell(columnCount++);
               
               cell.setCellValue(status);
            cell = row.createCell(columnCount++);

            cell.setCellValue(first_adv_dt);
            cell = row.createCell(columnCount++);

            cell.setCellValue(closed_dt);
            cell = row.createCell(columnCount++);
            cell.setCellValue(cycle);
            cell = row.createCell(columnCount++);
            cell.setCellValue(acct_type);
            cell = row.createCell(columnCount++);

             cell.setCellValue(prodDescription);
            cell = row.createCell(columnCount++);
            cell.setCellValue(class_code);
            cell = row.createCell(columnCount++);
            cell.setCellValue(Name);
            cell = row.createCell(columnCount++);
            cell.setCellValue(name);
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
