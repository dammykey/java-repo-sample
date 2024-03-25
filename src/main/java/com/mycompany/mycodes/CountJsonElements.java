/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mycodes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author dakinkuolie
 */
public class CountJsonElements {
    public static void main(String[] args) {
        // Sample JSON string
        String jsonString = "{\"name\": \"John\", \"age\": 30}";

        // Create an ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Parse the JSON string into a JsonNode
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            // Count the number of elements in the JSON object
            int numElements = jsonNode.size();
            System.out.println("Number of elements in the JSON: " + numElements);
        } catch (Exception e) {
            e.printStackTrace();
        }
}
}
