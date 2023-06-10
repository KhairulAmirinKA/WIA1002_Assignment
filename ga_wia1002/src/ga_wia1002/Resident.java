package ga_wia1002;

import java.util.ArrayList;
import java.util.List;

public class Resident {
    private String name;
    private int age;
    private String gender;
    private String residentialArea;
    private String stand;
    private String order;
    private List<String> orderHistory;
    private String restaurant;

    public Resident(String name, int age, String gender, String residentialArea, String stand, String order, List<String> orderHistory) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.residentialArea = residentialArea;
        this.stand = stand;
        this.order = order;
        this.orderHistory = orderHistory;
    }

    public Resident(String name, int age, String gender,String residentialArea,String order,List<String>orderHistory) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.order = order;
        this.residentialArea = residentialArea;
        this.orderHistory = orderHistory;
    }
 
    public Resident(String name, int age, String gender, String residentialArea,  String stand) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.residentialArea = residentialArea;
        this.stand = stand;
    }
    
    public Resident(String name) {
        this.name = name;
        this.orderHistory = new ArrayList<>();
    }

    public void addToOrderHistory(String food, String restaurant) {
        int day = orderHistory.size() + 1;
        String order = String.format("| %-3d | %-46s | %-19s |", day, food, restaurant);
        orderHistory.add(order);
    }

    public String getRestaurant() {
        return restaurant;
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

    public void setOrder(String order) {
        this.order = order;
    }
 
    public String getOrder() {
        return order;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getResidentialArea() {
        return residentialArea;
    }

    public void setResidentialArea(String residentialArea) {
        this.residentialArea = residentialArea;
    }

    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }   

    public void setOrderHistory(List<String> orderHistory) {
        this.orderHistory = orderHistory;
    }
}