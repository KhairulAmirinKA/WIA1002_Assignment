package ga_wia1002;
import java.util.*;

public class JOJOLandsGame{
    private JOJOLandsMap map;
    private JojolandLocation previousLocation;
    private JojolandLocation currentLocation;
    private int currentDay;
    private String day;

    public JOJOLandsGame() {
        map = new JOJOLandsMap();
        currentLocation = map.getLocation("Town Hall"); //
        currentDay = 1; // Initialize currentDay to 1
        day = "Sunday";   // Initialize day1 as Sunday
        
    }

    public void start() {
        ResidentManager residentManager = new ResidentManager();
        residentManager.loadResidents("residents.csv", "stands.csv");
        List<Resident> residents = residentManager.getResidents();
        StandManager standManager = new StandManager();
        standManager.loadStands("stands.csv");
        ProjectJOJOLandSystem jojoSystem = new ProjectJOJOLandSystem();
        
        //Q3 view Waiting List and Processing List 
        RestaurantInfo resInfo = new RestaurantInfo();
        
        //Q4-view resident's profile
        Preference viewProfile = new Preference();
        
        JOJOLandsRestaurant jojoRestaurant = new JOJOLandsRestaurant();
        //start
        System.out.println("It's Day " + currentDay + " (" + day + ") of our journey in JOJOLands!");
        printCurrentLocation();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            //at Town Hall
            if(currentLocation.getName().equals("Town Hall")){
                System.out.println("[1] Move to:");
                int optionCounter = 1;
                for (JojolandLocation neighbor : currentLocation.getPaths().keySet()) {
                    System.out.print("[" + (char)('A' + optionCounter - 1) + "] " + neighbor.getName()+"\t\t");
                    optionCounter++;
                }
                System.out.println(" ");
                
                //print other options
                String[] menuOptions = {
                    "Advance to Next Day",
                    "Save Game",
                    "Exit",
                };
                for (int i = 0; i < menuOptions.length; i++) {
                    System.out.println("[" + (i + 2) + "] " + menuOptions[i]);
                }        
                
                System.out.print("Select: ");
                String input = scanner.nextLine().trim();
                
                if(input.charAt(0)=='1'){
                    if (input.length() < 2 || !Character.isDigit(input.charAt(0))) {            //so no error if user only type 1 or A
                        System.out.println("Invalid input format. Please enter a digit followed by a character.");
                        continue; // Go back to the beginning of the loop to prompt for input again
                    }
                    map.setPreviousLocation(currentLocation);       //set location before travelling as previousLocation
                    JojolandLocation destination = findDestination(input);
                    if (destination != null) {
                        moveToLocation(destination);
                        printCurrentLocation();        
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
                } else if (input.equals("2")) {
                    advanceToNextDay();
                    calculateDay();
                    System.out.println("It's Day " + currentDay + " (" + day + ") of our journey in JOJOLands!");
                    printCurrentLocation();
                } else if (input.equals("3")) {
                    saveGame();
                    System.out.println("Game saved.");
                } else if (input.equals("4")) {
                    System.out.println("Exiting JOJOLands game. Goodbye!");
                    break;
                }
            }

//at restaurants
            if (currentLocation.getName().equals("Savage Garden") || currentLocation.getName().equals("Cafe Deux Magots") || 
                currentLocation.getName().equals("Jade Garden")|| currentLocation.getName().equals("Trattoria Trussardi") || 
                currentLocation.getName().equals("Liberrio")) {
                
                //print all the instruction you can choose
                System.out.println("[1] Move to:");
                int optionCounter = 1;
                for (JojolandLocation neighbor : currentLocation.getPaths().keySet()) {
                    System.out.print("[" + (char)('A' + optionCounter - 1) + "] " + neighbor.getName()+"\t\t");
                    optionCounter++;
                }
                System.out.println(" ");
                
                //other options
                String[] menuOptions = {
                    "View Waiting List and Order Processing List",
                    "View Menu",
                    "View Sales Information",
                    "Milagro Man",
                    "Back (" + map.getPreviousLocation().getName() + ")",
                    "Back to Town Hall"
                };

                for (int i = 0; i < menuOptions.length; i++) {
                    System.out.println("[" + (i + 2) + "] " + menuOptions[i]);
                }                
                //receive input
                System.out.print("Select: ");
                String input = scanner.nextLine().trim();   
         
                if(input.charAt(0)=='1'){
                    if (input.length() < 2 || !Character.isDigit(input.charAt(0))) {            //so no error if user only type 1 or A
                        System.out.println("Invalid input format. Please enter a digit followed by a character.");
                        continue; 
                    }
                    map.setPreviousLocation(currentLocation);       //set location before travelling as previousLocation
                    JojolandLocation destination = findDestination(input);
                    if (destination != null) {
                        moveToLocation(destination);
                        printCurrentLocation();        
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
                }else if (input.equals("2")) {
                    resInfo.processList(currentDay,currentLocation.getName());  
                    resInfo.displayWaitingAndProcessingList(currentLocation.getName());
                } else if (input.equals("3")) {
                    //View Menu
                    
                } else if (input.equals("4")) {
                    resInfo.viewSales(currentLocation.getName());
                   
                } else if(input.equals("5")){
                    //[5] Milagro Man
                    
                }else if(input.equals("6")){
                    //[6] back to previous location
                        currentLocation = map.getPreviousLocation();
                        moveToLocation(currentLocation);
                        printCurrentLocation();  
                        
                    
                }else if(input.equals("7")){
                    currentLocation = map.getLocation("Town Hall");
                    moveToLocation(currentLocation);
                    printCurrentLocation();  
                }
            }
            
//######need change to general location
            if(currentLocation.getName().equals("Polnareff Land")){
                System.out.println("[1] Move to:");
                int optionCounter = 1;
                for (JojolandLocation neighbor : currentLocation.getPaths().keySet()) {
                    System.out.print("[" + (char)('A' + optionCounter - 1) + "] " + neighbor.getName()+"\t\t");
                    optionCounter++;
                }
                System.out.println(" ");
                
                // Other options
                String[] menuOptions = {
                    "View Resident Information",
                    "Back (" + map.getPreviousLocation().getName() + ")",
                    "Back to Town Hall"
                };

                for (int i = 0; i < menuOptions.length; i++) {
                    System.out.println("[" + (i + 2) + "] " + menuOptions[i]);
                }
                
                System.out.print("Select: ");
                String input = scanner.nextLine();  //user instruction

                if(input.charAt(0)=='1'){
                    if (input.length() < 2 || !Character.isDigit(input.charAt(0))) {            
                        System.out.println("Invalid input format. Please enter a digit followed by a character.");
                        continue; 
                    }
                    map.setPreviousLocation(currentLocation);       //set location before travelling as previousLocation
                    JojolandLocation destination = findDestination(input);
                    if (destination != null) {
                        moveToLocation(destination);
                        printCurrentLocation();        
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
                }else if (input.equals("2")) { //[2] View Resident Information
                    jojoSystem.displayResidentInformation(currentLocation.getName());
                    System.out.println("\n[1] View Resident's Profile");
                    System.out.println("[2] Sort");
                    System.out.println("[3] Exit");
                    
                    System.out.print("Select: ");
                    input = scanner.nextLine();  //user instruction
                    
                    if(input.equals("1")){  //[1] View Resident's Profile
                        Scanner sc = new Scanner(System.in);
                        System.out.print("Enter the resident’s name: ");
                        String residentName =sc.nextLine();
                        viewProfile.printResidentProfile(residentName);
                    }
           
                    else if(input.equals("2")){ //{2} Sort
                        List<Resident> sortResident = residentManager.getResidentsByResidentialArea(currentLocation.getName());//must in loop,so can get location
                        System.out.print("Enter the sorting order (e.g., Stamina (asc); Precision (desc); Stand (asc)): ");
                        String sortingOrder = scanner.nextLine();
                        jojoSystem.sortResidents(sortResident, sortingOrder); // Update sortResident with the sorted list    
                    }
                    else if(input.equals("3")){ //[3] Exit
                        // Exit
                        System.exit(0);
                        break;
                    } 
                } else if (input.equals("3")) { //[3]back to previous location
                    currentLocation = map.getPreviousLocation();
                    moveToLocation(currentLocation);
                    printCurrentLocation();
                } else if (input.equals("4")) { //[4] back to Town Hall
                    currentLocation = map.getLocation("Town Hall");
                    moveToLocation(currentLocation);
                    printCurrentLocation();
                }
                }
            }
    }
    
    private void printCurrentLocation() {
        System.out.println("Current Location: " + currentLocation.getName());
    }

    private JojolandLocation findDestination(String input) {    //input is user input
        String option = input.substring(1); // Extract the option part of the input (e.g., A, B, C), user will key in 1A, A is substring of 1(element behind 1)
        char optionChar = option.charAt(0);  //eg:after substring, option store A, so 1st character='A'
        int optionIndex = optionChar - 'A'; // Convert the option character to an index (0 for A, 1 for B, etc.), (A-A=65-65=0)so 1st index(0)=A

        List<JojolandLocation> paths = new ArrayList<>(currentLocation.getPaths().keySet());
        if (optionIndex >= 0 && optionIndex < paths.size()) {
            return paths.get(optionIndex);  //return path choosen by user
        }

        return null;
    }


    private void moveToLocation(JojolandLocation destination) {    
        if (destination.equals(currentLocation)) {
        // Base case: Stop recursion if destination is the same as current location
        return;
        }
        System.out.println("Moving to " + destination.getName() + "...");
        System.out.println("==================================================================================================================");
        currentLocation = destination;
    }

    private void advanceToNextDay() {
        currentDay++;
    }

    private void calculateDay() {
        int dayOfWeek = currentDay % 7;
        switch (dayOfWeek) {
            case 1:
                day = "Sunday";
                break;
            case 2:
                day = "Monday";
                break;
            case 3:
                day = "Tuesday";
                break;
            case 4:
                day = "Wednesday";
                break;
            case 5:
                day = "Thursday";
                break;
            case 6:
                day = "Friday";
                break;
            case 0:
                day = "Saturday";
                break;
        }
    }
    public int getCurrentDay() {
        return currentDay;
    }
    
    public String getDay(){
        return day;
    }
    
    public JojolandLocation getCurrentLocation() {
        return currentLocation;
    }

    private void saveGame() {

    }

    public static void main(String[] args) {
        JOJOLandsGame game = new JOJOLandsGame();
        game.start();
        
    }
}


