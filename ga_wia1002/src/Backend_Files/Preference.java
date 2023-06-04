
package ga_wia1002;
import java.util.*;

public class Preference {
    private static final int DAYS_TO_GENERATE = 30;
    private static final Random RANDOM = new Random();
    private static Restaurant JotaroRestaurant;
    private final List<Resident> residents;
    private final List<Restaurant> restaurants;
    private int currentDay;
    double totalFoodCost=0;
    int trattoriaFrequency=0;
    double remainingBudget=100.0;
// Reset trattoriaFrequency at the start of each week

    public Preference() {
        residents = new ArrayList<>();
        restaurants = new ArrayList<>();
        currentDay = 1;
    }

    public void addResident(Resident resident) {
        residents.add(resident);
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public void generateFoodOrders() {
        while (currentDay <= DAYS_TO_GENERATE) {
            for (Resident resident : residents) {
                if (currentDay >=1 && resident.getName().equals("Jolyne Cujoh")) {
                    if(currentDay%7==0){    //if Saturday, dine in with Jotaro
                       Restaurant currentRestaurant = JotaroRestaurant;
                        String food = getRandomFoodFromRestaurant(currentRestaurant);
                        resident.addToOrderHistory(food, currentRestaurant.getName());       
                    }
                    else{   //other days, can eat anywhere
                        String previousRestaurant = getPreviousRestaurant(resident);
                        Restaurant currentRestaurant = getNextRestaurant(previousRestaurant);   //avoid dining at the same restaurant twice in a row
                        String food = getRandomFoodFromRestaurant(currentRestaurant);
                        resident.addToOrderHistory(food, currentRestaurant.getName());
                        } 
                } 

                else if (resident.getName().equals("Jonathan Joestar")) {
                    Set<String> uniqueFoods = new HashSet<>();
                    
                        Restaurant restaurant = getRandomRestaurant();
                        String randomFood = getRandomFoodFromRestaurant(restaurant);
                        String foodRestaurantPair = randomFood + "_" + restaurant.getName();
                        if (!uniqueFoods.contains(foodRestaurantPair)) {
                            String[] pair = foodRestaurantPair.split("_");
                            String food = pair[0];
                            String restaurantName = pair[1];
                            resident.addToOrderHistory(food, restaurantName);
                            uniqueFoods.add(foodRestaurantPair);
                        }
                    }

                else if (resident.getName().equals("Joseph Joestar")) {
                    Set<String> uniqueFoods = new HashSet<>();
                    
                        Restaurant restaurant = getRandomRestaurant();
                        String randomFood = getRandomFoodFromRestaurant(restaurant);
                        String foodRestaurantPair = randomFood + "_" + restaurant.getName();
                        if (!uniqueFoods.contains(foodRestaurantPair)) {
                            String[] pair = foodRestaurantPair.split("_");
                            String food = pair[0];
                            String restaurantName = pair[1];
                            resident.addToOrderHistory(food, restaurantName);
                            uniqueFoods.add(foodRestaurantPair);    //to check so that no repetition
                        }
                } 
                
                else if (resident.getName().equals("Jotaro Kujo")) {

                    String previousRestaurant = getPreviousRestaurant(resident);
                    Restaurant currentRestaurant = getNextRestaurant(previousRestaurant);
                    if(currentDay%7==0){
                        JotaroRestaurant = currentRestaurant;
                    }
                    String food = getRandomFoodFromRestaurant(currentRestaurant);
                    resident.addToOrderHistory(food, currentRestaurant.getName());
                } 

                else if (resident.getName().equals("Josuke Higashikata")) {
                    String food = getRandomFoodFromRestaurant(getRandomRestaurant());
                    double foodCost = getFoodCost(food);
                    double refund=0.0;

                    if (currentDay % 7== 0) {
                        refund+= 100.0;
                    }
                 
                    if (foodCost <= remainingBudget) {
                        remainingBudget =remainingBudget+refund- foodCost;
                        resident.addToOrderHistory(food, getRandomRestaurant().getName());
                    } else {
                        remainingBudget =remainingBudget+refund- 0.6;
                        resident.addToOrderHistory("Zucchero and Sale ($0.60)", "Liberrio");
                    }
                }


                
                if (currentDay % 8 == 1) {
                            trattoriaFrequency = 0;
                }
                
                else if (resident.getName().equals("Giorno Giovanna")) {                        
                        if(trattoriaFrequency<2){
                            String food = getNewFoodAtTrattoria(currentDay);
                            trattoriaFrequency++;
                            resident.addToOrderHistory(food, "Trattoria Trussardi");   
                        }              
                        else{
                            String randFood = getRandomFoodFromRestaurant(getNoTrattoria());
                            resident.addToOrderHistory(randFood, getNoTrattoria().getName());
                        }
                }
            }
            currentDay++;
        }
    }


    private String getPreviousRestaurant(Resident resident) {
        List<String> orderHistory = resident.getOrderHistory();
        if (!orderHistory.isEmpty()) {  //have order History
            String[] parts = orderHistory.get(orderHistory.size() - 1).split("\\|"); //get last element
            return parts[3].trim(); //[0] is spacing, [1] is no, [2] is menu, [3] is restaurant name
        }
        return "";
    }

    private Restaurant getRandomRestaurant() {
        return restaurants.get(RANDOM.nextInt(restaurants.size()));
    }

    private Restaurant getNoTrattoria() {
        return restaurants.get(RANDOM.nextInt(restaurants.size()-1)+1);
    }

    private String getRandomFoodFromRestaurant(Restaurant restaurant) {
        List<String> availableFoods = restaurant.getAvailableFoods();
        return availableFoods.get(RANDOM.nextInt(availableFoods.size()));
    }

    private Restaurant getNextRestaurant(String previousRestaurant) {
        List<Restaurant> availableRestaurants = new ArrayList<>(restaurants);
        int currentIndex = -1;
        for (int i = 0; i < availableRestaurants.size(); i++) {
            if (availableRestaurants.get(i).getName().equals(previousRestaurant)) { //found the previous restaurant
                currentIndex = i;   //save the index of the restaurant
                break;
            }
        }
        if (currentIndex != -1) {   //not found the index of previous restaurant
            int nextIndex = (currentIndex + 1) % availableRestaurants.size();
            return availableRestaurants.get(nextIndex);
        }
        return availableRestaurants.get(0);
    }

    private String getNewFoodAtTrattoria(int currentDay) {
        Restaurant trattoria = restaurants.get(0);
        List<String> availableFoods = trattoria.getAvailableFoods();
        return availableFoods.get((currentDay%4));
    }

    private double getFoodCost(String food) {
        // Extract numeric characters and decimal point
        String strPrice= food.replaceAll("[^\\d.]", "");
        double foodPrices = Double.parseDouble(strPrice);
        return foodPrices;
    }

    public static void main(String[] args) {
        Preference generator = new Preference();

        // Create residents
        Resident jonathan = new Resident("Jonathan Joestar");
        Resident joseph = new Resident("Joseph Joestar");
        Resident jotaro = new Resident("Jotaro Kujo");
        Resident josuke = new Resident("Josuke Higashikata");
        Resident giorno = new Resident("Giorno Giovanna");
        Resident jolyne = new Resident("Jolyne Cujoh");


        // Create restaurants
        List<String> jadeGardenFoods = new ArrayList<>();
        jadeGardenFoods.add("Braised Chicken in Black Bean Sauce ($15.00)");
        jadeGardenFoods.add("Braised Goose Web with Vermicelli ($21.00)");
        jadeGardenFoods.add("Deep-fried Hiroshima Oysters ($17.00)");
        jadeGardenFoods.add("Poached Tofu with Dried Shrimps ($12.00)");
        jadeGardenFoods.add("Scrambled Egg White with Milk ($10.00)");
        Restaurant jadeGarden = new Restaurant("Jade Garden", jadeGardenFoods);

        List<String> cafeDeuxMagotsFoods = new ArrayList<>();
        cafeDeuxMagotsFoods.add("Sampling Matured Cheese Platter ($23.00)");
        cafeDeuxMagotsFoods.add("Spring Lobster Salad ($35.00)");
        cafeDeuxMagotsFoods.add("Spring Organic Omelette ($23.00)");
        cafeDeuxMagotsFoods.add("Truffle-flavoured Poultry Supreme ($34.00)");
        cafeDeuxMagotsFoods.add("White Asparagus ($26.00)");
        Restaurant cafeDeuxMagots = new Restaurant("Cafe Deux Magots", cafeDeuxMagotsFoods);

        List<String> trattoriaFoods = new ArrayList<>();
        trattoriaFoods.add("Caprese Salad ($10.00)");
        trattoriaFoods.add("Creme caramel ($6.50)");
        trattoriaFoods.add("Lamb Chops with Apple Sauce ($25.00)");
        trattoriaFoods.add("Spaghetti alla Puttanesca ($15.00)");
        Restaurant trattoria = new Restaurant("Trattoria Trussardi", trattoriaFoods);

        List<String> liberrioFoods = new ArrayList<>();
        liberrioFoods.add("Formaggio ($12.50)");
        liberrioFoods.add("Ghiaccio ($1.01)");
        liberrioFoods.add("Melone ($5.20)");
        liberrioFoods.add("Prosciutto and Pesci ($20.23)");
        liberrioFoods.add("Risotto ($13.14)");
        liberrioFoods.add("Zucchero and Sale ($0.60)");
        Restaurant liberrio = new Restaurant("Liberrio", liberrioFoods);

        List<String> savageGardenFoods = new ArrayList<>();
        savageGardenFoods.add("Abbacchio’s Tea ($1.00)");
        savageGardenFoods.add("DIO’s Bread ($36.14)");
        savageGardenFoods.add("Giorno’s Donuts ($6.66)");
        savageGardenFoods.add("Joseph’s Tequila ($35.00)");
        savageGardenFoods.add("Kakyoin’s Cherry ($3.50)");
        savageGardenFoods.add("Kakyoin’s Porridge ($4.44)");
        Restaurant savageGarden = new Restaurant("Savage Garden", savageGardenFoods);

        // Add residents and restaurants to the generator
        generator.addResident(jonathan);
        generator.addResident(joseph);
        generator.addResident(jotaro);
        generator.addResident(josuke);
        generator.addResident(giorno);
        generator.addResident(jolyne);

        generator.addRestaurant(trattoria);
        generator.addRestaurant(jadeGarden);
        generator.addRestaurant(cafeDeuxMagots);
        generator.addRestaurant(savageGarden);
        generator.addRestaurant(liberrio);

        // Generate food orders
        generator.generateFoodOrders();


        // Print the order history for each resident
        for (Resident resident : generator.residents) {
                System.out.println("====================================================================================");
                System.out.println("Order History for " + resident.getName());
                System.out.println("+-----+------------------------------------------------+---------------------+");
                System.out.println("| Day | Food                                           | Restaurant          |");
                System.out.println("+-----+------------------------------------------------+---------------------+");
                for (String order : resident.getOrderHistory()) {
                    System.out.println(order);
                }
                System.out.println("====================================================================================");
                System.out.println();                
            }

        }
    }

