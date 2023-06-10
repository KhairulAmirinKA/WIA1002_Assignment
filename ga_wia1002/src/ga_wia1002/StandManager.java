package ga_wia1002;
import java.io.*;
import java.util.*;

class StandManager {
    private List<Stand> stands;

    public StandManager() {
        stands = new ArrayList<>();
    }
    
    //method to extract info in stand.csv file, store all info in stands list
    public void loadStands(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String standName = data[0];
                String standUser = data[1];
                String destructivePower = data[2];
                String speed = data[3];
                String range = data[4];
                String stamina = data[5];
                String precision = data[6];
                String developmentPotential = data[7];

                Stand stand = new Stand(standName, standUser, destructivePower, speed, range, stamina, precision, developmentPotential);
                stands.add(stand);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stand getStand(String standName) {
        for (Stand stand : stands) {
            if (stand.getName().equalsIgnoreCase(standName)) {
                return stand;
            }
        }
        return null;
    }
    
    //check if the user got stand or not
    public Stand getStandByUser(String standUser) {
        for (Stand stand : stands) {
            if (stand.getStandUser().equalsIgnoreCase(standUser)) {
                return stand;
            }
        }
        return null;
    }
    
    public List<Stand> getStands() {
        return stands;
    }
}
