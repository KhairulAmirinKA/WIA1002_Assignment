/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplicationlast120;

import java.util.Scanner;

/**
 *
 * @author Abdul Hadi
 */
public class JavaApplicationlast120 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         JojoLandsGame game = new JojoLandsGame();
        // Adding locations
        game.addLocation("Town Hall", "The bustling Town Hall");
        game.addLocation("Cafe Deux Magots", "A charming cafe");
        game.addLocation("Jade Garden", "A beautiful garden");
        game.addLocation("Morioh Grand Hotel", "A luxurious hotel");
        game.addLocation("Trattoria Trussardi", "An exquisite Italian restaurant");
        game.addLocation("Green Dolphin Street Prison", "A high-security prison");
        game.addLocation("San Giorgio Maggiore", "A stunning island");

        // Adding roads between locations
        game.addRoad("Town Hall", "Cafe Deux Magots");
        game.addRoad("Town Hall", "Jade Garden");
        game.addRoad("Town Hall", "Morioh Grand Hotel");
        game.addRoad("Morioh Grand Hotel", "Trattoria Trussardi");
        game.addRoad("Trattoria Trussardi", "Green Dolphin Street Prison");
        game.addRoad("Trattoria Trussardi", "San Giorgio Maggiore");

        // Adding missions to locations
        game.addMission("Town Hall", "Find the missing artifact");
        game.addMission("Cafe Deux Magots", "Deliver a love letter");
        game.addMission("Jade Garden", "Solve the puzzle");
        game.addMission("Morioh Grand Hotel", "Investigate the haunted room");
        game.addMission("Trattoria Trussardi", "Cook a special dish");
        game.addMission("Green Dolphin Street Prison", "Escape from prison");
        game.addMission("San Giorgio Maggiore", "Retrieve the stolen painting");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            game.displayCurrentDay();
            game.displayCurrentLocation();
            game.displayMenu();

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter the location: ");
                    String location = scanner.nextLine();
                    game.move(location);
                    break;
                case 2:
                    game.advanceToNextDay();
                    break;
                case 3:
                    System.out.println("Game saved.");
                    break;
                case 4:
                    System.out.println("Exiting the game.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
                  
                   
            System.out.println("======================================================================");
        }
    }
}
      
  
