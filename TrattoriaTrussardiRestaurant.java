//Trattoria Trussardi takes equality seriously and serves the youngest man first, followed
//by the oldest woman. In the next turn, it’s the oldest man and then the youngest woman.
//This process continues until everyone is served, but if there is only one gender left in the
//queue, only one person is chosen in the next turn. Similarly, customers of unspecified
//ages will be served last for each gender.
//ym,ow,om,yw....   ow,yw,ow,yw.... unknown age
package ga_wia1002;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TrattoriaTrussardiRestaurant extends JOJOLandsRestaurant {
    public TrattoriaTrussardiRestaurant(String[] menu) {
        super(menu);
    }

    @Override
    public void processOrders() {
        List<Customer> waitingList = getWaitingList();
        List<Customer> processedList = TrattoriaTrussardiRule.processOrder(waitingList);
        orderProcessingList.addAll(processedList);
    }
}

class TrattoriaTrussardiRule {
    public static List<Customer> processOrder(List<Customer> waitingList) {
        List<Customer> processedList = new ArrayList<>();
        LinkedList<Customer> menLinkedList = new LinkedList<>();
        LinkedList<Customer> womenLinkedList = new LinkedList<>();
        LinkedList<Customer> unspecifiedAge = new LinkedList<>();

        // Separate customers into appropriate queues based on age and gender
        for (Customer customer : waitingList) {
            if(customer.getAge()==-1) {
                unspecifiedAge.add(customer);
            }
            else if (customer.getGender().equalsIgnoreCase("Male")) {
                menLinkedList.add(customer);
            } 
            else {
                womenLinkedList.add(customer);
            } 
        }
        
        // Sort menQueue in ascending order of age
        List<Customer> sortedMen = new ArrayList<>(menLinkedList);
        Collections.sort(sortedMen, (c1, c2) -> Integer.compare(c1.getAge(), c2.getAge()));
        menLinkedList = new LinkedList<>(sortedMen);

        // Sort womenQueue in ascending order of age
        List<Customer> sortedWomen = new ArrayList<>(womenLinkedList);
        Collections.sort(sortedWomen, (c1, c2) -> Integer.compare(c1.getAge(), c2.getAge()));
        womenLinkedList = new LinkedList<>(sortedWomen);

        // Process the orders based on the specified rules
        while (!menLinkedList.isEmpty() || !womenLinkedList.isEmpty()) {
            if (!menLinkedList.isEmpty()) {
                Customer youngestMan = menLinkedList.removeFirst();
                processedList.add(youngestMan);
            }
            if (!womenLinkedList.isEmpty()) {
                Customer oldestWoman = womenLinkedList.removeLast();
                processedList.add(oldestWoman);
            }
            if (!menLinkedList.isEmpty()) {
                Customer oldestMan = menLinkedList.removeLast();
                processedList.add(oldestMan);     
            }
            if (!womenLinkedList.isEmpty()) {
                Customer youngestWomen = womenLinkedList.removeFirst();
                processedList.add(youngestWomen);
            }
        }

        // Process remaining customers of unspecified gender
        while (!unspecifiedAge.isEmpty()) {
            Customer unknownAge = unspecifiedAge.removeFirst();
            processedList.add(unknownAge);
        }

        return processedList;
    }
}

