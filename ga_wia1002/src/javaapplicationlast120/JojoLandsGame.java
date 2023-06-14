/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplicationlast120;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 *
 * @author Abdul Hadi
 */
public class JojoLandsGame {
 private Map<String, Location> locations;
    private List<String> history;
    private String currentLocation;
    private int currentDay;

    public JojoLandsGame(){
        locations = new HashMap<>();
        history = new ArrayList<>();
        currentLocation = "Town Hall";
        currentDay = 1;
    }

    public void addLocation(String name, String description) {
        locations.put(name, new Location(name, description));
    }

    public void addRoad(String location1, String location2) {
        Location loc1 = locations.get(location1);
        Location loc2 = locations.get(location2);
        if (loc1 != null && loc2 != null) {
            loc1.addRoad(loc2);
            loc2.addRoad(loc1);
        }
    }

    public void addMission(String locationName, String missionDescription) {
        Location location = locations.get(locationName);
        if (location != null) {
            location.addMission(missionDescription);
        }
    }

    public void move(String locationName) {
        Location location = locations.get(locationName);
        if (location != null) {
            history.add(currentLocation);
            currentLocation = locationName;
        } else {
            System.out.println("Invalid location.");
        }
    }

    public void back() {
        if (!history.isEmpty()) {
            String previousLocation = history.remove(history.size() - 1);
            currentLocation = previousLocation;
        } else {
            System.out.println("No previous location to go back to.");
        }
    }

    public void forward() {
        if (history.size() >= 2) {
            String forwardLocation = history.get(history.size() - 2);
            history.remove(history.size() - 1);
            currentLocation = forwardLocation;
        } else {
            System.out.println("No forward location available.");
        }
    }

    public void goToTownHall() {
        history.clear();
        currentLocation = "Town Hall";
    }

    public void displayCurrentDay() {
        System.out.println("It's Day " + currentDay + " (" + getDayOfWeek(currentDay) + ") of our journey in JOJOLands!");
    }

    public void displayCurrentLocation() {
        System.out.println("Current Location: " + currentLocation);
    }

    public void displayMenu() {
        System.out.println("[1] Move to:");
        Location location = locations.get(currentLocation);
        if (location != null) {
            int optionNum = 1;
            for (Location adjacentLocation : location.getAdjacentLocations()) {
                System.out.println("[" + optionNum + "] " + adjacentLocation.getName());
                optionNum++;
            }
        }
        System.out.println("[2] Advance to Next Day");
        System.out.println("[3] Save Game");
        System.out.println("[4] Exit");
        System.out.print("Select: ");
    }

    public void displayMissions() {
        Location location = locations.get(currentLocation);
        if (location != null) {
            location.displayMissions();
        }
    }

    public void advanceToNextDay() {
        currentDay++;
    }

    public String getDayOfWeek(int day) {
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int index = (day - 1) % 7;
        return daysOfWeek[index];
    }
   public void activateAbility(String ability) {
        if (ability.equalsIgnoreCase("Red Hot Chilli Pepper")) {
            RedHotChilliPepper redHotChilliPepper = new RedHotChilliPepper();
            redHotChilliPepper.activateAbility();
        } else if (ability.equalsIgnoreCase("The Hand")) {
            TheHand theHand = new TheHand();
            theHand.activateAbility();
        } else {
            System.out.println("Invalid ability!");
        
}
   }}
