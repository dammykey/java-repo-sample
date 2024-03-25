/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mycodes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author dakinkuolie
 */
public class DateConversion {
    public static void main(String[] args) {
        String inputDate = "02-04-1989";
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = inputFormat.parse(inputDate);
            String outputDate = outputFormat.format(date);
            System.out.println("Input Date: " + inputDate);
            System.out.println("Converted Date: " + outputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
