/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mycodes;

/**
 *
 * @author dakinkuolie
 */
public class StringManipulation {
    public static String removeCharacter(String inputString, char charToRemove) {
        StringBuilder stringBuilder = new StringBuilder();

        for (char c : inputString.toCharArray()) {
            if (c != charToRemove) {
                stringBuilder.append(c);
            }
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String originalString = "July 22, 2023";
        char charToDelete = ',';

        String modifiedString = removeCharacter(originalString, charToDelete);
        System.out.println("Modified String: " + modifiedString);
    }
}
