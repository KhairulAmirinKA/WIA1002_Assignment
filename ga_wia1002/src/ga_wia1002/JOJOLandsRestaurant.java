package ga_wia1002;

import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public abstract class JOJOLandsRestaurant {
    protected List<Resident> waitingList;
    protected List<Resident> orderProcessingList;
    protected JOJOLandsGame game;

    //create hashMap to store price of each food
    HashMap<String, Double> jadeGarden_Price = new HashMap<>();
    HashMap<String, Double> savageGarden_Price = new HashMap<>();
    HashMap<String, Double> trattoriaTrussardi_Price = new HashMap<>();
    HashMap<String, Double> libeccio_Price = new HashMap<>();
    HashMap<String, Double> cafeDeuxMagots_Price = new HashMap<>();
    
    public JOJOLandsRestaurant(){
        waitingList=new ArrayList<>();
        orderProcessingList=new ArrayList<>();
        game = new JOJOLandsGame();
 
        //store the jadeGarden price into hashmap
        jadeGarden_Price.put("Braised Chicken in Black Bean Sauce", 15.00);
        jadeGarden_Price.put("Braised Goose Web with Vermicelli", 21.00);
        jadeGarden_Price.put("Deep-fried Hiroshima Oysters", 17.00);
        jadeGarden_Price.put("Poached Tofu with Dried Shrimps", 12.00);
        jadeGarden_Price.put("Scrambled Egg White with Milk", 10.00);
        
             
        //store the deux magots price into map
        cafeDeuxMagots_Price.put("Sampling Matured Cheese Platter", 23.00);
        cafeDeuxMagots_Price.put("Spring Lobster Salad", 35.00);
        cafeDeuxMagots_Price.put("Spring Organic Omelette", 23.00);
        cafeDeuxMagots_Price.put("Truffle-flavoured Poultry Supreme", 34.00);
        cafeDeuxMagots_Price.put("White Asparagus", 26.00);
        
        //store the trattoria price into map
        trattoriaTrussardi_Price.put("Caprese Salad", 10.00);
        trattoriaTrussardi_Price.put("Creme caramel", 6.50);
        trattoriaTrussardi_Price.put("Lamb Chops with Apple Sauce", 25.00);
        trattoriaTrussardi_Price.put("Spaghetti alla Puttanesca", 15.00);
          
        //store the libeccio price into map
        libeccio_Price.put("Formaggio", 12.50);
        libeccio_Price.put("Ghiaccio", 1.01);
        libeccio_Price.put("Melone", 5.20);
        libeccio_Price.put("Prosciutto and Pesci", 20.23);
        libeccio_Price.put("Risotto", 13.14);
        libeccio_Price.put("Zucchero and Sale", 0.60);
         
        //store savagegarden price to hashmap
        savageGarden_Price.put("Abbacchio’s Tea", 1.00);
        savageGarden_Price.put("DIO’s Bread", 36.14);
        savageGarden_Price.put("Giorno’s Donuts", 6.66);
        savageGarden_Price.put("Joseph’s Tequila", 35.00);
        savageGarden_Price.put("Kakyoin’s Cherry", 3.50);
        savageGarden_Price.put("Kakyoin’s Porridge", 4.44);
    }
    
    public JOJOLandsRestaurant(List<Resident> waitingList) {
        this.waitingList =waitingList;
     //   orderProcessingList = new ArrayList<>();
    }

    public void setWaitingList(List<Resident> waitingList) {
        this.waitingList = waitingList;
    }
    
    public List<Resident> getWaitingList() {
        return waitingList;
    }


    public void addCustomer(Resident customer) {
        waitingList.add(customer);
        orderProcessingList.add(customer);
    }

    public void viewWaitingList() {
        System.out.println("Waiting List: ");
        System.out.println("+----+------------------------+-----+--------+-----------------------------------------------+");
        System.out.println("| No | Name                   | Age | Gender | Order                                         |");
        System.out.println("+----+------------------------+-----+--------+-----------------------------------------------+");

        for (int i = 0; i < waitingList.size(); i++) {
            Resident customer = waitingList.get(i);
            System.out.printf("| %-2d | %-22s | %-3d | %-6s |%-46s |%n", i + 1, customer.getName(), customer.getAge(),
                    customer.getGender(),customer.getOrder());
        }

        System.out.println("+----+------------------------+-----+--------+-----------------------------------------------+-");
    }

    public void displayOrderProcessingList() {
        System.out.println("Order Processing List: ");
        System.out.println("+----+------------------------+-----+--------+-----------------------------------------------+");
        System.out.println("| No | Name                   | Age | Gender | Order                                         |");
        System.out.println("+----+------------------------+-----+--------+-----------------------------------------------+");

        for (int i = 0; i < orderProcessingList.size(); i++) {
            Resident customer = orderProcessingList.get(i);
            System.out.printf("| %-2d | %-22s | %-3d | %-6s |%-46s |%n", i + 1, customer.getName(), customer.getAge(),
                    customer.getGender(),customer.getOrder());
        }

        System.out.println("+----+------------------------+-----+--------+-----------------------------------------------+-");
    }

    public abstract void processOrders();
    
    //store order to txt file
    public void storeOrder(String restaurant_name){
        
        try{
          //location
          String filePath="src\\ga_wia1002\\"+restaurant_name+"Order.txt";
          
    PrintWriter writer= new PrintWriter(new FileOutputStream(filePath));
    
        for (int i = 0; i < orderProcessingList.size(); i++) {
            Resident customer = orderProcessingList.get(i);
            
            String foodName= customer.getOrder().replaceAll("\\s*\\(\\$\\d+\\.\\d+\\)", "");
            //store data to txt file
            writer.printf("| %-2d | %-22s | %-3d | %-6s |%-46s |%n", i + 1, customer.getName(), customer.getAge(),
                    customer.getGender(),foodName);
        }
        
        writer.close();
        
      
        }
        
        catch (IOException ie){
            ie.printStackTrace();
        }
    }
    
    //store order to txt file based on day
    public void storeOrder(String restaurant_name, int currentDay){
        
        try{
          //eg: Jade Garden Day 1 Order.txt
          String filePath="src\\ga_wia1002\\"+restaurant_name+" Day "+ currentDay+" Order.txt";
          
    PrintWriter writer= new PrintWriter(new FileOutputStream(filePath));
    
        for (int i = 0; i < orderProcessingList.size(); i++) {
            Resident customer = orderProcessingList.get(i);
            
            String foodName= customer.getOrder().replaceAll("\\s*\\(\\$\\d+\\.\\d+\\)", "");
            //store data to txt file
            writer.printf("| %-2d | %-22s | %-3d | %-6s |%-46s |%n", i + 1, customer.getName(), customer.getAge(),
                    customer.getGender(),foodName);
        }
        
        writer.close();
        
      
        }
        
        catch (IOException ie){
            ie.printStackTrace();
        }
    }
    
    //choose hashmap based on restaurant name
    public HashMap<String, Double> chooseHashMap(String restaurant_name){
        //for hashmap
        HashMap<String, Double> restaurantPrice= new HashMap<>();
        
        //choose hashmap
        switch (restaurant_name){
            
            case "Jade Garden":
                restaurantPrice= jadeGarden_Price;
                break;
                
            case "Trattoria Trussardi":
                restaurantPrice= trattoriaTrussardi_Price;
                break;
                
            case "Savage Garden":
                restaurantPrice= savageGarden_Price;
                break;
                
            case "Cafe Deux Magots":
                 restaurantPrice= cafeDeuxMagots_Price;
                break;
                
                
            case "Libeccio":
                 restaurantPrice= libeccio_Price;
                break;
        }//switch
        
        return restaurantPrice;
    }
    
    
    //view sales from txt file
    public void viewSales(String restaurant_name,int userInputDay){
        
        //for hashmap
        HashMap<String, Double> restaurantPrice= chooseHashMap(restaurant_name);
        
        //read file
        try{
             
          //eg: Jade Garden Day 1 Order.txt
          String filePath="src\\ga_wia1002\\"+restaurant_name+" Day "+ userInputDay+" Order.txt";
          
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
         System.out.println("\nRestaurant: "+restaurant_name);
         System.out.println("Day "+userInputDay+" Sales");
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
    
    //milagro man map. will return hashmap
    public HashMap<String,Double> milagroManMap(String restaurant_name){
        
        System.out.println("Milagro Man mode");
        
        //for hashmap
        HashMap<String, Double> restaurantPrice= chooseHashMap(restaurant_name);
        Scanner sc= new Scanner(System.in);
        
        
        //modify food
        String foodName;
        double newPrice;
        
        
        System.out.print("Enter food name: ");
        foodName= sc.nextLine();
        
        System.out.print("Enter new price: $");
        newPrice= sc.nextDouble();
        
        //modify price
        restaurantPrice.put(foodName, newPrice);
        
        return restaurantPrice;
    }
    
    //total and average sales
    public void viewTotalSales(String restaurant_name){
        Scanner sc= new Scanner(System.in);
        
        //start and end day
        int startDay=0 , endDay=0;
        
        System.out.println();
        System.out.println(restaurant_name+" Total Sales");
        
        System.out.print("Enter Start Day: ");
        startDay= sc.nextInt();
        
        System.out.print("Enter End Day: ");
        endDay= sc.nextInt();
        
        //read file
        try{
            
            //file to store the total order
            String totalSaleFile= "src\\ga_wia1002\\"+restaurant_name+" Total Order.txt";
         PrintWriter writer= new PrintWriter(new FileOutputStream(totalSaleFile));
         
         
         //read all files from day 1 to end day
            for (int i=startDay; i<= endDay; i++){
                
            //location to read.
            //eg: Jade Garden Day 1 Order.txt
          String filePath="src\\ga_wia1002\\"+restaurant_name+" Day "+ i+" Order.txt";
          
         //file scanner
         Scanner fileSc= new Scanner(new FileInputStream(filePath));
         
         //loop to write
         while( fileSc.hasNextLine()){
             String line= fileSc.nextLine();
             
             //write to a new file: totalsales.txt
             writer.println(line);
             
         }//while
         
         fileSc.close();
       
         
            }//for
            
            writer.close();
        }//try
        
        catch (FileNotFoundException fe){
            fe.printStackTrace();
        }  //end of write to file
        
        
        
        //display the total sales
        //for hashmap
        HashMap<String, Double> restaurantPrice= chooseHashMap(restaurant_name);
        
        
        //read file containing total order
        try{
             
          //location
          String filePath="src\\ga_wia1002\\"+restaurant_name+" Total Order.txt";
          
         //read
         Scanner fileSc= new Scanner(new FileInputStream(filePath));
         
         //map to store the quantity of food
         HashMap<String, Integer> foodQuantityMap= new HashMap<>();
         
         //read from txt
         while (fileSc.hasNextLine()) {
                String line = fileSc.nextLine();
                
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
         System.out.printf("Total and Average Sales (Day %d - %d)\n", startDay, endDay);
         System.out.println("+-----------------------------------------------+-------------------+");
         System.out.printf("%-48s| %-9s| %-11s\n","|Food", "Quantity", "Price  |");
         System.out.println("+-----------------------------------------------+-------------------+");
         
        //calc total sales and average
         double totalSales=0;
         double averageSales=0;
         
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
            
            //calc average sales
            averageSales= totalSales/ (endDay-startDay+1);
            
            //display the average sales
            System.out.printf("|%57s | $%.2f|\n", "Average Sales", averageSales);
            
        System.out.println("+-----------------------------------------------+-------------------+");

            fileSc.close();
     }
     
     catch (FileNotFoundException fe){
         fe.printStackTrace();
     }
        
                
            
                
    }// end of viewTotalSales
     
    
    //minimum sales
    public void viewMinimumSales(String restaurant_name){
        
        //for hashmap
        HashMap<String, Double> restaurantPrice= chooseHashMap(restaurant_name);
        
        Scanner sc= new Scanner(System.in);
        
        //start and end day
        int startDay=0 , endDay=0;
        
        System.out.println();
        System.out.println(restaurant_name+" Minimum Sales");
        
        System.out.print("Enter Start Day: ");
        startDay= sc.nextInt();
        
        System.out.print("Enter End Day: ");
        endDay= sc.nextInt();
        
        //array to store sales
        double[] sales_arr=new double[endDay];
        
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
         
         fileSc.close();
       
         
            }//for startDay<=endDay
            
            
            //find the minimum sales from sales_arr
            double minimum_sales= sales_arr[0];
            int day_minimumSales=1;
            
            for (int j=0; j< sales_arr.length; j++){
                
                if (sales_arr[j]< minimum_sales){
                    minimum_sales= sales_arr[j];
                    day_minimumSales= j+1;
                }
            }
            
            System.out.printf("Minimum Sales is: $%.2f on day %d",minimum_sales, day_minimumSales);
           
            
            try{
            //display minimum sales
            //location to read.
            //eg: Jade Garden Day 1 Order.txt
           filePath="src\\ga_wia1002\\"+restaurant_name+" Day "+ day_minimumSales+" Order.txt";
          
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
         System.out.println("Day "+day_minimumSales+" Sales");
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
            }
            catch (FileNotFoundException fe2){
                fe2.printStackTrace();
                        
            }
            
            
            
        }
        }//try. 
        
        catch (FileNotFoundException fe){
            fe.printStackTrace();
        }  //end of write to file
        
        
        
            
    }// end of viewMinimumSales
    
    //minimum sales
    public void viewMaximumSales(String restaurant_name){
        
        //for hashmap
        HashMap<String, Double> restaurantPrice= chooseHashMap(restaurant_name);
        
        Scanner sc= new Scanner(System.in);
        
        //start and end day
        int startDay=0 , endDay=0;
        
        System.out.println();
        System.out.println(restaurant_name+" Minimum Sales");
        
        System.out.print("Enter Start Day: ");
        startDay= sc.nextInt();
        
        System.out.print("Enter End Day: ");
        endDay= sc.nextInt();
        
        //array to store sales
        double[] sales_arr=new double[endDay];
        
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
         
         fileSc.close();
       
         
            }//for startDay<=endDay
            
            
            //find the minimum sales from sales_arr
            double maximum_sales= sales_arr[0];
            int day_maximumSales=1;
            
            for (int j=0; j< sales_arr.length; j++){
                
                if (sales_arr[j]> maximum_sales){
                    maximum_sales= sales_arr[j];
                    day_maximumSales= j+1;
                }
            }
            
            System.out.printf("Minimum Sales is: $%.2f on day %d",maximum_sales, day_maximumSales);
           
            
            try{
            //display minimum sales
            //location to read.
            //eg: Jade Garden Day 1 Order.txt
           filePath="src\\ga_wia1002\\"+restaurant_name+" Day "+ day_maximumSales+" Order.txt";
          
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
         System.out.println("Day "+day_maximumSales+" Sales");
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
            }
            catch (FileNotFoundException fe2){
                fe2.printStackTrace();
                        
            }
            
            
            
        }
        }//try. 
        
        catch (FileNotFoundException fe){
            fe.printStackTrace();
        }  //end of write to file
        
        
        
        
        
        
        
        
            
                
    }// end of viewMaxSales
    
    
    //k hIGHEST sales
    public void view_K_Highest_Sales(String restaurant_name){
        
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
        
        double totalSales=0;
        
        
        //read file
        try{  
              
         //read all files from day 1 to end day
            for (int i=startDay; i<= endDay; i++){
                
                totalSales=0;
                
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
        
        //put totalSales according to day
        daySalesMap.put(i, totalSales);
         
         fileSc.close();
       
        }
            }//for startDay<=endDay
            
        }// end of 1st try
        
        catch  (FileNotFoundException e){
            e.printStackTrace();}
            
        
        //transformation
            
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
           
            //create an array containing the day with highest key
            int[] day_with_highestSales = new int[highestKeys.size()];
            
            //store the array with day with highest key. descending
         for (int i = 0; i < highestKeys.size(); i++) {
             day_with_highestSales[i] = highestKeys.get(i);
            }
         
           //FOR DEbugging
            System.out.println("\nANALYSIS");
            System.out.println("k= "+day_with_highestSales.length);
            for (int i: day_with_highestSales){
                System.out.println("Day "+ i);
            }
            
            try{
            //display minimum sales
            //location to read.
            
            for ( int i=0; i<k; i++){
            //eg: Jade Garden Day 1 Order.txt
           String filePath="src\\ga_wia1002\\"+restaurant_name+" Day "+ day_with_highestSales[i]+" Order.txt";
          
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
         
         
         //display food, quantity and price
         System.out.println("\nRestaurant: "+restaurant_name);
         System.out.println("Day "+day_with_highestSales[i]+" Sales");
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
            
            
       
    }// end of viewKHighest
    

    //milagroMan mode 13/6. including modify and view sales milagro
    public void MilagroMode(String restaurant_name,int currentDay){
        
        Scanner sc1= new Scanner(System.in);
        
        int choice=0;
        
        //list of choices
        while (choice!=3){
        System.out.println(restaurant_name+" (Milagro Man Mode)");
        System.out.println("[1] Modify Food Prices");
        System.out.println("[2] View Sales Information");
        System.out.println("[3] Exit Milagro Man");
        
        System.out.print("Select: ");
        choice= sc1.nextInt();
        
        //choice=3 exiting
        if (choice==3){
            System.out.println("Exiting Milagro Man Mode");
            break;
        }
        
        //temp
        HashMap<String, Double> restaurantPrice= chooseHashMap(restaurant_name);
        
        
        //modify food prices
        if (choice==1){
        //load hashmap of milagro
         restaurantPrice= milagroManMap(restaurant_name);  }
        
        //view sales with milagro man mode
        else if (choice==2){
            
        System.out.println("Sales Information");
            System.out.println("[1] View Sales");
            System.out.println("[2] View Agrregated Information");
            System.out.println("\t[A] Minimum Sales");
            System.out.println("\t[B] Maximum Sales");
            System.out.println("\t[C] Top k Highest Sales");
            System.out.println("\t[D] Total and Average Sales");
            System.out.println("[3] Exit");
            System.out.print("\nSelect: ");
            sc1.nextLine(); //to avoid input skipping
            String select =sc1.nextLine();
            //sc1.nextLine();
            System.out.println("==========================================================================================================================");
            
            switch(select){
                case("1"):
                    //[1]view Sales
                        System.out.print("Enter Day: ");
                        int userInputDay= sc1.nextInt();
                        //resInfo.viewSales(currentLocation.getName(),userInputDay);
                        break;
                case("2A"):
                    //[2A] Minimum Sales
                    //resInfo.viewMinimumSales(currentLocation.getName());
                    break;
                case("2B"):
                    //[2B]Maximum Sales
                    //resInfo.viewMaximumSales(currentLocation.getName());
                    break;
                case("2C"):
                    //[2C]Top k Highest Sales
                    //resInfo.view_K_Highest(currentLocation.getName());
                    break;
                case("2D"):
                    //[2D]Total and Average Sales
                    viewTotalSales_Milagro(restaurant_name, restaurantPrice);
                    break;      
                }//dwitch
            
        
        
        } //choice==2
    } //while choice
        
    }//end of milagro mode
    
  
    
    //total and average sales milagro
    public void viewTotalSales_Milagro(String restaurant_name, HashMap<String,Double> restaurantPrice){
        Scanner sc= new Scanner(System.in);
        
        //start and end day
        int startDay=0 , endDay=0;
        
        System.out.println();
        System.out.println(restaurant_name+" Total Sales");
        
        System.out.print("Enter Start Day: ");
        startDay= sc.nextInt();
        
        System.out.print("Enter End Day: ");
        endDay= sc.nextInt();
        
        //read file
        try{
            
            //file to store the total order
            String totalSaleFile= "src\\ga_wia1002\\"+restaurant_name+" Total Order.txt";
         PrintWriter writer= new PrintWriter(new FileOutputStream(totalSaleFile));
         
         
         //read all files from day 1 to end day
            for (int i=startDay; i<= endDay; i++){
                
            //location to read.
            //eg: Jade Garden Day 1 Order.txt
          String filePath="src\\ga_wia1002\\"+restaurant_name+" Day "+ i+" Order.txt";
          
         //file scanner
         Scanner fileSc= new Scanner(new FileInputStream(filePath));
         
         //loop to write
         while( fileSc.hasNextLine()){
             String line= fileSc.nextLine();
             
             //write to a new file: totalsales.txt
             writer.println(line);
             
         }//while
         
         fileSc.close();
       
         
            }//for
            
            writer.close();
        }//try
        
        catch (FileNotFoundException fe){
            fe.printStackTrace();
        }  //end of write to file
        
        
        
        //display the total sales
        
        
        //read file containing total order
        try{
             
          //location
          String filePath="src\\ga_wia1002\\"+restaurant_name+" Total Order.txt";
          
         //read
         Scanner fileSc= new Scanner(new FileInputStream(filePath));
         
         //map to store the quantity of food
         HashMap<String, Integer> foodQuantityMap= new HashMap<>();
         
         //read from txt
         while (fileSc.hasNextLine()) {
                String line = fileSc.nextLine();
                
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
         System.out.printf("Total and Average Sales (Day %d - %d)\n", startDay, endDay);
         System.out.println("+-----------------------------------------------+-------------------+");
         System.out.printf("%-48s| %-9s| %-11s\n","|Food", "Quantity", "Price  |");
         System.out.println("+-----------------------------------------------+-------------------+");
         
        //calc total sales and average
         double totalSales=0;
         double averageSales=0;
         
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
            
            //calc average sales
            averageSales= totalSales/ (endDay-startDay+1);
            
            //display the average sales
            System.out.printf("|%57s | $%.2f|\n", "Average Sales", averageSales);
            
        System.out.println("+-----------------------------------------------+-------------------+");

            fileSc.close();
     }
     
     catch (FileNotFoundException fe){
         fe.printStackTrace();
     }
        
                
            
                
    }// end of viewTotalSales
}

    
    
    
    
    
  
    


