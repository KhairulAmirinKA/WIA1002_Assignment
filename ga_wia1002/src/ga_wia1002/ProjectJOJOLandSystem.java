package ga_wia1002;

import java.util.*;

public class ProjectJOJOLandSystem {
    private static ResidentManager residentManager;
    private static StandManager standManager;
    private static String currentLocation;

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        ProjectJOJOLandSystem displayResidentInfo = new ProjectJOJOLandSystem();
        // Ask for the current location
        System.out.print("Current Location: ");
        currentLocation = scanner.nextLine().trim();

        residentManager = new ResidentManager();
        residentManager.loadResidents("residents.csv", "stands.csv");
        List<Resident> residents = residentManager.getResidents();
        standManager = new StandManager();
        standManager.loadStands("stands.csv");
        
        

        while (true) {
            System.out.println("[1] Move to:");
            System.out.println("[A] Cafe Deux Magots [B] Savage Garden");
            System.out.println("[2] View Resident Information");
            System.out.println("[3] Back (Savage Garden)");
            System.out.println("[4] Back to Town Hall");
            System.out.print("Select: ");
            String input = scanner.nextLine();

            if (input.equals("1")) {
                System.out.print("Move to (A/B): ");
                String moveTo = scanner.nextLine();
                if (moveTo.equalsIgnoreCase("A")) {
                    currentLocation = "Cafe Deux Magots";
                } else if (moveTo.equalsIgnoreCase("B")) {
                    currentLocation = "Savage Garden";
                }
            } else if (input.equals("2")) {
                displayResidentInfo.displayResidentInformation(currentLocation);
            } else if (input.equals("3")) {
                currentLocation = "Savage Garden";
            } else if (input.equals("4")) {
                currentLocation = "Town Hall";
            } else {
                System.out.println("Invalid option. Please try again.");
            }

            System.out.println();
    System.out.println("[1] View Resident's Profile");
    System.out.println("[2] Sort");
    System.out.println("[3] Exit");
    System.out.print("Select: ");

    String option = scanner.nextLine();
    switch (option) {
        case "1":
            System.out.print("Enter the resident number to view profile: ");
            int residentNumber = scanner.nextInt();
            if (residentNumber >= 1 && residentNumber <= residents.size()) {
                Resident selectedResident = residents.get(residentNumber - 1);
                displayResidentInfo.viewResidentProfile(selectedResident);
            } else {
                System.out.println("Invalid resident number.");
            }
            break;
        case "2":
            System.out.print("Enter the sorting order (e.g., 'Stamina (ASC); Precision (DESC); Stand (ASC)'): ");
            scanner.nextLine(); // Clear the newline character from previous input
            String sortingOrder = scanner.nextLine();
            sortResidents(residents, sortingOrder);
            displayResidentInfo.displayResidentInformation(currentLocation);
            
            break;
        case "3":
            // Exit
            System.exit(0);
            break;
        default:
            System.out.println("Invalid option.");
            break;
    }
        }
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




        public void viewResidentProfile(Resident selectedResident) {
        System.out.println("Resident Profile:");
        System.out.println("Name: " + selectedResident.getName());
        System.out.println("Age: " + selectedResident.getAge());
        System.out.println("Gender: " + selectedResident.getGender());


        Stand stand = standManager.getStandByUser(selectedResident.getName());
        if (stand != null) {
            System.out.println("Stand Information:");
            System.out.println("Stand: " + stand.getName());
            System.out.println("Destructive Power: " + stand.getDestructivePower());
            System.out.println("Speed: " + stand.getSpeed());
            System.out.println("Range: " + stand.getRange());
            System.out.println("Stamina: " + stand.getStamina());
            System.out.println("Precision: " + stand.getPrecision());
            System.out.println("Development Potential: " + stand.getDevelopmentPotential());
        } else {
            System.out.println("Stand information not available.");
        }
        }

    public static void sortResidents(List<Resident> residents, String sortingOrder) {

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
            if (sortingParts.length < 2) {
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
                case "stand":
                    comparator = (r1, r2) -> {
                        Stand s1 = standManager.getStandByUser(r1.getName());
                        Stand s2 = standManager.getStandByUser(r2.getName());
                        if (s1 != null && s2 != null) {
                            return s1.getName().compareTo(s2.getName());
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
        }

        Comparator<Resident> finalComparator = comparators.stream().reduce(Comparator::thenComparing).orElse(null);

        if (finalComparator != null) {
            Collections.sort(sortedResidents, finalComparator);
            displaySortedResidents(sortedResidents);
        } else {
            System.out.println("No valid sorting criteria found.");
        }
    }

    public static void displaySortedResidents(List<Resident> residents) {
        System.out.println("Sorted Resident Information");
        System.out.println("+----+-----------------------+-----+--------+------------------+-----------------+---------+-------+---------+------------+----------------------+");
        System.out.println("| No | Name                  | Age | Gender | Stand            | Destructive Power | Speed | Range | Stamina | Precision | Development Potential |");
        System.out.println("+----+-----------------------+-----+--------+------------------+-----------------+---------+-------+---------+------------+-----------------------+");

        int counter = 1;
        for (Resident resident : residents) {
            Stand stand = standManager.getStandByUser(resident.getName());
            System.out.printf("| %-2d | %-21s | %-3s | %-6s | %-16s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n",
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

        System.out.println("+----+-----------------------+-----+--------+------------------+-----------------+");
        System.out.println();
    }

    }
