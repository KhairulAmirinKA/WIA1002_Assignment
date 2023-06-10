
package ga_wia1002;
import java.io.*;
import java.util.*;

//a class that uses to extract info from residents.csv and stand.csv file
public class ResidentManager {
    private List<Resident> residents;

    public ResidentManager() {
        residents = new ArrayList<>();
    }
    
    //read residents.csv file and extract info of the residents(name,age,gender,residentialArea), and get standUsers name from stand.csv file,add all info into residents list
    public void loadResidents(String filePath, String standsFilePath) {
        Map<String, String> standUsers = loadStandUsers(standsFilePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                String ageString = data[1];
                String gender = data[2];
                String residentialArea = data[3];
                String parents = ""; // Initialize parents as an empty string

                if (data.length >= 6 && data[4] != null && data[5] != null) {
                    parents = data[4] + ", " + data[5]; // Concatenate parents with a comma and space delimiter
                }
                else{
                    parents =data[4];
                }

                //extract age
                int age;
                if (ageString.equalsIgnoreCase("N/A")) {
                    age = -1; // Use -1 to represent unknown age

                } else if (ageString.matches("\\d+")) {
                    age = Integer.parseInt(ageString);
                } else {           
                    continue;
                }
                
                //get standUsers name from stand.csv file
                String standUser = standUsers.get(name);
                Resident resident = new Resident(name, age, gender, residentialArea, standUser,parents);
                residents.add(resident);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //read csv file and extract standName and standUser then linked them together using HashMap and return standUsers
    private Map<String, String> loadStandUsers(String filePath) {
        Map<String, String> standUsers = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String standName = data[0];
                String standUser = data[1];
                standUsers.put(standName, standUser);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return standUsers;
    }

    //get the list that contain info(name,age,gender,residentialArea)
    public List<Resident> getResidents() {
        return residents;
    }
    
    //check whether the resident live in that area, if yes, then will save to residentsInArea list
    public List<Resident> getResidentsByResidentialArea(String residentialArea) {
    List<Resident> residentsInArea = new ArrayList<>();
    for (Resident resident : residents) {
        if (resident.getResidentialArea().equalsIgnoreCase(residentialArea)) {
            residentsInArea.add(resident);
        }
    }
    return residentsInArea;
}


}