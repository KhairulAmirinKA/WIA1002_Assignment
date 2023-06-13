/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Khairul<Khairul Amirin UM>
 */
public class Restaurant_Experiment {
    public void MilagroMode(String restaurant_name,int currentDay){
        
        Scanner sc1= new Scanner(System.in);
        
        int choice=0;
        
        while (choice!=3){
        System.out.println("[1] Modify Food Prices");
        System.out.println("[2] View Sales Information");
        System.out.println("[3] Exit Milagro Man");
        
        System.out.print("Select: ");
        choice= sc1.nextInt();
        
        
    
        //load hashmap of milagro
        HashMap<String, Double> restaurantPrice= milagroManMap(restaurant_name); 
        
         if (choice==2){
        //read file
        try{
             
          //location
          String filePath="src\\ga_wia1002\\"+restaurant_name+"Order.txt";
          
         //read
         Scanner sc= new Scanner(new FileInputStream(filePath));
         
         //map to store the quantity of food
         HashMap<String, Integer> foodQuantityMap= new HashMap<>();
         
         //read from txt
         while (sc.hasNextLine()) {
                String line = sc.nextLine();
                
                //split
                String[] orderData = line.split("\\|");
                
                if (orderData.length >= 5) {
                    
                    //get food name
                    String foodName = orderData[5].trim();
                    
                    //calculate the quantity of each food
                    foodQuantityMap.put(foodName, foodQuantityMap.getOrDefault(foodName, 0) + 1);
                    
                    //get the price of food
                    double price = restaurantPrice.getOrDefault(foodName, 0.00);
                      
                }//if
            }//while
         
         //display food, quantity and price
         System.out.println("===================================================================================================");
         System.out.println("Restaurant: "+restaurant_name+" (Milagro Man Mode)");
         System.out.println("Day "+currentDay+" Sales");
         System.out.println("+-----------------------------------------------+-------------------+");
         System.out.printf("%-48s| %-9s| %-11s\n","|Food", "Quantity", "Price  |");
         System.out.println("+-----------------------------------------------+-------------------+");
        
         //calc total sales
         double totalSales=0; 
         
         
         // Displaying the food quantity
        for (String food : foodQuantityMap.keySet()) {
            
            //get quantity
            int quantity = foodQuantityMap.getOrDefault(food,0);
            
            //calculate price. quantity x price per unit  
            double price = quantity * restaurantPrice.getOrDefault(food,0.0);
            
            //calc total sales
            totalSales+= price;
            
            //display food, quantity and price
            System.out.printf("| %-45s | %-8d | $%-6.2f|%n", food, quantity, price);
           
        }// loop food name and quantity
        System.out.println("+-----------------------------------------------+-------------------+");
                 
        //display the total sales
            System.out.printf("|%57s | $%.2f|\n", "Total Sales", totalSales);
            
        System.out.println("+-----------------------------------------------+-------------------+");

            sc.close();
     }
     
     catch (FileNotFoundException fe){
         fe.printStackTrace();
     }
        
        }
    }
        
    }
    
    //view the modified sales using milagro 
    public void viewSalesMilagro(String restaurant_name,int currentDay){
        
        //load hashmap of milagro
        HashMap<String, Double> restaurantPrice= milagroManMap(restaurant_name);
   
        //read file
        try{
             
          //location
          String filePath="src\\ga_wia1002\\"+restaurant_name+"Order.txt";
          
         //read
         Scanner sc= new Scanner(new FileInputStream(filePath));
         
         //map to store the quantity of food
         HashMap<String, Integer> foodQuantityMap= new HashMap<>();
         
         //read from txt
         while (sc.hasNextLine()) {
                String line = sc.nextLine();
                
                //split
                String[] orderData = line.split("\\|");
                
                if (orderData.length >= 5) {
                    
                    //get food name
                    String foodName = orderData[5].trim();
                    
                    //calculate the quantity of each food
                    foodQuantityMap.put(foodName, foodQuantityMap.getOrDefault(foodName, 0) + 1);
                    
                    //get the price of food
                    double price = restaurantPrice.getOrDefault(foodName, 0.00);
                      
                }//if
            }//while
         
         //display food, quantity and price
         System.out.println("===================================================================================================");
         System.out.println("Restaurant: "+restaurant_name+" (Milagro Man Mode)");
         System.out.println("Day "+currentDay+" Sales");
         System.out.println("+-----------------------------------------------+-------------------+");
         System.out.printf("%-48s| %-9s| %-11s\n","|Food", "Quantity", "Price  |");
         System.out.println("+-----------------------------------------------+-------------------+");
        
         //calc total sales
         double totalSales=0; 
         
         
         // Displaying the food quantity
        for (String food : foodQuantityMap.keySet()) {
            
            //get quantity
            int quantity = foodQuantityMap.getOrDefault(food,0);
            
            //calculate price. quantity x price per unit  
            double price = quantity * restaurantPrice.getOrDefault(food,0.0);
            
            //calc total sales
            totalSales+= price;
            
            //display food, quantity and price
            System.out.printf("| %-45s | %-8d | $%-6.2f|%n", food, quantity, price);
           
        }// loop food name and quantity
        System.out.println("+-----------------------------------------------+-------------------+");
                 
        //display the total sales
            System.out.printf("|%57s | $%.2f|\n", "Total Sales", totalSales);
            
        System.out.println("+-----------------------------------------------+-------------------+");

            sc.close();
     }
     
     catch (FileNotFoundException fe){
         fe.printStackTrace();
     }
        
    }
}
