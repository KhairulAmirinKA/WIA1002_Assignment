package ga_wia1002;
import java.util.*;

public class JOJOLandsGame{
    private JOJOLandsMap map;
    private JojolandLocation previousLocation;
    private JojolandLocation currentLocation;
    private String day;
    int currentDay;
    private Scanner scanner;
    private Stack<JojolandLocation> movementHistory;
    private Stack<JojolandLocation> forwardHistory;    
    
    public JOJOLandsGame() {
        map = new JOJOLandsMap();
        currentLocation = map.getLocation("Town Hall"); //
        currentDay = 1; // Initialize currentDay to 1
        day = "Sunday";   // Initialize day1 as Sunday
        scanner = new Scanner(System.in);
        movementHistory = new Stack<>();
        forwardHistory = new Stack<>(); // Initialize the forward history stack        
    }

    public void start() {
        //start
        System.out.println("It's Day " + currentDay + " (" + day + ") of our journey in JOJOLands!");
        printCurrentLocation();
        movementHistory.add(currentLocation);
        String currentInput=" ";
        
        while (true) {
            //at Town Hall
                moveOption(currentLocation.getName());  //print[1]MoveTo: A B C
                otherOption(currentInput);
                String input = userInput();
                currentInput=input;
                if(input.charAt(0)=='1'){
                    Input1(input,map,currentLocation);  //when user choose 1, find the location in map
                }

                //at Town Hall
                if(currentLocation.getName().equals("Town Hall")){  //execute Town Hall option
                    if(!TownHall(input)){   //Town Hall only send false when want to exit, !false==true,then will break; else will if(false),skip the break and continue to while(true)
                        break;
                    }
                }
                
                //at Restaurants
                else if(isRestaurant(currentLocation.getName())){
                    restaurants(input,currentInput);
                }
                
                //at Residential Area
                else if(isResidentialArea(currentLocation.getName())){
                    if(!otherResidentialArea(input,currentInput)){
                        break;
                    }
                }
                
                //at Morioh Grand Garden
                else if(currentLocation.getName().equals("Morioh Grand Hotel")){
                    if(!Morioh(input,currentInput)){
                       break;
                    }
                }
                System.out.println("Currentlocation: "+currentLocation.getName()+" PreviousLocation: "+map.getPreviousLocation().getName());
                System.out.println("Current Input: "+input+" Previous input: "+currentInput);
                System.out.println("Back key? "+ isBackKey(currentInput));
                
        }
        
    }
    
    private void moveOption(String location) {
        JojolandLocation currentLocation = map.getLocation(location);

        System.out.println("[1] Move To: ");
        int optionCounter = 1;
        for (JojolandLocation neighbor : currentLocation.getPaths().keySet()) {
            System.out.print("\t[" + (char)('A' + optionCounter - 1) + "] " + neighbor.getName() + "\t\t");
            optionCounter++;
        }
        System.out.println();
    }
    
