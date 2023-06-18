package ga_wia1002;
import java.util.*;

public class JOJOLandsMap {     //JojolandLocation can receive locationName and all locationPaths info(path and distance)
    private Map<String, JojolandLocation> locations;    //create a map named locations which contain 2 types: String and JojolandLocation 
    private JojolandLocation previousLocation; // Add a variable to keep track of the previous location

    
    public JOJOLandsMap() {
        locations = new HashMap<>();    //intialise locations        
        previousLocation = null; // Initialize previousLocation as null
        initializeDefaultMap();   
        initializeParallelMap();
        initializedAlternateMap(); 
    }
    
    public JojolandLocation getPreviousLocation() {
           return previousLocation;
       }

    public List<String> getNeighbours(String locationName) {
        List<String> neighbours = new ArrayList<>();
        JojolandLocation location = getLocation(locationName);
        if (location != null) {
            Map<JojolandLocation, Integer> paths = location.getPaths();
            for (JojolandLocation neighbour : paths.keySet()) {
                neighbours.add(neighbour.getName());
            }
        }
        return neighbours;
    }
    public int getEdgeWeight(String location1, String location2) {
        JojolandLocation loc1 = getLocation(location1);
        JojolandLocation loc2 = getLocation(location2);
        if (loc1 != null && loc2 != null) {
            Map<JojolandLocation, Integer> paths = loc1.getPaths();
            return paths.getOrDefault(loc2, -1); // Return the edge weight or -1 if the edge doesn't exist
        }
        return -1; // Return -1 if either location is not found
    }


public void initializeDefaultMap(){
    addLocation("Town Hall");
    addLocation("Morioh Grand Hotel");
    addLocation("Jade Garden");
    addLocation("Cafe Deux Magots");
    addLocation("Polnareff Land");
    addLocation("Savage Garden");
    addLocation("Trattoria Trussardi");
    addLocation("San Giorgio Maggiore");
    addLocation("Joestar Mansion");
    addLocation("Vineyard");
    addLocation("Libeccio");
    addLocation("DIO's Mansion");
    addLocation("Angelo Rock");
    addLocation("Green Dolphin Street Prison");
  
        addPath("Town Hall", "Morioh Grand Hotel", 5);
        addPath("Town Hall", "Jade Garden", 5);
        addPath("Town Hall", "Cafe Deux Magots", 4);
        addPath("Cafe Deux Magots", "Polnareff Land", 4);
        addPath("Cafe Deux Magots", "Jade Garden", 3);
        addPath("Cafe Deux Magots", "Savage Garden", 4);
        addPath("Polnareff Land", "Savage Garden", 6);
        addPath("Morioh Grand Hotel", "Trattoria Trussardi", 6);
        addPath("Morioh Grand Hotel", "Jade Garden", 3);
        addPath("Jade Garden", "San Giorgio Maggiore", 2);
        addPath("Jade Garden", "Joestar Mansion", 2);
        addPath("Joestar Mansion", "Savage Garden", 4);
        addPath("Joestar Mansion", "Libeccio", 6);
        addPath("Joestar Mansion", "Vineyard", 3);
        addPath("Savage Garden", "Vineyard", 8);
        addPath("Vineyard", "Libeccio", 6);
        addPath("Vineyard", "DIO's Mansion", 3);
        addPath("San Giorgio Maggiore", "Libeccio", 4);
        addPath("Trattoria Trussardi", "San Giorgio Maggiore", 3);
        addPath("Trattoria Trussardi", "Green Dolphin Street Prison", 6);
        addPath("Libeccio", "Green Dolphin Street Prison", 3);
        addPath("Libeccio", "DIO's Mansion", 2);
        addPath("DIO's Mansion", "Angelo Rock", 3);
        addPath("Angelo Rock", "Green Dolphin Street Prison", 2);
}
  public void initializeParallelMap () {
    addLocation("Town Hall");
    addLocation("Morioh Grand Hotel");
    addLocation("Jade Garden");
    addLocation("Cafe Deux Magots");
    addLocation("Polnareff Land");
    addLocation("Savage Garden");
    addLocation("Trattoria Trussardi");
    addLocation("San Giorgio Maggiore");
    addLocation("Joestar Mansion");
    addLocation("Vineyard");
    addLocation("Libeccio");
    addLocation("DIO's Mansion");
    addLocation("Angelo Rock");
    addLocation("Green Dolphin Street Prison");
        addPath("Town Hall", "Trattoria Trussardi", 6);
        addPath("Town Hall", "Vineyard", 3);
        addPath("Town Hall", "Libeccio", 2);
        addPath("Town Hall", "Cafe Deux Magots", 4);
        addPath("Morioh Grand Hotel", "Joestar Mansion", 4);
        addPath("Morioh Grand Hotel", "Cafe Deux Magots", 6);
        addPath("Trattoria Trussardi", "Joestar Mansion", 5);
        addPath("Trattoria Trussardi", "DIO's Mansion", 4);
        addPath("Trattoria Trussardi", "Angelo Rock", 3);
        addPath("Green Dolphin Street Prison", "Angelo Rock", 8);
        addPath("Green Dolphin Street Prison", "DIO's Mansion", 6);
        addPath("Angelo Rock", "DIO's Mansion", 1);
        addPath("Vineyard", "Libeccio", 3);
        addPath("Savage Garden", "Jade Garden", 4);
        addPath("Savage Garden", "San Giorgio Maggiore", 6);
        addPath("Savage Garden", "Cafe Deux Magots", 5);
        addPath("Polnareff Land", "Cafe Deux Magots", 2);
        addPath("Cafe Deux Magots", "Jade Garden", 3);
        addPath("San Giorgio Maggiore", "Joestar Mansion",5);
        addPath("Joestar Mansion","Jade Garden",3);
    }
  public void initializedAlternateMap () {
    addLocation("Town Hall");
    addLocation("Morioh Grand Hotel");
    addLocation("Jade Garden");
    addLocation("Cafe Deux Magots");
    addLocation("Polnareff Land");
    addLocation("Savage Garden");
    addLocation("Trattoria Trussardi");
    addLocation("San Giorgio Maggiore");
    addLocation("Joestar Mansion");
    addLocation("Vineyard");
    addLocation("Libeccio");
    addLocation("DIO's Mansion");
    addLocation("Angelo Rock");
    addLocation("Green Dolphin Street Prison");
    addPath("Town Hall", "Morioh Grand Hotel", 2);
    addPath("Town Hall", "Green Dolphin Street Prison", 3);
    addPath("Town Hall", "Libeccio", 7);
    addPath("Morioh Grand Hotel", "San Giorgio Maggiore", 3);
    addPath("Morioh Grand Hotel", "Joestar Mansion", 4);
    addPath("Morioh Grand Hotel", "Green Dolphin Street Prison", 2);
    addPath("Trattoria Trussardi", "Green Dolphin Street Prison", 4);
    addPath("Trattoria Trussardi", "Joestar Mansion", 5);
    addPath("Trattoria Trussardi", "Libeccio", 1);
    addPath("Libeccio", "Angelo Rock", 6);
    addPath("Angelo Rock", "Polnareff Land", 2);
    addPath("Angelo Rock", "Jade Garden", 1);
    addPath("DIO's Mansion", "Cafe Deux Magots", 1);
    addPath("DIO's Mansion", "Polnareff Land", 2);
    addPath("DIO's Mansion", "Libeccio", 2);
    addPath("Vineyard", "Savage Garden", 4);
    addPath("Vineyard", "Cafe Deux Magots", 4);
    addPath("Savage Garden", "San Giorgio Maggiore", 6);
    addPath("Polnareff Land", "Jade Garden", 2);
    addPath("Cafe Deux Magots","Libeccio",4);
    addPath("Cafe Deux Magots","Vineyard",4);
    addPath("Vineyard","Savage Garden",4);
    addPath("Savage Garden","San Giorgio Maggiore",6);
    }  
  


