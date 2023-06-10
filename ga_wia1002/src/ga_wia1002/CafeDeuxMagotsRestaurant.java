package ga_wia1002;

import java.util.*;

public class CafeDeuxMagotsRestaurant extends JOJOLandsRestaurant {
    public CafeDeuxMagotsRestaurant() {
        super();
    }

    @Override
    public void processOrders() {
        Queue<Resident> customerQueue = new LinkedList<>(waitingList);
        List<Resident> processedList = CafeDeuxMagotsRule.processOrder(customerQueue);
        orderProcessingList.addAll(processedList);
    }
}

class CafeDeuxMagotsRule {
    public static List<Resident> processOrder(Queue<Resident> customerQueue) {
        List<Resident> processedList = new ArrayList<>();
        List<Resident> temp = new ArrayList<>();

        while (!customerQueue.isEmpty()) {
            Resident oldest = null;
            Resident youngest = null;

            Iterator<Resident> iterator = customerQueue.iterator();
            while (iterator.hasNext()) {
                Resident customer = iterator.next();

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