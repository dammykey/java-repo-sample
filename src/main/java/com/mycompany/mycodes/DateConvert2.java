/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mycodes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author dakinkuolie
 */
public class DateConvert2 {
    public static void main(String[] args) {
        
   
    // The date string to be converted
        String dateString = "Jun 22 2023";

        // Define the date format to match the input string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

        // Parse the date string into a LocalDate object
        LocalDate date = LocalDate.parse(dateString, formatter);

        // Output the result
        System.out.println("Parsed date: " + date);
    }
}
