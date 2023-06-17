/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga_wia1002;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Abdul Hadi
 */
public class Superfly {

    /**
     * @param args the command line arguments
     */
    
public static void main (String [] args ){
          JojoLandsGame game = new JojoLandsGame();
        game.addLocation("Town Hall", "The bustling Town Hall");
        game.addLocation("Cafe Deux Magots", "A popular cafe in JOJOLands");
        game.addLocation("Jade Garden", "A beautiful garden in JOJOLands");
        game.addLocation("Morioh Grand Hotel", "A luxurious hotel in JOJOLands");
        game.addLocation("Trattoria Trussardi", "A famous restaurant in JOJOLands");
        game.addRoad("Town Hall", "Cafe Deux Magots");
        game.addRoad("Town Hall", "Jade Garden");
        game.addRoad("Town Hall", "Morioh Grand Hotel");
        game.addRoad("Cafe Deux Magots", "Jade Garden");
        game.addRoad("Cafe Deux Magots", "Morioh Grand Hotel");
        game.addRoad("Jade Garden", "Morioh Grand Hotel");
        game.addRoad("Morioh Grand Hotel", "Trattoria Trussardi");

        game.addMission("Cafe Deux Magots", "Find a missing book");
        game.addMission("Jade Garden", "Rescue a trapped bird");
        game.addMission("Morioh Grand Hotel", "Retrieve a lost artifact");
        game.addMission("Trattoria Trussardi", "Serve a special dish");

        Scanner scanner = new Scanner(System.in);
        boolean exitGame = false;

        while (!exitGame) {
            game.displayCurrentDay();
            game.displayCurrentLocation();
            game.displayMenu();

            System.out.println("[0] Back to Town Hall");
            System.out.println("[X] Exit");
            System.out.print("Select: ");

            String choice = scanner.nextLine();
            System.out.println("======================================================================");

            if (choice.equalsIgnoreCase("x")) {
                exitGame = true;
            } else if (choice.equalsIgnoreCase("0")) {
                game.currentLocation = "Town Hall";
            } else if (choice.length() == 1 && Character.isLetter(choice.charAt(0))) {
                int optionNum = choice.toUpperCase().charAt(0) - 'A' + 1;
                Location currentLoc = game.locations.get(game.currentLocation);
                List<Location> adjacentLocations = currentLoc.getAdjacentLocations();
                if (optionNum > 0 && optionNum <= adjacentLocations.size()) {
                    Location nextLocation = adjacentLocations.get(optionNum - 1);
                    game.currentLocation = nextLocation.getName();
                    game.history.clear();
                    game.currentDay++;
                } else {
                    System.out.println("Invalid option!");
                }
            } else {
                System.out.println("Invalid input!");
            }

            System.out.println("======================================================================");
        }
    }
    private static String getDayOfWeek(int day) {
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        if (day >= 1 && day <= 7) {
            return daysOfWeek[day - 1];
        }
        return "Unknown";
    }
}
//    public static void main(String [] args){
//        Superfly op = new Superfly();
//        op.Superfly();
//        
//    }

      
  
