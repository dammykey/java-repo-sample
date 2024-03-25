/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mycodes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author dakinkuolie
 */
public class DateConversionExample {
    public static void main(String[] args) {
        String inputDate = "23 sept 2023";
        String cleaned="";
        if (inputDate.contains("sept")) {
           cleaned= check(inputDate);
            // Define the input and output date formats
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            // Parse the input date string into a Date object
            Date date = inputFormat.parse(cleaned);
            
            // Format the Date object into the desired output format
            String outputDate = outputFormat.format(date);
            
            System.out.println("Input date: " + cleaned);
            System.out.println("Output date: " + outputDate);
            System.exit(0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        }
        // Define the input and output date formats
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            // Parse the input date string into a Date object
            Date date = inputFormat.parse(inputDate);
            
            // Format the Date object into the desired output format
            String outputDate = outputFormat.format(date);
            
            System.out.println("Input date: " + inputDate);
            System.out.println("Output date: " + outputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    public static String check(String checkString) {
     

        // Character to be removed
        char charToRemove = 't';

        // Create a StringBuilder to build the modified sentence
        StringBuilder modifiedSentence = new StringBuilder();

        // Loop through each character in the input sentence
        for (char c : checkString.toCharArray()) {
            // Check if the character is equal to the one to be removed
            if (c != charToRemove) {
                // If not, append it to the modified sentence
                modifiedSentence.append(c);
            }
        }

        // Convert the StringBuilder to a String
        String result = modifiedSentence.toString();

        // Print the modified sentence
        
        System.out.println("Modified Sentence: " + result);
 return result;
    }
}
