package ga_wia1002;
import static ga_wia1002.ProjectJOJOLandSystem.sortResidents;
import java.util.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;


public class JOJOLandsGame {
    private JOJOLandsMap map;
    private JojolandLocation currentLocation;
    private int currentDay;
    private String day;
    // Create restaurants
    
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/game_database";
//    private static final String DB_USERNAME = "your_username";
//    private static final String DB_PASSWORD = "your_password";

    // ...
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
        //Q3 view Waiting List and Processing List 
        showWaitingAndProcessingList showWP_List = new showWaitingAndProcessingList();
        //Q2
        ProjectJOJOLandSystem displayResidentInfo = new ProjectJOJOLandSystem();
        
        //Q4-view resident's profile
        Preference viewProfile = new Preference();
        
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
            
                System.out.println("[2] Advance to Next Day");
                System.out.println("[3] Save Game");
                System.out.println("[4] Exit");
                System.out.print("Select: ");

                String input = scanner.nextLine().trim();
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
                    break;
                } else {
                    JojolandLocation destination = findDestination(input);
                    if (destination != null) {
                        moveToLocation(destination);
                        printCurrentLocation();
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
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
                
                System.out.println("[2] View Waiting List and Order Processing List");       
                System.out.println("[3] View Menu");
                System.out.println("[4] View Sales Information");
                System.out.println("[5] Milagro Man");
                System.out.println("[6] Back ("+")"); //previous location
                System.out.println("[7] Back to Town Hall");
                
                //receive input
                System.out.print("Select: ");
                String input = scanner.nextLine().trim();   
                
                if (input.equals("2")) {
                    showWP_List.displayWaitingAndProcessingList(currentDay,currentLocation.getName());
                } else if (input.equals("3")) {
                    //View Menu
                    
                } else if (input.equals("4")) {
                   //view  Sales information
                   
                } else if(input.equals("5")){
                    //[5] Milagro Man
                    
                }else if(input.equals("6")){
                    //[6] back to previous location
                    
                }else if(input.equals("7")){
                    currentLocation = map.getLocation("Town Hall");
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
                
                System.out.println("[2] View Resident Information");
                System.out.println("[3] Back (Savage Garden)");
                System.out.println("[4] Back to Town Hall");
                
                System.out.print("Select: ");
                String input = scanner.nextLine();  //user instruction

                    
                if (input.equals("2")) {
                    displayResidentInfo.displayResidentInformation(currentLocation.getName());
                    System.out.println("\n[1] View Resident's Profile");
                    System.out.println("[2] Sort");
                    System.out.println("[3] Exit");
                    
                    System.out.print("Select: ");
                    input = scanner.nextLine();  //user instruction
                    
                    if(input.equals("1")){
                        Scanner sc = new Scanner(System.in);
                        System.out.print("Enter the resident’s name: ");
                        String residentName =sc.nextLine();
                        viewProfile.printResidentProfile(residentName);
                    }
           
                    else if(input.equals("2")){
                        System.out.print("Enter the sorting order (e.g., 'Stamina (ASC); Precision (DESC); Stand (ASC)'): ");
                        scanner.nextLine(); // Clear the newline character from previous input
                        String sortingOrder = scanner.nextLine();
                        sortResidents(residents, sortingOrder);
                        displayResidentInfo.displayResidentInformation(currentLocation.getName());                           
                    }
                    
                    else if(input.equals("3")){
                        // Exit
                        System.exit(0);
                        break;
                    } 
                    
                    
                } else if (input.equals("3")) {
                    currentLocation = map.getLocation("Savage Garden");
                } else if (input.equals("4")) {
                    currentLocation = map.getLocation("Town Hall");
                } else{
                        JojolandLocation destination = findDestination(input);
                        if (destination != null) {
                            moveToLocation(destination);
                            printCurrentLocation();
                        } else {
                            System.out.println("Invalid input. Please try again.");
                        }
                    }  
                }
            }
    }

    private void printCurrentLocation() {
        System.out.println("Current Location: " + currentLocation.getName());
    }

    private JojolandLocation findDestination(String input) {
        for (JojolandLocation neighbor : currentLocation.getPaths().keySet()) {
            if (neighbor.getName().startsWith(input.toUpperCase())) {
                return neighbor;
            }
        }
        return null;
    }

    private void moveToLocation(JojolandLocation destination) {
        System.out.println("Moving to " + destination.getName() + "...");
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
//        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
//            String query = "INSERT INTO saved_games (current_location, current_day) VALUES (?, ?)";
//
//            try (PreparedStatement statement = connection.prepareStatement(query)) {
//                statement.setString(1, currentLocation.getName());
//                statement.setInt(2, currentDay);
//                // Set any other relevant parameters here
//                
//                int rowsAffected = statement.executeUpdate();
//                if (rowsAffected > 0) {
//                    System.out.println("Game saved.");
//                } else {
//                    System.out.println("Failed to save the game.");
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Error saving the game: " + e.getMessage());
//        }
    }

    public static void main(String[] args) {
        JOJOLandsGame game = new JOJOLandsGame();
        game.start();
    }
}


