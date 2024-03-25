/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.mycodes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author dakinkuolie
 */
public class MyCodes {

    public static void main(String[] args) {
      String inputDate = "July 22 2023"; // Replace this with your date string in "month day year" format
        String outputFormat = "yyyy-MM-dd";

        // Parse the input date string
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMMM d yyyy");
        LocalDate date = LocalDate.parse(inputDate, inputFormatter);

        // Format the date to the desired format
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputFormat);
        String outputDate = date.format(outputFormatter);

        System.out.println("Input date: " + inputDate);
        System.out.println("Output date: " + outputDate);
    }
}
