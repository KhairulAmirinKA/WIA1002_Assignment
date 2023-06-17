/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga_wia1002;
import diavolojourney.DiavoloJourneyclass.Graph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.tools.JavaFileManager.Location;
/**
 *
 * @author Abdul Hadi
 */
public class DiavoloJourney {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 Graph graph = new Graph(7);
        graph.addLocation("Town Hall");
        graph.addLocation("Cafe Deux Magots");
        graph.addLocation("Savage Garden");
        graph.addLocation("Joestar Mansion");
        graph.addLocation("San Giorgio Maggiore");
        graph.addLocation("Jade Garden");
        graph.addLocation("Vineyard");

        graph.addEdge("Town Hall", "Cafe Deux Magots", 5);
        graph.addEdge("Cafe Deux Magots", "Savage Garden", 3);
        graph.addEdge("Savage Garden", "Joestar Mansion", 4);
        graph.addEdge("Cafe Deux Magots", "Joestar Mansion", 12);
        graph.addEdge("Town Hall", "San Giorgio Maggiore", 8);
        graph.addEdge("San Giorgio Maggiore", "Joestar Mansion", 6);
        graph.addEdge("San Giorgio Maggiore", "Vineyard", 2);

        String source = "Town Hall";
        String destination = "Joestar Mansion";
        List<String> identifiedLocations = Arrays.asList("Jade Garden", "San Giorgio Maggiore", "Vineyard");

        for (String location : identifiedLocations) {
            if (graph.locationIndices.containsKey(location)) {
                int locationIndex = graph.locationIndices.get(location);
                graph.adjacencyList.get(locationIndex).clear();
                graph.locationIndices.remove(location);
            }
        }

        List<String> shortestPath = graph.findShortestPath(source, destination);

        System.out.println("Optimal Path:");
        System.out.println(String.join(" > ", shortestPath));
        System.out.println("(" + calculateTotalDistance(graph, shortestPath) + " km)");
    }

    private static int calculateTotalDistance(Graph graph, List<String> path) {
        int totalDistance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            int sourceIndex = graph.locationIndices.get(path.get(i));
            int destinationIndex = graph.locationIndices.get(path.get(i + 1));
            for (DiavoloJourneyclass.Location neighbor : graph.adjacencyList.get(sourceIndex)) {
                if (neighbor.index == destinationIndex) {
                    totalDistance += neighbor.distance;
                    break;
                }
            }
        }
        return totalDistance;
    }
}
    

