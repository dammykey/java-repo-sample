/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mycodes;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author dakinkuolie
 */
public class MD5Hashing {
     public static void main(String[] args) {
    String input = "1234"; // Replace this with the string you want to hash

        try {
            // Create an instance of MessageDigest with the MD5 algorithm
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Update the digest with the bytes of the input string
            md.update(input.getBytes());

            // Calculate the MD5 hash
            byte[] hashBytes = md.digest();

            // Convert the byte array to a hexadecimal representation
            String hash = new BigInteger(1, hashBytes).toString(16);

            // Make sure the hash has 32 characters (pad with leading zeros if needed)
            while (hash.length() < 32) {
                hash = "0" + hash;
            }

            System.out.println("MD5 Hash: " + hash);
        } 
catch (NoSuchAlgorithmException e) {
            System.err.println("MD5 algorithm not available.");
        }
    }
     
//     public String hashText(String text) {
//         
//        String hash="";
//        try {
//            // Create an instance of MessageDigest with the MD5 algorithm
//            MessageDigest md = MessageDigest.getInstance("MD5");
//
//            // Update the digest with the bytes of the input string
//            md.update(text.getBytes());
//
//            // Calculate the MD5 hash
//            byte[] hashBytes = md.digest();
//
//            // Convert the byte array to a hexadecimal representation
//              hash = new BigInteger(1, hashBytes).toString(16);
//
//            // Make sure the hash has 32 characters (pad with leading zeros if needed)
//            while (hash.length() < 32) {
//                hash = "0" + hash;
//            }
// 
//          
//        } 
//catch (NoSuchAlgorithmException e) {
//            System.err.println("MD5 algorithm not available.");
//        } 
//        return hash;
//     }
}
