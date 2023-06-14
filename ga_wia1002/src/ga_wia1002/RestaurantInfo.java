package ga_wia1002;

import java.util.*;

public class RestaurantInfo {
    // Create restaurants
    private JOJOLandsRestaurant jadeGarden ;
    private JOJOLandsRestaurant cafeDeuxMagots ;
    private JOJOLandsRestaurant trattoriaTrussardi ;
    private JOJOLandsRestaurant libeccio ;
    private JOJOLandsRestaurant savageGarden ;

    public RestaurantInfo() {
        // Create restaurants
        jadeGarden = new JadeGardenRestaurant();
        cafeDeuxMagots = new CafeDeuxMagotsRestaurant();
        trattoriaTrussardi = new TrattoriaTrussardiRestaurant();
        libeccio = new LibeccioRestaurant();
        savageGarden = new SavageGardenRestaurant();
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
                savageGarden.storeOrder(location, currentDay);
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

    public void viewSales(String location,int currentDay){
        switch (location) {
            case "Jade Garden":                                                              
                jadeGarden.viewSales(location,currentDay);
                jadeGarden.viewTotalSales(location);
                break;

            case "Cafe Deux Magots":
                cafeDeuxMagots.viewSales(location,currentDay);
                break;

            case "Trattoria Trussardi":
                trattoriaTrussardi.viewSales(location,currentDay);
                break;

            case "Libeccio":
                libeccio.viewSales(location,currentDay);
                break;

            case "Savage Garden":
                savageGarden.viewSales(location,currentDay);
                break;
        }            
    }
    
// tester    
//    public static void main(String[] args) {
//        JOJOLandsGame game = new JOJOLandsGame();
//        int currentDay =game.getCurrentDay();
//        String location = "Jade Garden";
//        RestaurantInfo sc = new RestaurantInfo();
//        sc.processList(currentDay,location);
//        sc.displayWaitingAndProcessingList(location);
//        sc.viewSales(location,currentDay);
//    }    
}


