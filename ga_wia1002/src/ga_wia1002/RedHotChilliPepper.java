/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga_wia1002;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author Abdul Hadi
 */
class RedHotChilliPepper {
    public void activateAbility() {
        System.out.println("You have activated the ability of Red Hot Chilli Pepper!");
        System.out.println("You can now manipulate electricity and control nearby electronic devices!");
    }
      List <com.sun.jdi.connect.spi.Connection> powercables;
        private Iterable<Location> locations;
        public RedHotChilliPepper(){
            this.powercables = new ArrayList<>();
        }
        public void upgradePowerCables(List<Location> locations, int budget) {
            for (Location source : locations) {
                for (Location destination : locations) {
                    if (source != destination) {
                        List<com.sun.jdi.connect.spi.Connection> shortestPath = findShortestPath(source, destination);
    //                        for (Connection connection : shortestPath) {
    //                            if (connection.getType().equals("power cable") && !connection.isOpen()) {
    //                                if (budget >= connection.leng) {
    //                                    connection.requireUpgrade = true;
    //                                    powercables.add(connection);
    //                                    budget -= connection.leng;
    //                            } else {
    //                                // Lacks budget to upgrade the connection
    //                                break;
    //                            }
    //                        }
    //                    }
                    }
                }
            }
        }
        private List<com.sun.jdi.connect.spi.Connection> findShortestPath(Location source, Location destination) {
            Map<Location, Integer> distances = new HashMap<>();
        Map<Location, com.sun.jdi.connect.spi.Connection> previousConnections = new HashMap<>();
        PriorityQueue<Location> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (Location location : locations) {
            distances.put(location, Integer.MAX_VALUE);
            previousConnections.put(location, null);
        }

        distances.put(source, 0);
        priorityQueue.offer(source);

        while (!priorityQueue.isEmpty()) {
            Location current = priorityQueue.poll();

            if (current == destination) {
                break;
            }
    //        for (Connection connection : current.connections) {
    //            Location neighbor = connection;
    //            int distance = distances.get(current) + connection.;
    //            if (distance < distances.get(neighbor)) {
    //                distances.put(neighbor, distance);
    //                previousConnections.put(neighbor, connection);
    //                priorityQueue.offer(neighbor);
    //            }
    //        }
        }
        List<com.sun.jdi.connect.spi.Connection> shortestPath = new ArrayList<>();
        Location current = destination;
        while (previousConnections.get(current) != null) {
            com.sun.jdi.connect.spi.Connection connection = previousConnections.get(current);
            shortestPath.add(connection);

        }
        Collections.reverse(shortestPath);
        return shortestPath;
        }
    }



    
