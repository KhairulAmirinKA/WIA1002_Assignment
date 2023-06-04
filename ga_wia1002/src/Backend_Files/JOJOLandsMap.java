package Backend_Files;
import java.util.*;

public class JOJOLandsMap {
    private Map<String, JojolandLocation> locations;

    public JOJOLandsMap() {
        locations = new HashMap<>();
        initializeMap();
    }

    private void initializeMap() {
        addLocation("Town Hall");
        addLocation("Moriah Grand Hotel");
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

        addPath("Town Hall", "Moriah Grand Hotel", 5);
        addPath("Town Hall", "Jade Garden", 5);
        addPath("Town Hall", "Cafe Deux Magots", 4);
        addPath("Cafe Deux Magots", "Polnareff Land", 4);
        addPath("Cafe Deux Magots", "Jade Garden", 3);
        addPath("Cafe Deux Magots", "Savage Garden", 4);
        addPath("Polnareff Land", "Savage Garden", 6);
        addPath("Moriah Grand Hotel", "Trattoria Trussardi", 6);
        addPath("Moriah Grand Hotel", "Jade Garden", 3);
        addPath("Jade Garden", "San Giorgio Maggiore", 2);
        addPath("Jade Garden", "Joestar Mansion", 2);
        addPath("Joestar Mansion", "Savage Garden", 4);
        addPath("Savage Garden", "Vineyard", 8);
        addPath("Savage Garden", "Polnareff Land", 6);
        addPath("Vineyard", "Joestar Mansion", 3);
        addPath("Vineyard", "Libeccio", 6);
        addPath("Vineyard", "DIO's Mansion", 3);
        addPath("Joestar Mansion", "Libeccio", 6);
        addPath("San Giorgio Maggiore", "Libeccio", 4);
        addPath("Trattoria Trussardi", "San Giorgio Maggiore", 3);
        addPath("Trattoria Trussardi", "Green Dolphin Street Prison", 6);
        addPath("Libeccio", "Green Dolphin Street Prison", 3);
        addPath("Libeccio", "DIO's Mansion", 2);
        addPath("DIO's Mansion", "Angelo Rock", 3);
        addPath("Angelo Rock", "Green Dolphin Street Prison", 2);
    }

    private void addLocation(String locationName) {
        JojolandLocation location = new JojolandLocation(locationName);
        locations.put(locationName, location);
    }

    private void addPath(String location1, String location2, int distance) {
        JojolandLocation loc1 = locations.get(location1);
        JojolandLocation loc2 = locations.get(location2);
        loc1.addPath(loc2, distance);
        loc2.addPath(loc1, distance);
    }

    public JojolandLocation getLocation(String locationName) {
        return locations.get(locationName);
    }
}

class JojolandLocation {
    private String name;
    private Map<JojolandLocation, Integer> paths;

    public JojolandLocation(String name) {
        this.name = name;
        paths = new HashMap<>();
    }

    public void addPath(JojolandLocation destination, int distance) {
        paths.put(destination, distance);
    }

    public String getName() {
        return name;
    }

    public Map<JojolandLocation, Integer> getPaths() {
        return paths;
    }
}