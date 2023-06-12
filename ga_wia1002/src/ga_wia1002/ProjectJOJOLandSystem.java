package ga_wia1002;

import java.util.*;

public class ProjectJOJOLandSystem {
    private static ResidentManager residentManager;
    private static StandManager standManager;
    private static String currentLocation;
    
    //the file path of residents.csv and stands.csv
    static String residentFilePath="src\\ga_wia1002\\residents.csv";
    static String standFilePath="src\\ga_wia1002\\stands.csv";

    
    public ProjectJOJOLandSystem() {
        residentManager = new ResidentManager();
        standManager = new StandManager();
        residentManager.loadResidents(residentFilePath, standFilePath);
        standManager.loadStands(standFilePath);
    }
    
    public static void main(String[] args) {
            ProjectJOJOLandSystem jojoSystem = new ProjectJOJOLandSystem();
            jojoSystem.displayResidentInformation("Polnareff Land");
            System.out.println();
            List<Resident> residents = residentManager.getResidentsByResidentialArea("Polnareff Land");
            jojoSystem.sortResidents(residents, "destructive power(asc);stand(desc)");
        }
    
    public void displayResidentInformation(String currentLocation) {
        List<Resident> residents = residentManager.getResidentsByResidentialArea(currentLocation);
        if (residents.isEmpty()) {
            System.out.println("No residents found in " + currentLocation);
            return;
        }

        System.out.println("Resident Information in " + currentLocation);
        System.out.println("+----+-----------------------+-----+--------+------------------+");
        System.out.println("| No | Name                  | Age | Gender | Stand            |");
        System.out.println("+----+-----------------------+-----+--------+------------------+");
        int counter = 1;
        for (Resident resident : residents) {
            Stand stand = standManager.getStandByUser(resident.getName());
            System.out.printf("| %-2d | %-21s | %-3s | %-6s | %-16s |\n",
                    counter, resident.getName(), resident.getAge(), resident.getGender(), stand != null ? stand.getName() : "N/A");
            counter++;
        }
        System.out.println("+----+-----------------------+-----+--------+------------------+");

        System.out.println();
        System.out.println("-+-------------------+-------+-------+---------+-----------+-");
        System.out.println(" | Destructive Power | Speed | Range | Stamina | Precision |");
        System.out.println("-+-------------------+-------+-------+---------+-----------+-");
        for (Resident resident : residents) {
            Stand stand = standManager.getStandByUser(resident.getName());
            if (stand != null) {
                System.out.printf(" | %-17s | %-5s | %-5s | %-7s | %-9s |\n",
                        stand.getDestructivePower(), stand.getSpeed(), stand.getRange(),
                        stand.getStamina(), stand.getPrecision());
            } else {
                System.out.println("| N/A | N/A | N/A | N/A | N/A |");
            }
        }
        System.out.println("-+-------------------+-------+-------+---------+-----------+-");
        System.out.println("-+-----------------------+");
        System.out.println(" | Development Potential |");
        System.out.println("-+-----------------------+");
        for (Resident resident : residents) {
            Stand stand = standManager.getStandByUser(resident.getName());
            System.out.printf(" | %-21s |\n", stand != null ? stand.getDevelopmentPotential() : "N/A");
        }
        System.out.println("-+-----------------------+");

        System.out.println();
        }

