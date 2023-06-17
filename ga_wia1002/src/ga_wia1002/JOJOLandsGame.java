package ga_wia1002;
import java.util.*;

public class JOJOLandsGame{
    private JOJOLandsMap map;
    private JojolandLocation currentLocation;
    private String day;
    int currentDay;
    private Scanner scanner;
    private Stack<JojolandLocation> movementHistory;
    private Stack<JojolandLocation> forwardHistory;    
    private boolean diffDay;
    RestaurantInfo resInfo ;
    
    public JOJOLandsGame() {
        map = new JOJOLandsMap();
        currentLocation = map.getLocation("Town Hall"); //
        currentDay = 1; // Initialize currentDay to 1
        day = "Sunday";   // Initialize day1 as Sunday
        scanner = new Scanner(System.in);
        movementHistory = new Stack<>();
        forwardHistory = new Stack<>(); // Initialize the forward history stack    
        diffDay=true;
    }

    public void start() {
        //start
//        System.out.println("Welcome, the fantastical realm of JOJOLands.");
//        System.out.println("[1] Start Game");
//        System.out.println("[2] Load Game");
//        System.out.println("[3] Exit");
//       
//        System.out.println("\n Select: ");
//        String select = scanner.nextLine();
//        System.out.println("========================================================================================================");
//        System.out.println("Select a map:");
//        System.out.println("[1] Default Map");
//        System.out.println("[2] Parallel Map");
//        System.out.println("[3] Alternate Map");
     
        resInfo = new RestaurantInfo(currentDay);
        System.out.println("It's Day " + currentDay + " (" + day + ") of our journey in JOJOLands!");
        printCurrentLocation();
        movementHistory.add(currentLocation);
        
        while (true) {
            //at Town Hall
                if(diffDay){ 
                    resInfo = new RestaurantInfo(currentDay);
                    resInfo.generateFoodAndWaitingList(currentDay);
                    resInfo.storeOrder(currentDay);
                    diffDay=false;
                }
                moveOption(currentLocation.getName());  //print[1]MoveTo: A B C
                otherOption();
                String input = userInput();

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
                    restaurants(input);
                }
                
                //at Residential Area
                else if(isResidentialArea(currentLocation.getName())){
                    ResidentialArea(input);
                }

                System.out.println("CurrentDay: "+currentDay);
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
    
    private void otherOption() {   // Print other menu options
        String[] menuOptions;
        String backLocation=backLocation();
        String forwardLocation = forwardLocation();
        
        //at TownHall
        if (currentLocation.getName().equals("Town Hall")) {
            menuOptions = new String[]{"Advance to Next Day", "Save Game", "Exit"};     
        }        
   
        //at restaurant
        else if(isRestaurant(currentLocation.getName())){   
            if(forwardHistory.size()>1){    //[7][Forward] enable 
                menuOptions = new String[]{"View Waiting List and Order Processing List", "View Menu", "View Sales Information", "Milagro Man",
                        "Back (" + backLocation + ")","Back to Town Hall","Forward (" + forwardLocation + ")"};                   
            }        
            else {
                menuOptions = new String[]{"View Waiting List and Order Processing List", "View Menu", "View Sales Information", "Milagro Man",
                        "Back (" + backLocation + ")", "Back to Town Hall"};
                }
        }
        
        else if(currentLocation.getName().equals("Green Dolphin Street Prison")){
            if(forwardHistory.size()>1){    //[6]Forward
                menuOptions= new String[]{"View Resident Information","Back (" + backLocation + ")","Back to Town Hall","Dirty Deeds Done Dirt Cheap", "Forward (" + forwardLocation + ")"}; 
            }
            else{
                menuOptions= new String[]{"View Resident Information","Back (" + backLocation + ")","Back to Town Hall","Dirty Deeds Done Dirt Cheap"}; 
            }
        }
        
        else if(currentLocation.getName().equals("Morioh Grand Hotel")){    
            if(forwardHistory.size()>1){ //[6][Forward]
                menuOptions= new String[]{"View Resident Information","Back (" + backLocation + ")","Back to Town Hall","The Hand", "Forward (" + forwardLocation + ")"}; 
            }        
            else{
                menuOptions= new String[]{"View Resident Information","Back (" + backLocation + ")","Back to Town Hall","The Hand"}; 
            }
        }
        
        else if(currentLocation.getName().equals("Angelo Rock")){    
            if(forwardHistory.size()>1){    //[7][Forward]
                menuOptions= new String[]{"View Resident Information","Back (" + backLocation + ")","Back to Town Hall","Red Hot Chili Pepper","Another One Bites the Dust","Forward (" + forwardLocation + ")"}; 
            }
            else{   //no [Forward]
                menuOptions= new String[]{"View Resident Information","Back (" + backLocation + ")","Back to Town Hall","Red Hot Chili Pepper","Another One Bites the Dust"}; 
            }
        }
        
        else if(isResidentialArea(currentLocation.getName())){ 
            if(forwardHistory.size()>1){//[5][Forward]
                        menuOptions= new String[]{"View Resident Information","Back (" + backLocation + ")","Back to Town Hall","Forward (" + forwardLocation + ")"};
                    }
            else{ 
                        menuOptions= new String[]{"View Resident Information","Back (" + backLocation + ")","Back to Town Hall"};
                    }        
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

        JojolandLocation destination = findDestination(input);
        if (destination != null) {
            moveToLocation(destination);
        } else {
            System.out.println("\nInvalid input. Please try again.");
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
            return false; // Exit the loop
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
               locationName.equals("DIO's Mansion") ||
               locationName.equals("Vineyard") ||  
               locationName.equals("Angelo Rock") ||    
               locationName.equals("Morioh Grand Hotel") ;     
    }

    private void ResidentialArea(String input) {
        Preference viewProfile = new Preference();//Q4-view resident's profile
        ResidentManager residentManager = new ResidentManager();
        residentManager.loadResidents("residents.csv", "stands.csv");
        List<Resident> residents = residentManager.getResidents();
        StandManager standManager = new StandManager();
        standManager.loadStands("stands.csv");
        ProjectJOJOLandSystem jojoSystem = new ProjectJOJOLandSystem();
        DirtyDeeds dd = new DirtyDeeds();   //extraFeaturesQ4
        
                while (input.equals("2")) {
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
                        viewProfile.printResidentProfile(residentName, currentDay,currentLocation.getName());
                    } else if (select.equals("2")) {
                        // {2} Sort
                        List<Resident> sortResident = residentManager.getResidentsByResidentialArea(currentLocation.getName());
                        System.out.print("Enter the sorting order (e.g., Stamina (asc); Precision (desc); Stand (asc)): ");
                        String sortingOrder = scanner.nextLine();
                        jojoSystem.sortResidents(sortResident, sortingOrder); // Update sortResident with the sorted list
                    } else if (select.equals("3")) {
                        //[3]Exit
                        System.out.println("==========================================================================================================================");
                        printCurrentLocation();
                        break;
                    } 
                 } 
                if (input.equals("3")) {
                    // [3] Back
                    moveBack();
                }               
                else if(input.equals("4")){
                    //[4]Back to Town Hall
                    currentLocation = map.getLocation("Town Hall");
                    moveToLocation(currentLocation);    
                }
                
                if(forwardHistory.size()>1){   //[Forward] case
                    if(currentLocation.getName().equals("Angelo Rock")){
                        if(input.equals("5")){
                            //[5]Red Hot Chili Pepper
                        }
                        else if(input.equals("6")){
                            //[6] Another One Bites the Dust
                        }
                        else if(input.equals("7")){
                            //[7] Forward
                            moveForward();
                        }
                    }
                    if(currentLocation.getName().equals("Green Dolphin Street Prison")){
                        if(input.equals("5")){
                         //[5]Dirty Deeds Done Dirt Cheap
                         dd.runDirtyDeeds();
                        }
                        else if(input.equals("6")){
                          //[6] Forward
                          moveForward();
                        }
                    }
                    if(currentLocation.getName().equals("Morioh Grand Hotel")){
                        if(input.equals("5")){
                            //[5] The Hand
                        }
                        else if(input.equals("6")){
                            //[6] Forward
                            moveForward();
                        }        
                    }
                    else{   //otherResidentialArea
                        if(input.equals("5")){
                            //[5]Forward
                            moveForward();
                        }
                    }
                }
                
                else{   //no [Forward]--normal case
                    
                    if(currentLocation.getName().equals("Green Dolphin Street Prison")){
                        if(input.equals("5")){
                         //[5]Dirty Deeds Done Dirt Cheap
                         dd.runDirtyDeeds();
                        }
                    }                    
                    if(currentLocation.getName().equals("Angelo Rock")){
                        if(input.equals("5")){
                            //[5]Red Hot Chili Pepper
                        }
                        else if(input.equals("6")){
                            //[6] Another One Bites the Dust
                        }
                    }
                    if(currentLocation.getName().equals("Morioh Grand Hotel")){
                        if(input.equals("5")){
                            //[5] The Hand
                        }
                    }        
                }
    }

    private void restaurants(String input) {       
        if (input.equals("2")) {      
            resInfo.displayWaitingAndProcessingList(currentLocation.getName());
        } else if (input.equals("3")) {
            resInfo.viewMenu(currentLocation.getName());
        } while (input.equals("4")) {
            //[4]View Sales Information
            System.out.println("==========================================================================================================================");
            System.out.println("Restaurant: "+currentLocation.getName());
            System.out.println("Sales Information");
            System.out.println("[1] View Sales");
            System.out.println("[2] View Aggregated Information");
            System.out.println("\t[A] Minimum Sales");
            System.out.println("\t[B] Maximum Sales");
            System.out.println("\t[C] Top k Highest Sales");
            System.out.println("\t[D] Total and Average Sales");
            System.out.println("[3] Exit");
            System.out.print("\nSelect: ");
            String select =scanner.nextLine();
            System.out.println("==========================================================================================================================");
            switch(select){
                case("1"):
                    //[1]view Sales
                        System.out.print("Enter Day: ");
                        int userInputDay= scanner.nextInt();
                        resInfo.viewSales(currentLocation.getName(),userInputDay);
                        break;
                case("2A"):
                    //[2A] Minimum Sales
                    resInfo.viewMinimumSales(currentLocation.getName());
                    break;
                case("2B"):
                    //[2B]Maximum Sales
                    resInfo.viewMaximumSales(currentLocation.getName());
                    break;
                case("2C"):
                    //[2C]Top k Highest Sales
                    resInfo.view_K_Highest(currentLocation.getName());
                    break;
                case("2D"):
                    //[2D]Total and Average Sales
                    resInfo.viewTotalSales(currentLocation.getName());
                    break;      
                }
                    
            if(select.equals("3")){
                    //[3]exit
                    break;         
            }    
        } if (input.equals("5")) {
            //[5] Milagro Man
            resInfo.MilagroMode(currentLocation.getName(), currentDay);
            
        } else if (input.equals("6")) {
            //[6] Back
            moveBack();
        } else if (input.equals("7")) {
            //[7] Back to Town Hall
            currentLocation = map.getLocation("Town Hall");
            moveToLocation(currentLocation);

        } else if (input.equals("8")&&forwardHistory.size()>1) {
            //[8] Forward (Jade Garden)
            moveForward();
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
        System.out.println("Moving to " + destination.getName() + "...");
        System.out.println("==================================================================================================================");
        currentLocation = destination;
        forwardHistory.clear();     //clear all the forwardHistory if the user select to go to any where except [Forward]
        forwardHistory.add(destination);        //add the last travel place,so actually it always record last travel only
        movementHistory.add(destination);   //keep track on all the places travelled
        printCurrentLocation();
    }

    private void advanceToNextDay() {
        System.out.println("==========================================================================================================================");
        currentDay++;
        diffDay=true;
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


