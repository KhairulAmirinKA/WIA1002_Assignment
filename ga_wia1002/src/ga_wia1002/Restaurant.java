package ga_wia1002;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private List<String> availableFoods;
    private List<String> customers=new ArrayList<>();

    public Restaurant(String name, List<String> availableFoods) {
        this.name = name;
        this.availableFoods = availableFoods;
    }

    public String getName() {
        return name;
    }

    public List<String> getAvailableFoods() {
        return availableFoods;
    }
    
    public void addCustomer(String customer){
        this.customers.add(customer);
    }
}