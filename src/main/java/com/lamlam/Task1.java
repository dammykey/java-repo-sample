/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lamlam;

import java.util.Scanner;

/**
 *
 * @author dakinkuolie
 */
public class Task1 {
  
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please input the quantity of products");
        
          int quantity = s.nextInt(); 
          double discount;
          double p=100;
        Tester tester = new Tester();
        discount = tester.calculateDiscount(quantity, p);
        System.out.println("The initial pay is "+p+ " the discount is "+discount+ "and the total payable after discount is" +
                (p-discount));
    }
    
}

class Tester{
    public double calculateDiscount(int quant, double price){

        double discount =0.0;
                if (quant <=5){
                    discount = 0.02 * price;
                }
                else if (quant >=6  && quant <=10){
                    discount = 0.05 * price;
                }
                else if (quant >=11  && quant <=20){
                    discount = 0.1 * price;
                }
                else if (quant >=21 ){
                    discount = 0.15 * price;
                }
        return discount;
    }
}
