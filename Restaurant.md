package ga_wia1002;

import java.util.List;
//
//public class Restaurant {
//    private String name;
//    private List<String> menu;
//
//    public Restaurant(String name, List<String> menu) {
//        this.name = name;
//        this.menu = menu;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public List<String> getMenu() {
//        return menu;
//    }
//}
public class Restaurant {
    private String name;
    private List<String> availableFoods;

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
}
