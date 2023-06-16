package Test;

import ga_wia1002.CafeDeuxMagotsRestaurant;
import ga_wia1002.JOJOLandsGame;
import ga_wia1002.JOJOLandsRestaurant;
import ga_wia1002.JadeGardenRestaurant;
import ga_wia1002.LibeccioRestaurant;
import ga_wia1002.Preference;
import ga_wia1002.Resident;
import ga_wia1002.SavageGardenRestaurant;
import ga_wia1002.TrattoriaTrussardiRestaurant;
import java.util.*;

public class showWaitingAndProcessingList {
    public static void main(String[] args) {

        JOJOLandsGame game = new JOJOLandsGame();
        int currentDay =game.getCurrentDay();
        String location = "Jade Garden";
        showWaitingAndProcessingList sc = new showWaitingAndProcessingList();
        sc.displayWaitingAndProcessingList(currentDay,location);
    }
                
        public void displayWaitingAndProcessingList(int currentDay,String location){
                System.out.println("====================================================================================================");
                System.out.println("Restaurant: "+location);
                Preference prefer = new Preference();
                List<Resident> waitingOrder = prefer.extractLastOrdersAndRestaurants();
                List<Resident> copyWaitingOrder = new ArrayList<>(waitingOrder);

                // Create separate copies of the waitingOrder list for each restaurant
                List<Resident> jadeGardenWaitingOrder = new ArrayList<>();
                List<Resident> cafeDeuxMagotsWaitingOrder = new ArrayList<>();
                List<Resident> trattoriaTrussardiWaitingOrder = new ArrayList<>();
                List<Resident> libeccioWaitingOrder = new ArrayList<>();
                List<Resident> savageGardenWaitingOrder = new ArrayList<>();

                // Create restaurants
                JOJOLandsRestaurant jadeGarden = new JadeGardenRestaurant();
                JOJOLandsRestaurant cafeDeuxMagots = new CafeDeuxMagotsRestaurant();
                JOJOLandsRestaurant trattoriaTrussardi = new TrattoriaTrussardiRestaurant();
                JOJOLandsRestaurant libeccio = new LibeccioRestaurant();
                JOJOLandsRestaurant savageGarden = new SavageGardenRestaurant();

                for (Resident resident : copyWaitingOrder) {
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

                        case "Liberrio":
                            libeccioWaitingOrder.add(resident); // Add current resident to Liberrio waiting list
                            break;

                        case "Savage Garden":
                            savageGardenWaitingOrder.add(resident); // Add current resident to Savage Garden waiting list
                            break;

                        // Add more cases for other restaurants if needed
                    }
                }

                // Set the waiting lists for each restaurant
                ((JadeGardenRestaurant) jadeGarden).setWaitingList(jadeGardenWaitingOrder);
                ((CafeDeuxMagotsRestaurant) cafeDeuxMagots).setWaitingList(cafeDeuxMagotsWaitingOrder);
                ((TrattoriaTrussardiRestaurant) trattoriaTrussardi).setWaitingList(trattoriaTrussardiWaitingOrder);
                ((LibeccioRestaurant) libeccio).setWaitingList(libeccioWaitingOrder);
                ((SavageGardenRestaurant) savageGarden).setWaitingList(savageGardenWaitingOrder);

                // Print waiting list before process orders (to avoid waitingOrder be cleared 1st)and processing list for each restaurant
                if (location.equals("Savage Garden")){
                    savageGarden.viewWaitingList();
                    savageGarden.processOrders();
                    savageGarden.displayOrderProcessingList();             
                }

                else if(location.equals("Cafe Deux Magots")){
                    cafeDeuxMagots.viewWaitingList();
                    cafeDeuxMagots.processOrders();
                    cafeDeuxMagots.displayOrderProcessingList();               
                }

                else if(location.equals("Jade Garden")){
                    jadeGarden.viewWaitingList();
                    jadeGarden.processOrders();
                    jadeGarden.displayOrderProcessingList();
                }

                else if(location.equals("Trattoria Trussardi")){
                    trattoriaTrussardi.viewWaitingList();
                    trattoriaTrussardi.processOrders();
                    trattoriaTrussardi.displayOrderProcessingList();                
                }    

                else if(location.equals("Libeccio")){
                    libeccio.viewWaitingList();
                    libeccio.processOrders();
                    libeccio.displayOrderProcessingList();                
                }

                System.out.println("====================================================================================================");
            } 
}
    

