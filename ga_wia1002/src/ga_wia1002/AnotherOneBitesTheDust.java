package ga_wia1002;
import java.util.*;

public class AnotherOneBitesTheDust {
    
    public AnotherOneBitesTheDust() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Yoshikage Kira’s path: ");
        String paths = sc.nextLine();

        String longestPath = findLongestRepeatedPath(paths);
        System.out.println("======================================================================");

        if (longestPath.length() > 0) {
            System.out.println("Bites the Dust is most likely to be activated when Kira passed through " + longestPath);
        } else {
            System.out.println("Bites the Dust is not activated.");
        }
    }

    private static String findLongestRepeatedPath(String paths) {
        String[] pathArray = paths.split(" -> ");
        int n = pathArray.length;
        Map<String, Integer> pathCounts = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                StringBuilder currentPath = new StringBuilder();
                for (int k = i; k < j; k++) {
                    currentPath.append(pathArray[k]).append(" -> ");
                }
                currentPath.append(pathArray[j]);
                String currentPathString = currentPath.toString();
                int currentCount = pathCounts.getOrDefault(currentPathString, 0) + 1;
                pathCounts.put(currentPathString, currentCount);
            }
        }

        // Remove non-repeated path and remoove path with repeated location
        Iterator<Map.Entry<String, Integer>> next = pathCounts.entrySet().iterator();
        while (next.hasNext()) {
            Map.Entry<String, Integer> entry = next.next();
            if (entry.getValue()<=1||!hasNoRepeatedLocations(entry.getKey())) {
                next.remove();
            }
        }

        // Find the longest path
        String longestPath = "";
        int max = -1;
        for (String path : pathCounts.keySet()) {
            String [] location = path.split("->");
            int maxLocation = location.length;
            if(maxLocation>max){
                longestPath = path;
                max = maxLocation;
            }
        }
        return longestPath;
    }

    private static boolean hasNoRepeatedLocations(String path) {
        String[] locations = path.split(" -> ");
        Set<String> visited = new HashSet<>();
        for (String location : locations) {
            if (!visited.add(location)) {
                return false; // Found a repeated location
            }
        }
        return true; // No repeated locations found
    }
    
    public static void main(String[] args) {

        AnotherOneBitesTheDust bitedust = new AnotherOneBitesTheDust();
        bitedust.findLongestRepeatedPath("");
}

}

//Savage Garden -> Angelo Rock -> Savage Garden -> Angelo Rock -> Savage Garden
//Libeccio -> Vineyard -> Joestar Mansion -> Vineyard -> Polnareff Land
//Jade Garden -> Town Hall -> Morioh Grand Hotel -> Jade Garden -> Town Hall -> Jade Garden -> Town Hall -> Jade Garden -> Town Hall -> Morioh Grand Hotel

