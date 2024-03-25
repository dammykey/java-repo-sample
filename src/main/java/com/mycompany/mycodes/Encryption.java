/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mycodes;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author dakinkuolie
 */
public class Encryption {
    public static void main(String[] args) throws Exception {
        // Generate a secret key for encryption and decryption
        SecretKey secretKey = generateSecretKey();

        // Your plaintext message
        String plaintext = "Hello, World!";

        // Encrypt the plaintext
        String encryptedText = encryptString(plaintext, secretKey);

        // Decrypt the ciphertext
        String decryptedText = decryptString(encryptedText, secretKey);

        // Print results
        System.out.println("Original Text: " + plaintext);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
        System.out.println("secretKey : " + secretKey);
    }
    public static SecretKey generateSecretKey() throws Exception {
        // Generate a secret key using AES algorithm
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256); // Key size (128, 192, or 256 bits)
        return keyGenerator.generateKey();
    }

    public static String encryptString(String input, SecretKey secretKey) throws Exception {
        // Create a cipher for encryption
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Encrypt the input string
        byte[] encryptedBytes = cipher.doFinal(input.getBytes());

        // Encode the encrypted bytes as a Base64 string
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decryptString(String input, SecretKey secretKey) throws Exception {
        // Create a cipher for decryption
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decode the Base64 string to get the encrypted bytes
        byte[] encryptedBytes = Base64.getDecoder().decode(input);

        // Decrypt the encrypted bytes
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        // Convert the decrypted bytes back to a string
        return new String(decryptedBytes);
    }
}