    private void otherOption(String currentInput) {   // Print other menu options
        String[] menuOptions;
        String backLocation=backLocation();
        String forwardLocation = forwardLocation();
        
        if (currentLocation.getName().equals("Town Hall")) {
            menuOptions = new String[]{"Advance to Next Day", "Save Game", "Exit"};
        }
        
        else if(isBackKey(currentInput)&&isRestaurant(currentLocation.getName())){    //[Forward]
            menuOptions = new String[]{"View Waiting List and Order Processing List", "View Menu", "View Sales Information", "Milagro Man",
                    "Back (" + backLocation + ")","Forward (" + forwardLocation + ")", "Back to Town Hall"};                   
        }        
        else if (isRestaurant(currentLocation.getName())&&!isBackKey(currentInput)) {
            menuOptions = new String[]{"View Waiting List and Order Processing List", "View Menu", "View Sales Information", "Milagro Man",
                    "Back (" + backLocation + ")", "Back to Town Hall"};
            }
        
        else if(currentLocation.getName().equals("Morioh Grand Hotel")&&isBackKey(currentInput)){    //[Forward]
           menuOptions= new String[]{"View Resident Information","The Hand","Back (" + backLocation + ")","Forward (" + forwardLocation + ")","Back to Town Hall"}; 
        }        
        else if(currentLocation.getName().equals("Morioh Grand Hotel")&&!isBackKey(currentInput)){    
           menuOptions= new String[]{"View Resident Information","The Hand","Back (" + backLocation + ")","Back to Town Hall"}; 
        }
        
        else if(isResidentialArea(currentLocation.getName())&&isBackKey(currentInput)){ //[Forward]
            menuOptions= new String[]{"View Resident Information","Back (" + backLocation + ")","Forward (" + forwardLocation + ")","Back to Town Hall"};
        }
        else if(isResidentialArea(currentLocation.getName())&&!isBackKey(currentInput)){
            menuOptions= new String[]{"View Resident Information","Back (" + backLocation + ")","Back to Town Hall"};
        }        
 
        else {
            return; //exit loop
        }
        for (int i = 0; i < menuOptions.length; i++) {
            System.out.println("[" + (i + 2) + "] " + menuOptions[i]);  //start from[2]
        }
    }
 
    private String userInput(){
        System.out.print("Select: ");
        return scanner.nextLine().trim(); //user input
    }    
    
    private void Input1(String input, JOJOLandsMap map, JojolandLocation currentLocation) {
        if (input.length() < 2 || !Character.isDigit(input.charAt(0))) {
            System.out.println("Invalid input format. Please enter a digit followed by a character.");
            return;
        }

        map.setPreviousLocation(currentLocation); // Set location before traveling as previousLocation
        JojolandLocation destination = findDestination(input);
        if (destination != null) {
            map.setPreviousLocation(currentLocation);
            moveToLocation(destination);
        } else {
            System.out.println("Invalid input. Please try again.");
        }
    }
    
    private boolean TownHall(String input) {
        if (input.equals("2")) {
            advanceToNextDay();
            calculateDay();
            System.out.println("It's Day " + currentDay + " (" + day + ") of our journey in JOJOLands!");
            printCurrentLocation();
        } else if (input.equals("3")) {
            saveGame();
            System.out.println("Game saved.");
        } else if (input.equals("4")) {
            System.out.println("Exiting JOJOLands game. Goodbye!");
            // You may want to add additional cleanup or exit logic here if needed
            return false; // Exit the loop
        } else {
            System.out.println("Invalid input. Please try again.");
        }
        return true; // Continue the loop
    }

    private boolean isRestaurant(String locationName) {
        return locationName.equals("Savage Garden") ||
               locationName.equals("Cafe Deux Magots") ||
               locationName.equals("Jade Garden") ||
               locationName.equals("Trattoria Trussardi") ||
               locationName.equals("Libeccio");
    }

    private boolean isResidentialArea(String locationName){
        return locationName.equals("Polnareff Land")||
               locationName.equals("Joestar Mansion") ||
               locationName.equals("San Giorgio Maggiore") ||
               locationName.equals("Green Dolphin Street Prison")||
               locationName.equals("Angelo Rock") ||
               locationName.equals("DIO's Mansion") ||
               locationName.equals("Vineyard") ;            
    }

