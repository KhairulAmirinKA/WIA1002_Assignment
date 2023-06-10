
package ga_wia1002;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Preference {
    JOJOLandsGame game = new JOJOLandsGame();
    private int currentDay = game.getCurrentDay();
    private static final Random RANDOM = new Random();
    private  Restaurant JotaroRestaurant;
    private final List<Resident> residents;
    private final List<Restaurant> restaurants;
    private int foodCount;
    double totalFoodCost=0;
    int trattoriaFrequency=0;
    double remainingBudget=0.0;
    int i=1;
    
    //create hashMap to store price of each food
    HashMap<String, Double> jadeGarden_Price = new HashMap<>();
    HashMap<String, Double> savageGarden_Price = new HashMap<>();
    HashMap<String, Double> trattoriaTrussardi_Price = new HashMap<>();
    HashMap<String, Double> libeccio_Price = new HashMap<>();
    HashMap<String, Double> cafeDeuxMagots_Price = new HashMap<>();


// Reset trattoriaFrequency at the start of each week

    public Preference() {
        residents = new ArrayList<>();
        restaurants = new ArrayList<>();
        foodCount = 1;
    }

    public void addResident(Resident resident) {
        residents.add(resident);
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public void generateFoodOrders() {
        while (foodCount <= currentDay) {
            for (Resident resident : residents) {
                if (resident.getName().equals("Jotaro Kujo")) {

                           String previousRestaurant = getPreviousRestaurant(resident);
                           Restaurant currentRestaurant = getNextRestaurant(previousRestaurant);
//                           if(foodCount%7==1){//if Saturday, dine in with Jolyne
//                               JotaroRestaurant = currentRestaurant;
//                           }
                           
                           String food = getRandomFoodFromRestaurant(currentRestaurant);
                           resident.setOrder(food);
                           resident.addToOrderHistory(food, currentRestaurant.getName());
                       } 
                
                else if (foodCount >=1 && resident.getName().equals("Jolyne Cujoh")) {
                    if(foodCount%7==1){    //if Saturday, dine in with Jotaro
//                       Restaurant currentRestaurant = JotaroRestaurant;
//                        String food = getRandomFoodFromRestaurant(currentRestaurant);
//resident.setOrder(food);
//                        resident.addToOrderHistory(food, currentRestaurant.getName());       
//                    }
//                    else{   //other days, can eat anywhere
                        String previousRestaurant = getPreviousRestaurant(resident);
                        Restaurant currentRestaurant = getNextRestaurant(previousRestaurant);   //avoid dining at the same restaurant twice in a row
                        String food = getRandomFoodFromRestaurant(currentRestaurant);
                        resident.setOrder(food);
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
                            resident.setOrder(food);
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
                            resident.setOrder(food);
                            resident.addToOrderHistory(food, restaurantName);
                            uniqueFoods.add(foodRestaurantPair);    //to check so that no repetition
                        }
                } 

                else if (resident.getName().equals("Josuke Higashikata")) {
                    String food = getRandomFoodFromRestaurant(getRandomRestaurant());
                    double foodCost = getFoodCost(food);
                    double refund=0.0;

                    if (foodCount % 7== 1) {
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
                        
                else if (resident.getName().equals("Giorno Giovanna")) {      
                        if(foodCount%7==1){
                            trattoriaFrequency=0;
                        }
                        
                        if(trattoriaFrequency<2){
                            String food = getNewFoodAtTrattoria(foodCount);
                            trattoriaFrequency++;
                            resident.setOrder(food);
                            resident.addToOrderHistory(food, "Trattoria Trussardi");   
                        }              
                        else{
                            String randFood = getRandomFoodFromRestaurant(getNoTrattoria());
                            resident.setOrder(randFood);
                            resident.addToOrderHistory(randFood, getNoTrattoria().getName());
                        }
                }
                
                else{
                    Restaurant restaurant = getRandomRestaurant();
                    String food =getRandomFoodFromRestaurant(restaurant);
                    resident.setOrder(food);
                    resident.addToOrderHistory(food, restaurant.getName());
                }
            }
            foodCount++;
        }
    }
public List<Resident> extractLastOrdersAndRestaurants() {
    List<Resident> extractedResidents = new ArrayList<>();
    initializeRestaurant();
    getResidents();
    generateFoodOrders();
//     Loop over the populated residents list to extract data
    for (Resident resident : residents) {
        String lastOrder = "";
        String lastRestaurant = "";
        
List<String> orderHistory = resident.getOrderHistory();
// Check if the order history is not empty
if (!orderHistory.isEmpty()) {
    // Get the last order
    if (orderHistory.size() >= currentDay) {
    lastOrder = orderHistory.get(currentDay-1);
    // Split the order string to separate the food and restaurant names
    String[] orderParts = lastOrder.split("\\|");
    lastOrder = orderParts[2].trim();
        Resident extractedResident = new Resident(resident.getName(), resident.getAge(), resident.getGender(), resident.getResidentialArea(), lastOrder, resident.getOrderHistory());
        extractedResidents.add(extractedResident);
    }
}}
    return extractedResidents;
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

//    private String getRandomFoodFromRestaurant(Restaurant restaurant) {
//        List<String> availableFoods = restaurant.getAvailableFoods();
//        return availableFoods.get(RANDOM.nextInt(availableFoods.size()));
//    }
private String getRandomFoodFromRestaurant(Restaurant restaurant) {
    if (restaurant != null) {
        List<String> availableFoods = restaurant.getAvailableFoods();
        if (availableFoods != null && !availableFoods.isEmpty()) {
            return availableFoods.get(RANDOM.nextInt(availableFoods.size()));
        }
    }
    return "";  // Return an empty string or handle the null case appropriately
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

    private String getNewFoodAtTrattoria(int foodCount) {
        Restaurant trattoria = restaurants.get(0);
        List<String> availableFoods = trattoria.getAvailableFoods();
        return availableFoods.get((foodCount%4));
    }

    private double getFoodCost(String food) {
        // Extract numeric characters and decimal point
        String strPrice= food.replaceAll("[^\\d.]", "");
        double foodPrices = Double.parseDouble(strPrice);
        return foodPrices;
    }
    public void initializeRestaurant() {
        
        //jadeGarden
        List<String> jadeGardenFoods = new ArrayList<>();
        jadeGardenFoods.add("Braised Chicken in Black Bean Sauce ($15.00)");
        jadeGardenFoods.add("Braised Goose Web with Vermicelli ($21.00)");
        jadeGardenFoods.add("Deep-fried Hiroshima Oysters ($17.00)");
        jadeGardenFoods.add("Poached Tofu with Dried Shrimps ($12.00)");
        jadeGardenFoods.add("Scrambled Egg White with Milk ($10.00)");
        
        //store the price into hashmap
        jadeGarden_Price.put("Braised Chicken in Black Bean Sauce ($15.00)", 15.00);
        jadeGarden_Price.put("Braised Goose Web with Vermicelli ($21.00)", 21.00);
        jadeGarden_Price.put("Deep-fried Hiroshima Oysters ($17.00)", 17.00);
        jadeGarden_Price.put("Poached Tofu with Dried Shrimps ($12.00)", 12.00);
        jadeGarden_Price.put("Scrambled Egg White with Milk ($10.00)", 10.00);
        Restaurant jadeGarden = new Restaurant("Jade Garden", jadeGardenFoods);

        //CafeDeuxMagots
        List<String> cafeDeuxMagotsFoods = new ArrayList<>();
        cafeDeuxMagotsFoods.add("Sampling Matured Cheese Platter ($23.00)");
        cafeDeuxMagotsFoods.add("Spring Lobster Salad ($35.00)");
        cafeDeuxMagotsFoods.add("Spring Organic Omelette ($23.00)");
        cafeDeuxMagotsFoods.add("Truffle-flavoured Poultry Supreme ($34.00)");
        cafeDeuxMagotsFoods.add("White Asparagus ($26.00)");
        
        //store the price into map
        cafeDeuxMagots_Price.put("Sampling Matured Cheese Platter ($23.00)", 23.00);
        cafeDeuxMagots_Price.put("Spring Lobster Salad ($35.00)", 35.00);
        cafeDeuxMagots_Price.put("Spring Organic Omelette ($23.00)", 23.00);
        cafeDeuxMagots_Price.put("Truffle-flavoured Poultry Supreme ($34.00)", 34.00);
        cafeDeuxMagots_Price.put("White Asparagus ($26.00)", 26.00);
        Restaurant cafeDeuxMagots = new Restaurant("Cafe Deux Magots", cafeDeuxMagotsFoods);

        //TrattoriaTrussardi
        List<String> trattoriaFoods = new ArrayList<>();
        trattoriaFoods.add("Caprese Salad ($10.00)");
        trattoriaFoods.add("Creme caramel ($6.50)");
        trattoriaFoods.add("Lamb Chops with Apple Sauce ($25.00)");
        trattoriaFoods.add("Spaghetti alla Puttanesca ($15.00)");
        
        //store the price into map
        trattoriaTrussardi_Price.put("Caprese Salad ($10.00)", 10.00);
        trattoriaTrussardi_Price.put("Creme caramel ($6.50)", 6.50);
        trattoriaTrussardi_Price.put("Lamb Chops with Apple Sauce ($25.00)", 25.00);
        trattoriaTrussardi_Price.put("Spaghetti alla Puttanesca ($15.00)", 15.00);
        Restaurant trattoria = new Restaurant("Trattoria Trussardi", trattoriaFoods);

        //libeccio
        List<String> libeccioFoods = new ArrayList<>();
        libeccioFoods.add("Formaggio ($12.50)");
        libeccioFoods.add("Ghiaccio ($1.01)");
        libeccioFoods.add("Melone ($5.20)");
        libeccioFoods.add("Prosciutto and Pesci ($20.23)");
        libeccioFoods.add("Risotto ($13.14)");
        libeccioFoods.add("Zucchero and Sale ($0.60)");
        
        //store the price into map
        libeccio_Price.put("Formaggio ($12.50)", 12.50);
        libeccio_Price.put("Ghiaccio ($1.01)", 1.01);
        libeccio_Price.put("Melone ($5.20)", 5.20);
        libeccio_Price.put("Prosciutto and Pesci ($20.23)", 20.23);
        libeccio_Price.put("Risotto ($13.14)", 13.14);
        libeccio_Price.put("Zucchero and Sale ($0.60)", 0.60);
        Restaurant libeccio = new Restaurant("Liberrio", libeccioFoods);

        //savageGarden
        List<String> savageGardenFoods = new ArrayList<>();
        savageGardenFoods.add("Abbacchio’s Tea ($1.00)");
        savageGardenFoods.add("DIO’s Bread ($36.14)");
        savageGardenFoods.add("Giorno’s Donuts ($6.66)");
        savageGardenFoods.add("Joseph’s Tequila ($35.00)");
        savageGardenFoods.add("Kakyoin’s Cherry ($3.50)");
        savageGardenFoods.add("Kakyoin’s Porridge ($4.44)");
        
        //store price to hashmap
        savageGarden_Price.put("Abbacchio’s Tea ($1.00)", 1.00);
        savageGarden_Price.put("DIO’s Bread ($36.14)", 36.14);
        savageGarden_Price.put("Giorno’s Donuts ($6.66)", 6.66);
        savageGarden_Price.put("Joseph’s Tequila ($35.00)", 35.00);
        savageGarden_Price.put("Kakyoin’s Cherry ($3.50)", 3.50);
        savageGarden_Price.put("Kakyoin’s Porridge ($4.44)", 4.44);
        Restaurant savageGarden = new Restaurant("Savage Garden", savageGardenFoods);

        // Add restaurants to the generator
        addRestaurant(jadeGarden);
        addRestaurant(cafeDeuxMagots);
        addRestaurant(trattoria);
        addRestaurant(libeccio);
        addRestaurant(savageGarden);
    }

    public void printOrderHistory() {
        for (Resident resident : residents) {
            System.out.println("====================================================================================");
            System.out.println(i+"Order History for " + resident.getName());
            System.out.println("+-----+------------------------------------------------+---------------------+");
            System.out.println("| Day | Food                                           | Restaurant          |");
            System.out.println("+-----+------------------------------------------------+---------------------+");
            for (String order : resident.getOrderHistory()) {
                System.out.println(order);
            }
            System.out.println("====================================================================================");
            System.out.println();
            i++;
        }
    }

public void getResidents() {
    try {
        BufferedReader reader = new BufferedReader(new FileReader("src\\ga_wia1002\\residents.csv"));
        String line;
        boolean headerSkipped = false;

        while ((line = reader.readLine()) != null) {
            if (!headerSkipped) {
                // Skip the header line
                headerSkipped = true;
                continue;
            }

            String[] residentData = line.split(",");
            String name = residentData[0].trim();
            int age;
            if(residentData[1].trim().equals("N/A"))
                age=-1;
            else
                age = Integer.parseInt(residentData[1].trim());
            String gender = residentData[2].trim();
            Resident resident = new Resident(name);
            resident.setAge(age);
            resident.setGender(gender);
            addResident(resident);
        }

        reader.close();
    } catch (IOException e) {
        e.printStackTrace();
        return;
    }
}
    
    public void getPreference() {
        initializeRestaurant();
        getResidents();

        // Generate food orders
        generateFoodOrders();
//assignResidentsToRestaurants(residents, restaurants);

        // Print the order history for each resident
        printOrderHistory();

    }


    public static void main(String[] args) {
        Preference generator = new Preference();
        generator.getPreference();

    }
}

