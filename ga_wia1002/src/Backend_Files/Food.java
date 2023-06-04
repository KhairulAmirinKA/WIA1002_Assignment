package Backend_Files;

import java.util.List;

public class Food {
    private String name;
    private String restaurant;
   
    public Food(String name, String restaurant) {
        this.name = name;
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public String getRestaurant() {
        return restaurant;
    }
}