    private boolean otherResidentialArea(String input,String currentInput) {
        Preference viewProfile = new Preference();//Q4-view resident's profile
        ResidentManager residentManager = new ResidentManager();
        residentManager.loadResidents("residents.csv", "stands.csv");
        List<Resident> residents = residentManager.getResidents();
        StandManager standManager = new StandManager();
        standManager.loadStands("stands.csv");
        ProjectJOJOLandSystem jojoSystem = new ProjectJOJOLandSystem();
        
                if (input.equals("2")) {
                // View Resident Information
                jojoSystem.displayResidentInformation(currentLocation.getName());
                System.out.println("\n[1] View Resident's Profile");
                System.out.println("[2] Sort");
                System.out.println("[3] Exit");

                System.out.print("Select: ");
                String select = scanner.nextLine(); // user instruction

                if (select.equals("1")) {
                    // [1] View Resident's Profile
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Enter the resident’s name: ");
                    String residentName = sc.nextLine();
                    viewProfile.printResidentProfile(residentName, currentDay);
                } else if (select.equals("2")) {
                    // {2} Sort
                    List<Resident> sortResident = residentManager.getResidentsByResidentialArea(currentLocation.getName());
                    System.out.print("Enter the sorting order (e.g., Stamina (asc); Precision (desc); Stand (asc)): ");
                    String sortingOrder = scanner.nextLine();
                    jojoSystem.sortResidents(sortResident, sortingOrder); // Update sortResident with the sorted list
                } else if (select.equals("3")) {
                    return false;
                }
                
            } else if (input.equals("3")) {
                // [3] Back to previous location
                moveBack();
                
            } else if (input.equals("4")&&!isBackKey(currentInput)||input.equals("5")) {
                // [4] Back to Town Hall
                currentLocation = map.getLocation("Town Hall");
                moveToLocation(currentLocation);
            } else if(input.equals("4")&&isBackKey(currentInput)){    //[Back] is previously chosen,[4] Forward
                moveForward();
            }
        return true;
    }
    
   private boolean Morioh(String input,String currentInput) {
        Preference viewProfile = new Preference();//Q4-view resident's profile
        ResidentManager residentManager = new ResidentManager();
        residentManager.loadResidents("residents.csv", "stands.csv");
        List<Resident> residents = residentManager.getResidents();
        StandManager standManager = new StandManager();
        standManager.loadStands("stands.csv");
        ProjectJOJOLandSystem jojoSystem = new ProjectJOJOLandSystem();
        
                if (input.equals("2")) {
                // View Resident Information
                    jojoSystem.displayResidentInformation(currentLocation.getName());
                    System.out.println("\n[1] View Resident's Profile");
                    System.out.println("[2] Sort");
                    System.out.println("[3] Exit");

                System.out.print("Select: ");
                String select = scanner.nextLine(); // user instruction

                    if (select.equals("1")) {
                        // [1] View Resident's Profile
                        Scanner sc = new Scanner(System.in);
                        System.out.print("Enter the resident’s name: ");
                        String residentName = sc.nextLine();
                        viewProfile.printResidentProfile(residentName, currentDay);
                    } else if (select.equals("2")) {
                        // {2} Sort
                        List<Resident> sortResident = residentManager.getResidentsByResidentialArea(currentLocation.getName());
                        System.out.print("Enter the sorting order (e.g., Stamina (asc); Precision (desc); Stand (asc)): ");
                        String sortingOrder = scanner.nextLine();
                        jojoSystem.sortResidents(sortResident, sortingOrder); // Update sortResident with the sorted list
                    } else if (select.equals("3")) {
                        return false;
                      }
                    }
                
                else if(input.equals("3")){
                //[3]The Hand
                } 
                
                else if (input.equals("4")) {
                // [4] Back to previous location
                moveBack();               
                }
                
                else if (input.equals("5")&&!isBackKey(currentInput)||input.equals("6")) {
                // [5] Back to Town Hall
                currentLocation = map.getLocation("Town Hall");
                moveToLocation(currentLocation);
                }
                
                else if(isBackKey(currentInput)&&input.equals(5)){  //previously select [Back]
                // [5]Forward
                moveForward();
                }
                        
        return true;
    }
   
