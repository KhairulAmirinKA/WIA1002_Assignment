/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

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
    
    //minimum sales
    public void view_K_Highest_SalesGAGAL(String restaurant_name){
        
        //for hashmap
        HashMap<String, Double> restaurantPrice= chooseHashMap(restaurant_name);
        
        Scanner sc= new Scanner(System.in);
        
        //start and end day
        int startDay=0 , endDay=0;
        
        int k;
        
        System.out.println();
        System.out.println(restaurant_name+" K-Highest Sales");
        
        System.out.print("Enter Start Day: ");
        startDay= sc.nextInt();
        
        System.out.print("Enter End Day: ");
        endDay= sc.nextInt();
        
        System.out.print("Enter k value: ");
        k= sc.nextInt();
        
        //array to store sales
        double[] sales_arr=new double[endDay];
        
        //hashmap to store sales for each day
        HashMap<Integer, Double> daySalesMap= new HashMap<>();
        
        //read file
        try{  
         
         //read all files from day 1 to end day
            for (int i=startDay; i<= endDay; i++){
                
            //location to read.
            //eg: Jade Garden Day 1 Order.txt
          String filePath="src\\ga_wia1002\\"+restaurant_name+" Day "+ i+" Order.txt";
          
         //file scanner
         Scanner fileSc= new Scanner(new FileInputStream(filePath));
         
         //map to store the quantity of food
         HashMap<String, Integer> foodQuantityMap= new HashMap<>();
         
         
         //loop to write
         while( fileSc.hasNextLine()){
             String line= fileSc.nextLine();
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
  
        //add totalSales for each day to array
        sales_arr[i-1]= totalSales;
        
        //put into hashmap
        daySalesMap.put(i, totalSales);
         
         fileSc.close();
       
         
            }//for startDay<=endDay
            
            
            //sort sales_arr
             bubbleSort(sales_arr);
             
            //sort daySalesMap according to value
            List< Map.Entry<Integer, Double> > sortedList= 
                    daySalesMap.entrySet().stream().
                    sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toList());
            
            //get key from K highest
            List<Integer> highestKeys= 
                    sortedList.subList(0, k).stream()
                    .map(entry-> entry.getKey())
                    .collect(Collectors.toList());
           
            Integer[] day_with_highestSales= (Integer[]) highestKeys.toArray();
            
            try{
            //display minimum sales
            //location to read.
            
            for (int j=1;j<=k;j++){
            //eg: Jade Garden Day 1 Order.txt
           filePath="src\\ga_wia1002\\"+restaurant_name+" Day "+ day_with_highestSales[j]+" Order.txt";
          
         //file scanner
          fileSc= new Scanner(new FileInputStream(filePath));
         
         //map to store the quantity of food
         foodQuantityMap= new HashMap<>();
         
         
         //loop to write
         while( fileSc.hasNextLine()){
             String line= fileSc.nextLine();
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
         System.out.println("\nRestaurant: "+restaurant_name);
         System.out.println("Day "+j+" Sales");
         System.out.println("+-----------------------------------------------+-------------------+");
         System.out.printf("%-48s| %-9s| %-11s\n","|Food", "Quantity", "Price  |");
         System.out.println("+-----------------------------------------------+-------------------+");
         //calc total sales
          totalSales=0; 
         
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
            }//for 
            
            }//try
            catch (FileNotFoundException fe2){
                fe2.printStackTrace();
                        
            }
            
            
            
        }
        }//try. 
        
        catch (FileNotFoundException fe){
            fe.printStackTrace();
        }  //end of write to file
        
        
        
            
    }// end of viewK-highestSales
    
}
