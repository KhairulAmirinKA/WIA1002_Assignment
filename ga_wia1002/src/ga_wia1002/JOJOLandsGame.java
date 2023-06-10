package ga_wia1002;
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
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/game_database";
//    private static final String DB_USERNAME = "your_username";
//    private static final String DB_PASSWORD = "your_password";

    // ...
    public JOJOLandsGame() {
        map = new JOJOLandsMap();
        currentLocation = map.getLocation("Jade Garden"); //******************************need change back to "Town Hall"
        currentDay = 42; // Initialize currentDay to 1//******************************need change back to 1
        day = "Sunday";   // Initialize day1 as Sunday
    }

    public void start() {
        System.out.println("It's Day " + currentDay + " (" + day + ") of our journey in JOJOLands!");
        printCurrentLocation();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("[1] Move to:");
            for (JojolandLocation neighbor : currentLocation.getPaths().keySet()) {
                System.out.print("[" + neighbor.getName().charAt(0) + "] " + neighbor.getName()+"\t\t");
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


