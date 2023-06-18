//package ga_wia1002;
//
//import java.util.*;
//
//class Location {
//    String name;
//    int distance;
//
//    public Location(String name, int distance) {
//        this.name = name;
//        this.distance = distance;
//    }
//}
//
//class Path implements Comparable<Path> {
//    private List<String> locations;
//    private int distance;
//
//    public Path(List<String> locations, int distance) {
//        this.locations = locations;
//        this.distance = distance;
//    }
//
//    public List<String> getLocations() {
//        return locations;
//    }
//
//    public int getDistance() {
//        return distance;
//    }
//
//    @Override
//    public int compareTo(Path other) {
//        return Integer.compare(distance, other.distance);
//    }
//}
//
//
////Q7
////Q7
////package ga_wia1002;
////import java.util.ArrayList;
////import java.util.List;
////
/////**
//// *
//// * @author Abdul Hadi
//// */
////public class Location {
////     String name;
////     public List<Location> connections;
////    public List<Integer> cablesLengths;
////    public Location(String name) {
////        this.name = name;
////        this.connections = new ArrayList<>();
////        this.cablesLengths = new ArrayList<>();
////    }
////    public void addConnection(Location location, int length) {
////        connections.add(location);
////        cablesLengths.add(length);
////    }
////}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga_wia1002;
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
