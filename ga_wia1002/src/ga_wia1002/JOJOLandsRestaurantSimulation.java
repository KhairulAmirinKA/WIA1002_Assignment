package ga_wia1002;

import java.util.*;

public class JOJOLandsRestaurantSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Select: ");
        int instruction = sc.nextInt();
        

        JOJOLandsGame game = new JOJOLandsGame();
        int currentDay =game.getCurrentDay();
        String location = "Jade Garden";

        if (location.equals("Savage Garden") || location.equals("Cafe Deux Magots") || location.equals("Jade Garden")
                || location.equals("Trattoria Trussardi") || location.equals("Libeccio")) {
            if (instruction == 2) { // View Waiting List and Order Processing List
                System.out.println("====================================================================================================");
                System.out.println("Restaurant: " + location + "\n\n");

                Preference prefer = new Preference();
                List<Resident> waitingOrder = prefer.extractLastOrdersAndRestaurants(currentDay);

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
                JOJOLandsRestaurant libeccio = new LibeccioRestaurant(currentDay);
                JOJOLandsRestaurant savageGarden = new SavageGardenRestaurant(currentDay);

                for (Resident resident : waitingOrder) {
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

                        // Add more cases for other restaurants if needed
                    }
                }

                // Set the waiting lists for each restaurant, so food waiting list from other restaurant do not mix together
                jadeGarden.setWaitingList(jadeGardenWaitingOrder);
                cafeDeuxMagots.setWaitingList(cafeDeuxMagotsWaitingOrder);
                trattoriaTrussardi.setWaitingList(trattoriaTrussardiWaitingOrder);
                libeccio.setWaitingList(libeccioWaitingOrder);
                savageGarden.setWaitingList(savageGardenWaitingOrder);

                // Print waiting list before process orders (to avoid waitingOrder be cleared 1st)and processing list for each restaurant
//                System.out.println("Jade Garden:");
//                jadeGarden.viewWaitingList();
//                jadeGarden.processOrders();
//                jadeGarden.displayOrderProcessingList();
//                jadeGarden.storeOrder("Jade Garden", currentDay);
//                jadeGarden.viewSales("Jade Garden", currentDay);
//                jadeGarden.viewTotalSales("Jade Garden");
//                jadeGarden.MilagroMode("Jade Garden", currentDay);
//                
//
                System.out.println("Cafe Deux Magots:");
                cafeDeuxMagots.viewWaitingList();
                cafeDeuxMagots.processOrders();
                cafeDeuxMagots.displayOrderProcessingList();
                cafeDeuxMagots.storeOrder("Cafe Deux Magots");
                cafeDeuxMagots.viewSales("Cafe Deux Magots", currentDay);
                cafeDeuxMagots.viewTotalSales("Cafe Deux Magots");
                cafeDeuxMagots.MilagroMode("Cafe Deux Magots", currentDay);
                cafeDeuxMagots.viewMinimumSales("Cafe Deux Magots");

//                System.out.println("Trattoria Trussardi:");
//                trattoriaTrussardi.viewWaitingList();
//                trattoriaTrussardi.processOrders();
//                trattoriaTrussardi.displayOrderProcessingList();
//                trattoriaTrussardi.storeOrder("Trattoria Trussardi");
//                trattoriaTrussardi.viewSales("Trattoria Trussardi", currentDay);

//                System.out.println("Libeccio:");
//                libeccio.viewWaitingList();
//                libeccio.processOrders();
//                libeccio.displayOrderProcessingList();
//                libeccio.storeOrder("Libeccio");
//                libeccio.viewSales("Libeccio", currentDay);
//
//                System.out.println("Savage Garden:");
//                savageGarden.viewWaitingList();
//                savageGarden.processOrders();
//                savageGarden.displayOrderProcessingList();
//                savageGarden.storeOrder("Savage Garden");
//                savageGarden.viewSales("Savage Garden", currentDay);
              
                            }

                System.out.println("====================================================================================================");
            } 
        else {
                System.out.println("Select 2 to view the waiting list.");
            }
        }
    }

