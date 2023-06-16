package ga_wia1002;

import java.util.*;

public class RestaurantInfo {
    // Create restaurants
    private JOJOLandsRestaurant jadeGarden ;
    private JOJOLandsRestaurant cafeDeuxMagots ;
    private JOJOLandsRestaurant trattoriaTrussardi ;
    private JOJOLandsRestaurant libeccio ;
    private JOJOLandsRestaurant savageGarden ;

    //create hashMap to store price of each food
    HashMap<String, Double> jadeGarden_Price = new HashMap<>();
    HashMap<String, Double> savageGarden_Price = new HashMap<>();
    HashMap<String, Double> trattoriaTrussardi_Price = new HashMap<>();
    HashMap<String, Double> libeccio_Price = new HashMap<>();
    HashMap<String, Double> cafeDeuxMagots_Price = new HashMap<>();    

    public RestaurantInfo(int currentDay) {
        // Create restaurants
        jadeGarden = new JadeGardenRestaurant();
        cafeDeuxMagots = new CafeDeuxMagotsRestaurant();
        trattoriaTrussardi = new TrattoriaTrussardiRestaurant();
        libeccio = new LibeccioRestaurant(currentDay);
        savageGarden = new SavageGardenRestaurant(currentDay);
        
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
        
    public void processList(int currentDay,String location){
         // Create separate copies of the waitingOrder list for each restaurant
        List<Resident> jadeGardenWaitingOrder = new ArrayList<>();
        List<Resident> cafeDeuxMagotsWaitingOrder = new ArrayList<>();
        List<Resident> trattoriaTrussardiWaitingOrder = new ArrayList<>();
        List<Resident> libeccioWaitingOrder = new ArrayList<>();
        List<Resident> savageGardenWaitingOrder = new ArrayList<>();

        Preference prefer = new Preference();
        List<Resident> waitingOrder = prefer.extractLastOrdersAndRestaurants(currentDay);
        List<Resident> waitingOrderCopy = new ArrayList<>(waitingOrder);

        for (Resident resident : waitingOrderCopy) {
            // Get the last order from the orderHistory list
            List<String> orderHistory = resident.getOrderHistory();
            String lastOrder = orderHistory.get(currentDay - 1);

            // Split the order string to separate the food and restaurant names
            String[] orderParts = lastOrder.split("\\|");
            String lastRestaurant = orderParts[3].trim();

            // Add customers to waiting lists based on the last restaurant
            switch (lastRestaurant) {
                case "Jade Garden":
                    jadeGardenWaitingOrder.add(resident); // Add current resident to Jade Garden waiting list
                    break;

                case "Cafe Deux Magots":
                    cafeDeuxMagotsWaitingOrder.add(resident); // Add current resident to Cafe Deux Magots waiting list
                    break;

                case "Trattoria Trussardi":
                    trattoriaTrussardiWaitingOrder.add(resident); // Add current resident to Trattoria Trussardi waiting list
                    break;

                case "Libeccio":
                    libeccioWaitingOrder.add(resident); // Add current resident to Liberrio waiting list
                    break;

                case "Savage Garden":
                    savageGardenWaitingOrder.add(resident); // Add current resident to Savage Garden waiting list
                    break;
            }
                
        // Set the waiting lists for each restaurant, so food waiting list from other restaurant do not mix together
        jadeGarden.setWaitingList(jadeGardenWaitingOrder);
        cafeDeuxMagots.setWaitingList(cafeDeuxMagotsWaitingOrder);
        trattoriaTrussardi.setWaitingList(trattoriaTrussardiWaitingOrder);
        libeccio.setWaitingList(libeccioWaitingOrder);
        savageGarden.setWaitingList(savageGardenWaitingOrder);
        }
        
        //correct the queue order and store all the food,must execute processOders() 1st before storing, else, will get empty data 
        switch (location) {
            case "Jade Garden":                                                       
                jadeGarden.processOrders();         
                jadeGarden.storeOrder(location, currentDay);
                break;

            case "Cafe Deux Magots":
                cafeDeuxMagots.processOrders();
                cafeDeuxMagots.storeOrder(location, currentDay);
                break;

            case "Trattoria Trussardi":
                trattoriaTrussardi.processOrders();
                trattoriaTrussardi.storeOrder(location, currentDay);
                break;

            case "Libeccio":
                libeccio.processOrders();
                libeccio.storeOrder(location, currentDay);
                break;

            case "Savage Garden":
                savageGarden.processOrders();                           
                savageGarden.storeOrder(location);
                break;
        }
        //rearrange waiting list to correct queue according special order rule for each restaurant
    }

    public void displayWaitingAndProcessingList(String location){
        System.out.println("====================================================================================================");
        System.out.println("Restaurant: "+location);
        // Print waiting list before process orders (to avoid waitingOrder be cleared 1st)and processing list for each restaurant
        if (location.equals("Savage Garden")){
            savageGarden.viewWaitingList();
            savageGarden.displayOrderProcessingList();             
        }

        else if(location.equals("Cafe Deux Magots")){
            cafeDeuxMagots.viewWaitingList();
            cafeDeuxMagots.displayOrderProcessingList();               
        }

        else if(location.equals("Jade Garden")){
            jadeGarden.viewWaitingList();
            jadeGarden.displayOrderProcessingList();
        }

        else if(location.equals("Trattoria Trussardi")){
            trattoriaTrussardi.viewWaitingList();
            trattoriaTrussardi.displayOrderProcessingList();                
        }    

        else if(location.equals("Libeccio")){
            libeccio.viewWaitingList();
            libeccio.displayOrderProcessingList();                
        }

        System.out.println("====================================================================================================");
    }

    public void viewSales(String location,int userInputDay){
        switch (location) {
            case "Jade Garden":                                                              
                jadeGarden.viewSales(location,userInputDay);
                break;

            case "Cafe Deux Magots":
                cafeDeuxMagots.viewSales(location,userInputDay);
                break;

            case "Trattoria Trussardi":
                trattoriaTrussardi.viewSales(location,userInputDay);
                break;

            case "Libeccio":
                libeccio.viewSales(location, userInputDay);
                break;

            case "Savage Garden":
                savageGarden.viewSales(location,userInputDay);
                break;
        }            
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

        System.out.println("=========================================================================================================");
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
    
    //view total and average sales in JOJOLandsGame
    public void viewTotalSales(String location){
        switch (location) {
            case "Jade Garden": 
                jadeGarden.viewTotalSales(location);
                break;

            case "Cafe Deux Magots":
                cafeDeuxMagots.viewTotalSales(location);
                break;

            case "Trattoria Trussardi":
                trattoriaTrussardi.viewTotalSales(location);
                break;

            case "Libeccio":
                libeccio.viewTotalSales(location);
                break;

            case "Savage Garden":
                savageGarden.viewTotalSales(location);
                break;
        }            
    }    
    
    //view minimum sales
    public void viewMinimumSales(String location){
        switch (location) {
            case "Jade Garden": 
                jadeGarden.viewMinimumSales(location);
                break;

            case "Cafe Deux Magots":
                cafeDeuxMagots.viewMinimumSales(location);
                break;

            case "Trattoria Trussardi":
                trattoriaTrussardi.viewMinimumSales(location);
                break;

            case "Libeccio":
                libeccio.viewMinimumSales(location);
                break;

            case "Savage Garden":
                savageGarden.viewMinimumSales(location);
                break;
        }            
    } 
    
    //view max sales
    public void viewMaximumSales(String location){
        switch (location) {
            case "Jade Garden": 
                jadeGarden.viewMaximumSales(location);
                break;

            case "Cafe Deux Magots":
                cafeDeuxMagots.viewMaximumSales(location);
                break;

            case "Trattoria Trussardi":
                trattoriaTrussardi.viewMaximumSales(location);
                break;

            case "Libeccio":
                libeccio.viewMaximumSales(location);
                break;

            case "Savage Garden":
                savageGarden.viewMaximumSales(location);
                break;
        }            
    }
    
    //milagro mode
    public void MilagroMode(String location, int currentDay){
        switch (location) {
            case "Jade Garden": 
                jadeGarden.MilagroMode(location,currentDay );
                break;

            case "Cafe Deux Magots":
                cafeDeuxMagots.MilagroMode(location, currentDay);
                break;

            case "Trattoria Trussardi":
                trattoriaTrussardi.MilagroMode(location, currentDay);
                break;

            case "Libeccio":
                libeccio.MilagroMode(location, currentDay);
                break;

            case "Savage Garden":
                savageGarden.MilagroMode(location, currentDay);
                break;
        }            
    } 
    
//// tester    
//    public static void main(String[] args) {
//
//        String location = "Savage Garden";        
//        JOJOLandsGame game = new JOJOLandsGame();
//                Scanner scanner = new Scanner(System.in);
//                System.out.println("Enter Day: ");
//        int userInputDay =scanner.nextInt();
//        RestaurantInfo sc = new RestaurantInfo(userInputDay);
//        sc.processList(game.getCurrentDay(),location);
//        sc.displayWaitingAndProcessingList(location);        
//
//
//            sc.viewSales(location,userInputDay);
//       
//        System.out.println("Game currentDay: "+game.getCurrentDay());
//    }    
}


