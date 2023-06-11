
package ga_wia1002;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Preference {
    JOJOLandsGame game = new JOJOLandsGame();
    private int currentDay = game.getCurrentDay();
    private static final Random RANDOM = new Random();
    private final List<Resident> residents;
    private final List<Restaurant> restaurants;
    private int foodCount;
    double totalFoodCost=0;
    private int trattoriaFrequency=0;
    double remainingBudget=0.0;
    int foodIndex = 0;  //initialize to 1st food of the restaurant
    int resIndex=0;
    private Restaurant JotaroRestaurant = null;
    private List<Restaurant> JonathanFoodList = new ArrayList<>();
    private List<Restaurant> JosephFoodList = new ArrayList<>();
    
    //the file path of residents. and stands.csv
    static String residentFilePath="src\\ga_wia1002\\residents.csv";
    static String standFilePath="src\\ga_wia1002\\stands.csv";


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
                    if(foodCount%7==0){    //if Saturday, dine in with Jotaro,Jotaro eat in currentRestaurant
                        String food = getRandomFoodFromRestaurant(JotaroRestaurant);
                        resident.addToOrderHistory(food, JotaroRestaurant.getName());       
                    }
                    else{
                           String food = JotaroGetFood();
                           resident.addToOrderHistory(food, JotaroRestaurant.getName());
                       } 
                }
                
                else if (resident.getName().equals("Jolyne Cujoh")) {
                    if(foodCount%7==0){    //if Saturday, dine in with Jotaro,Jotaro eat in currentRestaurant
                        String food = JotaroGetFood();
                        resident.addToOrderHistory(food, JotaroRestaurant.getName());       
                    }
                    else{   //other days, can eat anywhere
                        String previousRestaurant = getPreviousRestaurant(resident);
                        Restaurant currentRestaurant = getNextRestaurant(previousRestaurant);   //avoid dining at the same restaurant twice in a row
                        String food = getRandomFoodFromRestaurant(currentRestaurant);
                        resident.addToOrderHistory(food, currentRestaurant.getName());
                        } 
                } 

                else if (resident.getName().equals("Jonathan Joestar")) {
                        Restaurant foodWithRestaurant=JonathanGetFood();
                            resident.addToOrderHistory(foodWithRestaurant.getFoodName(), foodWithRestaurant.getName());
                }

                else if (resident.getName().equals("Joseph Joestar")) {      
                        Restaurant foodWithRestaurant=JosephGetFood();
                            resident.addToOrderHistory(foodWithRestaurant.getFoodName(), foodWithRestaurant.getName());
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
                    if (trattoriaFrequency < 2) {
                        String food = getNewFoodAtTrattoria();
                        trattoriaFrequency++;
                        resident.addToOrderHistory(food, "Trattoria Trussardi");
                    }
                    else {
                        String randFood = getRandomFoodFromRestaurant(getNoTrattoria());
                        resident.addToOrderHistory(randFood, getNoTrattoria().getName());
                                }              
                            }
                else{
                    Restaurant res = getRandomRestaurant();
                    String food = getRandomFoodFromRestaurant(res);
                    resident.addToOrderHistory(food, res.getName());
                }
            }
                        foodCount++;
                        }
                    }

    public List<Restaurant> getAllAvailableFood() {
        List<Restaurant> allAvailableFood = new ArrayList<>();
        for (Restaurant res : restaurants) {
            for (String food : res.getAvailableFoods()) {
                Restaurant foodWithRestaurant = new Restaurant(food, res.getName());
                allAvailableFood.add(foodWithRestaurant);
            }
        }
        return allAvailableFood;
    }
    public Restaurant JosephGetFood() {
        if (JosephFoodList.isEmpty()) {
            JosephFoodList = new ArrayList<>(getAllAvailableFood());
            Collections.shuffle(JosephFoodList); //so that he can try food without following restaurant sequence
        }

        if (!JosephFoodList.isEmpty()) {
            Restaurant foodWithRestaurant = JosephFoodList.remove(0);
            return foodWithRestaurant;
        }

        return null;
    }

    public Restaurant JonathanGetFood() {
        if (JonathanFoodList.isEmpty()) {
            JonathanFoodList = new ArrayList<>(getAllAvailableFood());
            Collections.shuffle(JonathanFoodList); //so that he can try food without following restaurant sequence
        }

        if (!JonathanFoodList.isEmpty()) {
            Restaurant foodWithRestaurant = JonathanFoodList.remove(0);
            return foodWithRestaurant;
        }

        return null;
    }
    
    private void viewResidentProfile(String selectedResidentName) {
      StandManager standManager = new StandManager();   //read stands.csv and residents.csv
      standManager.loadStands(standFilePath);
      ResidentManager residentManager = new ResidentManager();
      residentManager.loadResidents(residentFilePath, standFilePath);
      List<Resident> residents = residentManager.getResidents(); // Get the populated list of residents
      System.out.println("====================================================================================");
      System.out.println(selectedResidentName+"'s Profile:");
      System.out.println("Name                  : " + selectedResidentName);

      // Search for the resident with the matching name
      Resident selectedResident = null;
      for (Resident resident : residents) {
          if (resident.getName().equals(selectedResidentName)) {
              selectedResident = resident;
              break;
          }
      }

      if (selectedResident != null) {   //found the resident in residents list(csv file)
          System.out.println("Age                   : "+selectedResident.getAge());
          System.out.println("Gender                : " + selectedResident.getGender());
          System.out.print("Parents               : "+selectedResident.getParents());

          System.out.println();
          Stand stand = standManager.getStandByUser(selectedResident.getName());
          if (stand != null) {
              System.out.println("Stand                 : " + stand.getName());
              System.out.println("Destructive Power     : " + stand.getDestructivePower());
              System.out.println("Speed                 : " + stand.getSpeed());
              System.out.println("Range                 : " + stand.getRange());
              System.out.println("Stamina               : " + stand.getStamina());
              System.out.println("Precision             : " + stand.getPrecision());
              System.out.println("Development Potential : " + stand.getDevelopmentPotential());
          } else {
              System.out.println("Stand information not available.");
          }
      } else {
          System.out.println("Resident not found.");
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
        Restaurant restaurant;
        do {
            restaurant = restaurants.get(RANDOM.nextInt(restaurants.size()));
        } while (restaurant.getName().equals("Trattoria Trussardi"));

        return restaurant;
    }

    private String getRandomFoodFromRestaurant(Restaurant restaurant) {
        if (restaurant != null) {
            List<String> availableFoods = restaurant.getAvailableFoods();
            if (availableFoods != null && !availableFoods.isEmpty()) {
                return availableFoods.get(RANDOM.nextInt(availableFoods.size()));
            }
        }
        return "";  // Return an empty string or handle the null case appropriately
    }
    
    private String JotaroGetFood() {
        String food;
        if (resIndex >= restaurants.size()) {   //no more restaurants, back to the 1st restaurant
            resIndex = 0;
        }

        JotaroRestaurant = restaurants.get(resIndex);   
        food = JotaroRestaurant.getAvailableFoods().get(foodIndex); //get foods from the restaurant

        foodIndex++;    //next food in the same restaurant
        if (foodIndex >= JotaroRestaurant.getAvailableFoods().size()) { //all food had been tried 
            resIndex++;     //move to next restaurant
            foodIndex = 0;  //1st food from new restaurant
        }

        return food;
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

    private String getNewFoodAtTrattoria() {
        Restaurant trattoria = null;
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equals("Trattoria Trussardi")) {
                trattoria = restaurant;
                break;
            }
        }

        List<String> availableFoods = trattoria.getAvailableFoods();
        return availableFoods.get(foodCount%4);
    }

    private double getFoodCost(String food) {
        // Extract numeric characters and decimal point
        String strPrice= food.replaceAll("[^\\d.]", "");
        double foodPrices = Double.parseDouble(strPrice);
        return foodPrices;
    }
    
    public void initializeRestaurant() {
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

        List<String> libeccioFoods = new ArrayList<>();
        libeccioFoods.add("Formaggio ($12.50)");
        libeccioFoods.add("Ghiaccio ($1.01)");
        libeccioFoods.add("Melone ($5.20)");
        libeccioFoods.add("Prosciutto and Pesci ($20.23)");
        libeccioFoods.add("Risotto ($13.14)");
        libeccioFoods.add("Zucchero and Sale ($0.60)");
        Restaurant libeccio = new Restaurant("Libeccio", libeccioFoods);

        List<String> savageGardenFoods = new ArrayList<>();
        savageGardenFoods.add("Abbacchio’s Tea ($1.00)");
        savageGardenFoods.add("DIO’s Bread ($36.14)");
        savageGardenFoods.add("Giorno’s Donuts ($6.66)");
        savageGardenFoods.add("Joseph’s Tequila ($35.00)");
        savageGardenFoods.add("Kakyoin’s Cherry ($3.50)");
        savageGardenFoods.add("Kakyoin’s Porridge ($4.44)");
        Restaurant savageGarden = new Restaurant("Savage Garden", savageGardenFoods);

        // Add restaurants to the generator
        addRestaurant(jadeGarden);
        addRestaurant(cafeDeuxMagots);
        addRestaurant(trattoria);
        addRestaurant(libeccio);
        addRestaurant(savageGarden);
    }

    public void printOrderHistory(String residentName) {
        for (Resident resident : residents) {
            if (resident.getName().equals(residentName)){
                System.out.println(" ");
                System.out.println("====================================================================================");
                System.out.println("\nOrder History for " + resident.getName());
                System.out.println("+-----+------------------------------------------------+---------------------+");
                System.out.println("| Day | Food                                           | Restaurant          |");
                System.out.println("+-----+------------------------------------------------+---------------------+");
                for (String order : resident.getOrderHistory()) {
                    System.out.println(order);
                }
                System.out.println("+-----+------------------------------------------------+---------------------+");
                System.out.println();
                System.out.println("====================================================================================");
                
            }}
    }

    public void getResidents() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(residentFilePath));
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
    public void printResidentProfile(String residentName){
            // Generate food orders--important,else will not generate orderHistory
            initializeRestaurant();
            getResidents();
            generateFoodOrders();   //must below initializeRestaurant and getResidents
            //print profile and orderHistory
            viewResidentProfile(residentName);
            printOrderHistory(residentName);
    }

    public static void main(String[] args) {
        Preference generator = new Preference();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the resident’s name: ");
        String residentName =sc.nextLine();
        generator.printResidentProfile(residentName);

    }
}

