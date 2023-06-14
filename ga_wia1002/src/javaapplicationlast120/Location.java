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
public class Location {
    private String name;
    private String description;
    private List<Location> adjacentLocations;
    private List<String> missions;

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.adjacentLocations = new ArrayList<>();
        this.missions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addRoad(Location location) {
        adjacentLocations.add(location);
    }

    public List<Location> getAdjacentLocations() {
        return adjacentLocations;
    }

    public void addMission(String mission) {
        missions.add(mission);
    }

    public void displayMissions() {
        System.out.println("Missions available in " + name + ":");
        for (String mission : missions) {
            System.out.println("- " + mission);
        }
    }
}