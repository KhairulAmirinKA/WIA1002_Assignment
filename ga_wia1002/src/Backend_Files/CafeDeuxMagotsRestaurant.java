package Backend_Files;

import java.util.*;

public class CafeDeuxMagotsRestaurant extends JOJOLandsRestaurant {
    public CafeDeuxMagotsRestaurant(String[] menu) {
        super(menu);
    }

    @Override
    public void processOrders() {
        Queue<Customer> customerQueue = new LinkedList<>(waitingList);
        List<Customer> processedList = CafeDeuxMagotsRule.processOrder(customerQueue);
        orderProcessingList.addAll(processedList);
    }
}

class CafeDeuxMagotsRule {
    public static List<Customer> processOrder(Queue<Customer> customerQueue) {
        List<Customer> processedList = new ArrayList<>();
        List<Customer> temp = new ArrayList<>();

        while (!customerQueue.isEmpty()) {
            Customer oldest = null;
            Customer youngest = null;

            Iterator<Customer> iterator = customerQueue.iterator();
            while (iterator.hasNext()) {
                Customer customer = iterator.next();

                if (customer.getAge() != -1) {
                    if (oldest == null || customer.getAge() > oldest.getAge()) {
                        oldest = customer;
                    }
                    if (youngest == null || customer.getAge() < youngest.getAge()) {
                        youngest = customer;
                    }
                }
            }

            if (oldest != null) {
                processedList.add(oldest);
                customerQueue.remove(oldest);
            }

            if (youngest != null) {
                processedList.add(youngest);
                customerQueue.remove(youngest);
            }

            if (oldest == null && youngest == null) {
                // No customers with age != -1, process any remaining customers
                temp.addAll(customerQueue);
                customerQueue.clear();
            }
        }

        // Add customers with age = -1 at the end
        processedList.addAll(temp);

        return processedList;
    }
}