    private void addLocation(String locationName) {     //a method to receive String locationName
        JojolandLocation location = new JojolandLocation(locationName);
        locations.put(locationName, location);  //link addded locationName(key),location(value) together and store in hashmap named locations
    }

    private void addPath(String location1, String location2, int distance) {
        JojolandLocation loc1 = locations.get(location1);  //get location1 
        JojolandLocation loc2 = locations.get(location2);   //get location2
        loc1.addPath(loc2, distance);   //connect location 1 to 2  
        loc2.addPath(loc1, distance);   //connect location 2 to 1 to enable bidirection 
    }

    public JojolandLocation getLocation(String locationName) {
        return locations.get(locationName);
    }  

    public void setPreviousLocation(JojolandLocation location) {
        previousLocation = location;
    }
    
}

class JojolandLocation {    //in this class will have name of location and path of location(eg:A to B (5km))
    private String name;
    private Map<JojolandLocation, Integer> paths;

    public JojolandLocation(String name) {  //create a constructor which receive String type (name of the location) 
        this.name = name;
        paths = new HashMap<>();
    }


    public void addPath(JojolandLocation destination, int distance) {   //the location can addPath, destination and distance
        paths.put(destination, distance);
    }

    public String getName() {
        return name;
    }

    public Map<JojolandLocation, Integer> getPaths() {
        return paths;       //return a list of map data about paths
    }
}