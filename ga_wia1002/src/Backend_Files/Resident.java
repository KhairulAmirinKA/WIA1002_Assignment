package Backend_Files;

import java.util.ArrayList;
import java.util.List;

//public class Resident {
//    private String name;
//    private int budget;
//    private String lastRestaurant;
//    private List<Food> foodHistory;
//
//    public Resident(String name) {
//        this.name = name;
//        this.budget = 0;
//        this.lastRestaurant = "";
//        this.foodHistory = new ArrayList<>();
//    }
//    public Resident(String name, int budget) {
//        this.name = name;
//        this.budget = budget;
//        this.lastRestaurant = "";
//        this.foodHistory = new ArrayList<>();
//    }
//
//    public List<Food> getFoodHistory() {
//        return foodHistory;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getBudget() {
//        return budget;
//    }
//
//    public void setBudget(int budget) {
//        this.budget = budget;
//    }
//
//    public String getLastRestaurant() {
//        return lastRestaurant;
//    }
//
//    public void setLastRestaurant(String lastRestaurant) {
//        this.lastRestaurant = lastRestaurant;
//    }
//}
public class Resident {
    private String name;
    private List<String> orderHistory;

    public Resident(String name) {
        this.name = name;
        this.orderHistory = new ArrayList<>();
    }

    public void addToOrderHistory(String food, String restaurant) {
        int day = orderHistory.size() + 1;
        String order = String.format("| %-3d | %-46s | %-19s |", day, food, restaurant);
        orderHistory.add(order);
    }

    public String getName() {
        return name;
    }

    public List<String> getOrderHistory() {
        return orderHistory;
    }
    
    public void clearOrderHistory(){
        orderHistory.clear();
    }
}