package ga_wia1002;

import java.util.ArrayList;
import java.util.List;

public class JOJOLandsRestaurant {
    protected List<Customer> waitingList;
    protected List<Customer> orderProcessingList;
    protected String[] menu;

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
    }

    public String[] getMenu() {
        return menu;
    }
}

