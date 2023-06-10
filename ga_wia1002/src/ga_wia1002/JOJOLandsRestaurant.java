package ga_wia1002;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;

//IO
import java.io.PrintWriter;
import java.io.FileOutputStream;

import java.io.FileNotFoundException;
import java.io.FileInputStream;       
import java.io.IOException;

public abstract class JOJOLandsRestaurant {
    protected List<Resident> waitingList;
    protected List<Resident> orderProcessingList;
    protected String[] menu;
    JOJOLandsGame game = new JOJOLandsGame();
    int currentDay=game.getCurrentDay();
   
      //create hashMap to store price of each food
    HashMap<String, Double> jadeGarden_Price = new HashMap<>();
    HashMap<String, Double> savageGarden_Price = new HashMap<>();
    HashMap<String, Double> trattoriaTrussardi_Price = new HashMap<>();
    HashMap<String, Double> libeccio_Price = new HashMap<>();
    HashMap<String, Double> cafeDeuxMagots_Price = new HashMap<>();
    
    public JOJOLandsRestaurant(){
        waitingList=new ArrayList<>();
        orderProcessingList=new ArrayList<>();
        
        
        //store the jadeGarden price into hashmap
        jadeGarden_Price.put("Braised Chicken in Black Bean Sauce ($15.00)", 15.00);
        jadeGarden_Price.put("Braised Goose Web with Vermicelli ($21.00)", 21.00);
        jadeGarden_Price.put("Deep-fried Hiroshima Oysters ($17.00)", 17.00);
        jadeGarden_Price.put("Poached Tofu with Dried Shrimps ($12.00)", 12.00);
        jadeGarden_Price.put("Scrambled Egg White with Milk ($10.00)", 10.00);
        
             
        //store the deux magots price into map
        cafeDeuxMagots_Price.put("Sampling Matured Cheese Platter ($23.00)", 23.00);
        cafeDeuxMagots_Price.put("Spring Lobster Salad ($35.00)", 35.00);
        cafeDeuxMagots_Price.put("Spring Organic Omelette ($23.00)", 23.00);
        cafeDeuxMagots_Price.put("Truffle-flavoured Poultry Supreme ($34.00)", 34.00);
        cafeDeuxMagots_Price.put("White Asparagus ($26.00)", 26.00);
        
        //store the trattoria price into map
        trattoriaTrussardi_Price.put("Caprese Salad ($10.00)", 10.00);
        trattoriaTrussardi_Price.put("Creme caramel ($6.50)", 6.50);
        trattoriaTrussardi_Price.put("Lamb Chops with Apple Sauce ($25.00)", 25.00);
        trattoriaTrussardi_Price.put("Spaghetti alla Puttanesca ($15.00)", 15.00);
          
        //store the libeccio price into map
        libeccio_Price.put("Formaggio ($12.50)", 12.50);
        libeccio_Price.put("Ghiaccio ($1.01)", 1.01);
        libeccio_Price.put("Melone ($5.20)", 5.20);
        libeccio_Price.put("Prosciutto and Pesci ($20.23)", 20.23);
        libeccio_Price.put("Risotto ($13.14)", 13.14);
        libeccio_Price.put("Zucchero and Sale ($0.60)", 0.60);
         
        //store savagegarden price to hashmap
        savageGarden_Price.put("Abbacchio’s Tea ($1.00)", 1.00);
        savageGarden_Price.put("DIO’s Bread ($36.14)", 36.14);
        savageGarden_Price.put("Giorno’s Donuts ($6.66)", 6.66);
        savageGarden_Price.put("Joseph’s Tequila ($35.00)", 35.00);
        savageGarden_Price.put("Kakyoin’s Cherry ($3.50)", 3.50);
        savageGarden_Price.put("Kakyoin’s Porridge ($4.44)", 4.44);
        
        
        
    }

    public JOJOLandsRestaurant(List<Resident> waitingList) {
        this.waitingList =waitingList;
        orderProcessingList = new ArrayList<>();

    }

    public int getCurrentDay() {
        return currentDay;
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
    
    //store order
    public void storeOrder(String restaurant_name){
        
        try{
          //location
          String filePath="src\\ga_wia1002\\"+restaurant_name+"Order.txt";
          
    PrintWriter writer= new PrintWriter(new FileOutputStream(filePath));
    
        for (int i = 0; i < orderProcessingList.size(); i++) {
            Resident customer = orderProcessingList.get(i);
            
            //store data to txt file
            writer.printf("| %-2d | %-22s | %-3d | %-6s |%-46s |%n", i + 1, customer.getName(), customer.getAge(),
                    customer.getGender(),customer.getOrder());
        }
        
        writer.close();
        
      
        }
        
        catch (IOException ie){
            ie.printStackTrace();
        }
    }
    
    //view sales
    public void viewSales(String restaurant_name){
        
        //for hashmap
        HashMap<String, Double> restaurantPrice= new HashMap<>();
        
        //choose hashmap
        switch (restaurant_name){
            
            case "jadeGarden":
                restaurantPrice= jadeGarden_Price;
                break;
                
            case "trattoriaTrussardi":
                restaurantPrice= trattoriaTrussardi_Price;
                break;
                
            case "savageGarden":
                restaurantPrice= savageGarden_Price;
                break;
                
            case "cafeDeuxMagots":
                 restaurantPrice= cafeDeuxMagots_Price;
                break;
                
                
            case "libeccio":
                 restaurantPrice= libeccio_Price;
                break;
        }//switch
        
        
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
         System.out.printf("%-48s| %-8s| %-9s\n","|Food", "Quantity", "Price|");
         System.out.println("+-------------------------------------+----------+-------------------+");
         
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
        System.out.println("+-------------------------------------+----------+-------------------+");
                 
        //display the total sales
            System.out.printf("|%59s|$%.2f\n", "Total Sales", totalSales);
            
        System.out.println("+-------------------------------------+----------+-------------------+");

            sc.close();
     }
     
     catch (FileNotFoundException fe){
         fe.printStackTrace();
     }
        
    }
    
    
    
    
    
    
}

