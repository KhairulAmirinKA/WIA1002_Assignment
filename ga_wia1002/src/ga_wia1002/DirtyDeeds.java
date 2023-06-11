package ga_wia1002;

import java.util.*;

public class DirtyDeeds {
    private static JOJOLandsMap map;
    private Scanner sc = new Scanner(System.in);
    List<List<String>> paths = new ArrayList<>();
    
    public DirtyDeeds() {
        map = new JOJOLandsMap();
    }

    public static void main(String[] args) {
        DirtyDeeds dd = new DirtyDeeds();
        map.initializeMap();
        dd.runDirtyDeeds();
    }

    public void runDirtyDeeds() {
        List<String> currentPath = new ArrayList<>();
        System.out.print("Source: ");
        String source = sc.nextLine();
        System.out.print("Destination: ");
        String destination = sc.nextLine();
        System.out.println("\n======================================================================");

        currentPath.add(source);
        findPossiblePaths(source, destination, currentPath, paths);

        List<List<String>> possiblePaths = new ArrayList<>();
        sortPathsByDistance(paths);

        List<List<String>> topPaths = new ArrayList<>();

        for (int i = 0; i < Math.min(3, paths.size()); i++) {
            topPaths.add(paths.get(i));
        }

        System.out.println();
        System.out.println("Top Three Shortest Paths:");
        for (int i = 0; i < Math.min(3, topPaths.size()); i++) {
            double distance = calculateDistance(topPaths.get(i));
            System.out.printf("%d. ", i + 1);
            for (int j = 0; j < topPaths.get(i).size(); j++) {
                System.out.print(topPaths.get(i).get(j));
                if (j < topPaths.get(i).size() - 1) {
                    System.out.print(" -> ");
                }
            }
        System.out.printf(" (%.2f km)\n", distance);
    }
        System.out.println("======================================================================");
        System.out.println();
    }

    public static void findPossiblePaths(String currentVertex, String targetVertex,
                                         List<String> currentPath, List<List<String>> paths) {
        if (currentVertex.equals(targetVertex)) {
            paths.add(new ArrayList<>(currentPath));
            return;
        }

        List<String> neighbors = map.getNeighbours(currentVertex);
        for (String neighbor : neighbors) {
            if (!currentPath.contains(neighbor)) {
                currentPath.add(neighbor);
                findPossiblePaths(neighbor, targetVertex, currentPath, paths);
                currentPath.remove(currentPath.size() - 1);
            }
        }
    }

    public static double calculateDistance(List<String> path) {
        double distance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            String currentVertex = path.get(i);
            String nextVertex = path.get(i + 1);
            int edgeWeight = map.getEdgeWeight(currentVertex, nextVertex);

            distance += edgeWeight;
        }
        return distance;
    }

    private static void sortPathsByDistance(List<List<String>> paths) {
        paths.sort(Comparator.comparingDouble(DirtyDeeds::calculateDistance));
    }

}