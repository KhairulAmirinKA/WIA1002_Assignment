package ga_wia1002;

import java.util.*;
import java.io.*;

public class JOJOLandsRestaurant {
    protected List<Resident> waitingList;
    protected List<Resident> orderProcessingList;
    protected JOJOLandsGame game;
    protected int currentDay;
    //protected int currentDay= JOJOLandsGame.currentDay;  //

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
        //currentDay=game.getCurrentDay(); // marked 
        
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
        savageGarden_Price.put("Abbacchioâ€™s Tea", 1.00);
        savageGarden_Price.put("DIOâ€™s Bread", 36.14);
        savageGarden_Price.put("Giornoâ€™s Donuts", 6.66);
        savageGarden_Price.put("Josephâ€™s Tequila", 35.00);
        savageGarden_Price.put("Kakyoinâ€™s Cherry", 3.50);
        savageGarden_Price.put("Kakyoinâ€™s Porridge", 4.44);
    }
    
    public void viewMenu(String restaurantName) {
        Map<String, Double> menu;
        switch (restaurantName){          
            case "Jade Garden":
                menu= jadeGarden_Price;
                break;
                
            case "Trattoria Trussardi":
                menu= trattoriaTrussardi_Price;
                break;
                
            case "Savage Garden":
                menu= savageGarden_Price;
                break;
                
            case "Cafe Deux Magots":
                 menu= cafeDeuxMagots_Price;
                break;
                
            case "Libeccio":
                 menu= libeccio_Price;
                break;
            default:
                 System.out.println("Restaurant not found.");
                 return; // Exit the method if restaurant not found
        }
        System.out.println("===================================================================================================");
        System.out.println("Menu for " + restaurantName + ":");
        System.out.println("+-----------------------------------------------+-----------------+");
        System.out.println("| Menu                                          | Price($)        |");
        System.out.println("+-----------------------------------------------+-----------------+");
        for (Map.Entry<String, Double> entry : menu.entrySet()) {
            
            String foodItem = entry.getKey();    
            double price = entry.getValue();
            
            System.out.printf("|%-46s | %-15.2f |%n",foodItem , price);
        }
        
        System.out.println("+-----------------------------------------------+-----------------+");
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

    public void processOrders(){
        System.out.println("This is not a restaurant, no waiting list and processing list available");
    }
    
    //store order to txt file
    public void storeOrder(String restaurant_name, int currentDay){
        
        try{
          //location
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
    
    //view sales from txt file
    public void viewSales(String restaurant_name, int currentDay){
        
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
        
        
        //read file
        try{
             
          //location
          String filePath="src\\ga_wia1002\\"+restaurant_name+" Day "+ currentDay+" Order.txt";
          
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

    //milagro man map. will return hashmap
    public HashMap<String,Double> milagroManMap(String restaurant_name){
        
        System.out.println("Milagro Man mode");
        
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
    
    //total sales
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
        HashMap<String, Double> restaurantPrice= jadeGarden_Price;
        
        //choose hashmap according to location
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
        
        
        //modify food prices
        if (choice==1){
        //load hashmap of milagro
         restaurantPrice= milagroManMap(restaurant_name);  }
        
        //view sales with milagro man mode
        else if (choice==2){
            
        //read file
        try{
             
          //location
          String filePath="src\\ga_wia1002\\"+restaurant_name+" Day "+ currentDay+" Order.txt";
          
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
        
        } //choice==2
    } //while choice
        
    } 
    
    
    
    
    
    
}