    private void restaurants(String input, String currentInput) {
        RestaurantInfo resInfo = new RestaurantInfo();
        JOJOLandsRestaurant jojoRestaurant = new JOJOLandsRestaurant();

        if (input.equals("2")) {
            resInfo.processList(currentDay, currentLocation.getName());
            resInfo.displayWaitingAndProcessingList(currentLocation.getName());
        } else if (input.equals("3")) {
            jojoRestaurant.viewMenu(currentLocation.getName());
        } else if (input.equals("4")) {
            resInfo.viewSales(currentLocation.getName());
        } else if (input.equals("5")) {
            //[5] Milagro Man
        } else if (input.equals("6")) {
            //[6] Back
            moveBack();
        } else if (input.equals("7") && !isBackKey(currentInput)) {
            //[7] Forward (Jade Garden)
            moveForward();
        } else if (input.equals("8")) {
            //[8] Back to Town Hall
            currentLocation = map.getLocation("Town Hall");
            moveToLocation(currentLocation);
        } 
    }
      

private boolean isBackKey(String currentInput) {
    if (currentLocation.getName().equals("Morioh Grand Hotel") && currentInput.equals("4")) {
        return true;
    }
    
    if (isRestaurant(currentLocation.getName()) && currentInput.equals("6")) {
        return true;
    }
    
    if (isResidentialArea(currentLocation.getName()) && currentInput.equals("3")) {
        return true;
    }
    
    if (isResidentialArea(currentLocation.getName())&&isRestaurant(map.getPreviousLocation().getName()) && currentInput.equals("6")) {
        return true;
    }
    
    if (isResidentialArea(map.getPreviousLocation().getName())&&isRestaurant(currentLocation.getName()) && currentInput.equals("3")) {
        return true;
    }    
    
    return false;
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
        System.out.println("Moving to " + destination.getName() + "...");
        System.out.println("==================================================================================================================");
        currentLocation = destination;
        forwardHistory.clear();     //clear all the forwardHistory if the user select to go to any where except [Forward]
        forwardHistory.add(destination);        //add the last travel place,so actually it always record last travel only
        movementHistory.add(destination);   //keep track on all the places travelled
        printCurrentLocation();
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
    //show forward=??
    private String forwardLocation(){
    //current=B, eg: A->B->C->B 
    if (!forwardHistory.isEmpty()) {
        return forwardHistory.peek().getName();  //forwardLocation= C
    }
    return " ";
    }

    private String backLocation() {
    if(movementHistory.size()>=2){
        return movementHistory.elementAt(movementHistory.size()-2).getName();   //A->B->C,new location, back=B
    }
        return " ";
    }

    private void moveBack() {
        if (!movementHistory.isEmpty()) {   //A->B->C->D    ->C
            forwardHistory.push(currentLocation); // D->forwardHistory,currentLocation=lastLocation
            map.setPreviousLocation(currentLocation);
            currentLocation = movementHistory.pop(); //remove D and return D to currentLocation ,after this method,movementHistory: A->B->C
            currentLocation=movementHistory.peek();    //cannot straight away use moveToLocation()function,will add again removed forwardHistory //C
            System.out.println("Moving to " + currentLocation.getName() + "...");
            System.out.println("==================================================================================================================");            
            printCurrentLocation();
        } else {
            System.out.println("No previous location to move back to.");
        }
    }

    private void moveForward() {    //always use after moveBack() method, so its last element always previous last element which forward want to go ,eg: D
        if (!forwardHistory.isEmpty()) {    ////A->B->C->D->C      ->D
            map.setPreviousLocation(currentLocation);
            JojolandLocation nextLocation = forwardHistory.pop(); //D after moveBack()
            forwardHistory.clear();
            currentLocation=nextLocation;   //cannot straight away use moveToLocation()function,will add again removed forwardHistory //D
            
            System.out.println("Moving to " + currentLocation.getName() + "...");
            System.out.println("==================================================================================================================");            
            printCurrentLocation();
        } else {
            System.out.println("No forward history available.");
        }
    }
    public static void main(String[] args) {
        JOJOLandsGame game = new JOJOLandsGame();
        game.start();
        
    }
}


