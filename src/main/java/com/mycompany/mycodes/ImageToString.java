/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mycodes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dakinkuolie
 */
public class ImageToString {
    public static void main(String[] args){
          try {
            Path imagePath = Paths.get("C:\\Users\\dakinkuolie\\Documents\\convert\\sign.jpg");
            byte[] imageBytes = Files.readAllBytes(imagePath);
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            System.out.println(base64Image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
