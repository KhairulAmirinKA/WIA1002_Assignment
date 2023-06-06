package Backend_Files;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JOJOLandsRestaurant{
    protected List<Customer> waitingList;
    protected List<Customer> orderProcessingList;
    protected String[] menu;
    private Map<String, Integer> salesInfo;

    protected int day = 2;  //#############temporary , need to change according Q1

    public List<Customer> getWaitingList() {
        return waitingList;
    }

    public int getDay() {
        return day;
    }

    public JOJOLandsRestaurant(String[] menu) {
        waitingList = new ArrayList<>();
        orderProcessingList = new ArrayList<>();
        this.menu = menu;
        salesInfo = new HashMap<>();
        initializeSalesInfo();
    }

    public void addCustomer(Customer customer) {
        waitingList.add(customer);
    }

    public void viewWaitingList() {
        System.out.println("Waiting List: ");
        System.out.println("+----+------------------------+-----+--------+---------------------------------------+");
        System.out.println("| No | Name                   | Age | Gender | Order                                 |");
        System.out.println("+----+------------------------+-----+--------+---------------------------------------+");

        for (int i = 0; i < waitingList.size(); i++) {
            Customer customer = waitingList.get(i);
            System.out.printf("| %-2d | %-22s | %-3d | %-6s |%-38s |%n", i + 1, customer.getName(), customer.getAge(),
                    customer.getGender(),customer.getOrder());
        }

        System.out.println("+----+------------------------+-----+--------+---------------------------------------+-");
    }

    public void displayOrderProcessingList() {
        System.out.println("Order Processing List: ");
        System.out.println("+----+------------------------+-----+--------+---------------------------------------+");
        System.out.println("| No | Name                   | Age | Gender | Order                                 |");
        System.out.println("+----+------------------------+-----+--------+---------------------------------------+");

        for (int i = 0; i < orderProcessingList.size(); i++) {
            Customer customer = orderProcessingList.get(i);
            System.out.printf("| %-2d | %-22s | %-3d | %-6s |%-38s |%n", i + 1, customer.getName(), customer.getAge(),
                    customer.getGender(),customer.getOrder());
        }

        System.out.println("+----+------------------------+-----+--------+---------------------------------------+-");
    }

    public void processOrders() {
        // Sort the waiting list by arrival time (ascending order)
//        waitingList.sort(Comparator.comparing(Customer::getName)); // Sort by name (default)

        // Apply the specific rule for order processing based on the restaurant
        if (this instanceof JadeGardenRestaurant) {
            orderProcessingList.addAll(JadeGardenRule.processOrder(waitingList));
        }
        else if (this instanceof LibeccioRestaurant) {
            orderProcessingList.addAll(LibeccioRule.processOrder(waitingList, day));
        }
        
        updateSalesInfo();
    }

    public String[] getMenu() {
        return menu;
    }

    
    //Khairul
    public void viewSalesInfo() {
        double totalSales = 0.0;
        
        System.out.println("+-------------------------------------+----------+-------------+");
        System.out.println("| Food                                | Quantity | Total Price |");
        System.out.println("+-------------------------------------+----------+-------------+");

        for (Map.Entry<String, Integer> entry : salesInfo.entrySet()) {
            String item = entry.getKey();
            int quantity = entry.getValue();
            double price = calculatePrice(item, quantity);
            totalSales += price;
            
            System.out.printf("| %-36s | %-8d | $%-9.2f |%n", item, quantity, price);
        }

        System.out.println("+-------------------------------------+----------+-------------+");
        System.out.printf("| Total Sales                         |          | $%-9.2f |%n", totalSales);
        System.out.println("+-------------------------------------+----------+-------------+");
    }

    private void initializeSalesInfo() {
        for (String item : menu) {
            salesInfo.put(item, 0);
        }
    }

    private void updateSalesInfo() {
        for (Customer customer : orderProcessingList) {
            String order = customer.getOrder();
            
                if (salesInfo.containsKey(order)) {
                    int currentQuantity = salesInfo.get(order);
                    salesInfo.put(order, currentQuantity + 1);
                } else {
                    // Handle the case when the item is not in the menu
                    System.out.println("Item not found in the menu.");
                }
            }
        }
    }
    
    private double calculatePrice(String item, int quantity) {
        // Calculate the price for the item based on your pricing logic
        // This is just a placeholder, you need to implement the actual calculation
        double price = 0.0;
        
        // Example pricing logic based on the quantity
        if (item.equals("Braised Chicken in Black Bean Sauce")) {
            price = 15.00 * quantity;
        } else if (item.equals("Braised Goose Web with Vermicelli")) {
            price = 21.00 * quantity;
        }
        
        return price;
    }
}
