package Backend_Files;
import java.util.Scanner;

public class JOJOLandsGame {
    private JOJOLandsMap map;
    private JojolandLocation currentLocation;

    public JOJOLandsGame() {
        map = new JOJOLandsMap();
        currentLocation = map.getLocation("Town Hall");
    }

    public void start() {
        System.out.println("It’s Day 1 (Sunday) of our journey in JOJOLands!");
        printCurrentLocation();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("[1] Move to:");
            for (JojolandLocation neighbor : currentLocation.getPaths().keySet()) {
                System.out.println("[" + neighbor.getName().charAt(0) + "] " + neighbor.getName());
            }
            System.out.println("[2] Advance to Next Day");
            System.out.println("[3] Save Game");
            System.out.println("[4] Exit");
            System.out.print("Select: ");

            String input = scanner.nextLine().trim();
            if (input.equals("2")) {
                System.out.println("It’s Day 2 (Monday) of our journey in JOJOLands!");
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

    private void saveGame() {
        // Add your game saving logic here
    }

    public static void main(String[] args) {
        JOJOLandsGame game = new JOJOLandsGame();
        game.start();
    }
}