    public void sortResidents(List<Resident> residents, String sortingOrder) {

        List<Resident> sortedResidents = new ArrayList<>(residents);

        if (sortedResidents.isEmpty()) {
            System.out.println("No residents to sort.");
            return;
              }

        String[] sortingTokens = sortingOrder.split(";");

        List<Comparator<Resident>> comparators = new ArrayList<>();

        for (String sortingToken : sortingTokens) {
            sortingToken = sortingToken.trim();

            String[] sortingParts = sortingToken.split("\\(");
            if (sortingParts.length <2) {
                System.out.println("Invalid sorting order: " + sortingOrder);
                return;
                }
            String sortingField = sortingParts[0].trim();
            String sortingDirection = sortingParts[1].replaceAll("\\)", "").trim();



            Comparator<Resident> comparator = null;

        switch (sortingField.toLowerCase()) {
           case "name":
               comparator = Comparator.comparing(Resident::getName);
               break;
           case "age":
               comparator = Comparator.comparingInt(Resident::getAge);
               break;
           case "gender":
               comparator = Comparator.comparing(Resident::getGender);
               break;
           case "destructive power":
               comparator = (r1, r2) -> {
                   Stand s1 = standManager.getStandByUser(r1.getName());
                   Stand s2 = standManager.getStandByUser(r2.getName());
                   if (s1 != null && s2 != null) {
                       return s1.getDestructivePower().compareTo(s2.getDestructivePower());
                   } else if (s1 != null) {
                       return -1;
                   } else if (s2 != null) {
                       return 1;
                   } else {
                       return 0;
                   }
               };
               break;
            case "stand":
                comparator = (r1, r2) -> {
                    Stand s1 = standManager.getStandByUser(r1.getName());
                    Stand s2 = standManager.getStandByUser(r2.getName());
                    if (s1 != null && s2 != null) {
                        return s1.getName().compareTo(s2.getName()); // Compare by stand name
                    } else if (s1 != null) {
                        return -1;
                    } else if (s2 != null) {
                        return 1;
                    } else {
                        return 0;
                    }
                };
                break;
           case "speed":
               comparator = (r1, r2) -> {
                   Stand s1 = standManager.getStandByUser(r1.getName());
                   Stand s2 = standManager.getStandByUser(r2.getName());
                   if (s1 != null && s2 != null) {
                       return s1.getSpeed().compareTo(s2.getSpeed());
                   } else if (s1 != null) {
                       return -1;
                   } else if (s2 != null) {
                       return 1;
                   } else {
                       return 0;
                   }
               };
               break;
           case "precision":
               comparator = (r1, r2) -> {
                   Stand s1 = standManager.getStandByUser(r1.getName());
                   Stand s2 = standManager.getStandByUser(r2.getName());
                   if (s1 != null && s2 != null) {
                       return s1.getPrecision().compareTo(s2.getPrecision());
                   } else if (s1 != null) {
                       return -1;
                   } else if (s2 != null) {
                       return 1;
                   } else {
                       return 0;
                   }
               };
               break;
           case "range":
               comparator = (r1, r2) -> {
                   Stand s1 = standManager.getStandByUser(r1.getName());
                   Stand s2 = standManager.getStandByUser(r2.getName());
                   if (s1 != null && s2 != null) {
                       return s1.getRange().compareTo(s2.getRange());
                   } else if (s1 != null) {
                       return -1;
                   } else if (s2 != null) {
                       return 1;
                   } else {
                       return 0;
                   }
               };
               break;
           case "stamina":
               comparator = (r1, r2) -> {
                   Stand s1 = standManager.getStandByUser(r1.getName());
                   Stand s2 = standManager.getStandByUser(r2.getName());
                   if (s1 != null && s2 != null) {
                       return s1.getStamina().compareTo(s2.getStamina());
                   } else if (s1 != null) {
                       return -1;
                   } else if (s2 != null) {
                       return 1;
                   } else {
                       return 0;
                   }
               };
               break;
           case "potential":
               comparator = (r1, r2) -> {
                   Stand s1 = standManager.getStandByUser(r1.getName());
                   Stand s2 = standManager.getStandByUser(r2.getName());
                   if (s1 != null && s2 != null) {
                       return s1.getDevelopmentPotential().compareTo(s2.getDevelopmentPotential());
                   } else if (s1 != null) {
                       return -1;
                   } else if (s2 != null) {
                       return 1;
                   } else {
                       return 0;
                   }
               };
               break;
           default:
               System.out.println("Invalid sorting field: " + sortingField);
               return;
            }           
            
            if (sortingDirection.equalsIgnoreCase("asc")) {
                comparators.add(comparator);
            } else if (sortingDirection.equalsIgnoreCase("desc")) {
                comparators.add(comparator.reversed());
            } else {
                System.out.println("Invalid sorting direction: " + sortingDirection);
                return;
            }
            if (sortingDirection.equalsIgnoreCase("asc")) {
                comparators.add(comparator);
            } else if (sortingDirection.equalsIgnoreCase("desc")) {
                comparators.add(comparator.reversed());
            } else {
                System.out.println("Invalid sorting direction: " + sortingDirection);
                return;
            }
            comparators.add(comparator);
               }

               Comparator<Resident> finalComparator = comparators.stream().reduce(Comparator::thenComparing).orElse(null);

               if (finalComparator != null) {
                   Collections.sort(sortedResidents, finalComparator);
                   displaySortedResidents(sortedResidents);
               } else {
                   System.out.println("No valid sorting criteria found.");
               }
           }


    public void displaySortedResidents(List<Resident> residents) {
        System.out.println("Sorted Resident Information");
        System.out.println("+----+-----------------------+-----+--------+------------------+-------------------+-------+-------+---------+-----------+-----------------------+");
        System.out.println("| No | Name                  | Age | Gender | Stand            | Destructive Power | Speed | Range | Stamina | Precision | Development Potential |");
        System.out.println("+----+-----------------------+-----+--------+------------------+-------------------+-------+-------+---------+-----------+-----------------------+");

        int counter = 1;
        for (Resident resident : residents) {
            Stand stand = standManager.getStandByUser(resident.getName());
            System.out.printf("| %-2d | %-21s | %-3s | %-6s | %-16s | %-17s | %-5s | %-5s | %-7s | %-9s | %-21s |\n",
                    counter, resident.getName(), resident.getAge(), resident.getGender(),
                    stand != null ? stand.getName() : "N/A",
                    stand != null ? stand.getDestructivePower() : "N/A",
                    stand != null ? stand.getSpeed() : "N/A",
                    stand != null ? stand.getRange() : "N/A",
                    stand != null ? stand.getStamina() : "N/A",
                    stand != null ? stand.getPrecision() : "N/A",
                    stand != null ? stand.getDevelopmentPotential() : "N/A");
            counter++;
        }

        System.out.println("+----+-----------------------+-----+--------+------------------+-------------------+-------+-------+---------+-----------+-----------------------+");
        System.out.println();
    }

    }


