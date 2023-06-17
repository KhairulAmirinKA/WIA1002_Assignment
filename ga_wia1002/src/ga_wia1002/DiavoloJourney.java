package ga_wia1002;

import ga_wia1002.DiavoloJourneyclass.Graph;
import java.util.*;

public class DiavoloJourney {

    public void stayTheHellAway(){
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
        
        List<String> identifiedLocations = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Source: ");
        String source = scanner.nextLine();
        System.out.print("Destination: ");
        String destination = scanner.nextLine();
        System.out.print("Identified Locations: ");
        String identifiedLoc= scanner.nextLine();
        String[] loc = identifiedLoc.split(", ");
        identifiedLocations.add(loc.toString());

        

        for (String location : identifiedLocations) {
            if (graph.locationIndices.containsKey(location)) {
                int locationIndex = graph.locationIndices.get(location);
                graph.adjacencyList.get(locationIndex).clear();
                graph.locationIndices.remove(location);
            }
        }

        List<String> shortestPath = graph.findShortestPath(source, destination);
        System.out.println("==================================================================================================================");  
        System.out.println("Optimal Path:");
        System.out.println(String.join(" > ", shortestPath));
        System.out.println("(" + calculateTotalDistance(graph, shortestPath) + " km)");
        System.out.println("==================================================================================================================");  
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
    
//    public static void main(String[] args) {
//        DiavoloJourney Q6extra = new DiavoloJourney();
//        Q6extra.stayTheHellAway();
//    }
}
    

