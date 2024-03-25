/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mycodes;

/**
 *
 * @author dakinkuolie
 */
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Paths;

public class CSVToWorkbookCopy {
    public static void main(String[] args) {
//        String sourceCsvFilePath = "path/to/source.csv";
//        String targetWorkbookFilePath = "path/to/target.xlsx";
//
//        try {
//            // Read the CSV data
//            Reader reader = Files.newBufferedReader(Paths.get(sourceCsvFilePath));
//            Workbook workbook = new XSSFWorkbook();
//            Sheet sheet = workbook.createSheet("Sheet1");
//
//            // Parse the CSV data and copy to the workbook
//            String line;
//            int rowNumber = 0;
//            while ((line = ((BufferedReader) reader).readLine()) != null) {
//                String[] columns = line.split(","); // You may need to adjust the delimiter based on your CSV format
//                Row row = sheet.createRow(rowNumber);
//                for (int i = 0; i < columns.length; i++) {
//                    Cell cell = row.createCell(i);
//                    cell.setCellValue(columns[i]);
//                }
//                rowNumber++;
//            }
//
//            // Write the workbook to the target file
//            FileOutputStream fileOutputStream = new FileOutputStream(targetWorkbookFilePath);
//            workbook.write(fileOutputStream);
//
//            // Close resources
//            fileOutputStream.close();
//            workbook.close();
//            reader.close();
//
//            System.out.println("CSV copied to the workbook successfully!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
