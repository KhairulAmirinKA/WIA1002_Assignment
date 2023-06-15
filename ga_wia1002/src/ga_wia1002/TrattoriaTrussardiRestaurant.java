//Trattoria Trussardi takes equality seriously and serves the youngest man first, followed
//by the oldest woman. In the next turn, it’s the oldest man and then the youngest woman.
//This process continues until everyone is served, but if there is only one gender left in the
//queue, only one person is chosen in the next turn. Similarly, customers of unspecified
//ages will be served last for each gender.
//ym,ow,om,yw....   ow,yw,ow,yw.... unknown age
package ga_wia1002;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class TrattoriaTrussardiRestaurant extends JOJOLandsRestaurant {
    public TrattoriaTrussardiRestaurant() {
        super();
    }

    @Override
    public void processOrders() {
        List<Resident> waitingList = getWaitingList();
        List<Resident> processedList = TrattoriaTrussardiRule.processOrder(waitingList);
        orderProcessingList.addAll(processedList);
    }
}

class TrattoriaTrussardiRule {
    public static List<Resident> processOrder(List<Resident> waitingList) {
        List<Resident> processedList = new ArrayList<>();
        LinkedList<Resident> menLinkedList = new LinkedList<>();
        LinkedList<Resident> womenLinkedList = new LinkedList<>();
        LinkedList<Resident> unspecifiedAge = new LinkedList<>();

        // Separate customers into appropriate queues based on age and gender
        for (Resident customer : waitingList) {
            if(customer.getAge()==-1 ||customer.getGender()==null) {
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
        menLinkedList.sort(Comparator.comparingInt(Resident::getAge));
        // Sort womenQueue in ascending order of age
        womenLinkedList.sort(Comparator.comparingInt(Resident::getAge));

        // Process the orders based on the specified rules
        while (!menLinkedList.isEmpty() || !womenLinkedList.isEmpty()) {
            if (!menLinkedList.isEmpty()) {
                Resident youngestMan = menLinkedList.removeFirst();
                processedList.add(youngestMan);
            }
            if (!womenLinkedList.isEmpty()) {
                Resident oldestWoman = womenLinkedList.removeLast();
                processedList.add(oldestWoman);
            }
            if (!menLinkedList.isEmpty()) {
                Resident oldestMan = menLinkedList.removeLast();
                processedList.add(oldestMan);     
            }
            if (!womenLinkedList.isEmpty()) {
                Resident youngestWomen = womenLinkedList.removeFirst();
                processedList.add(youngestWomen);
            }
        }

        // Process remaining customers of unspecified gender
        while (!unspecifiedAge.isEmpty()) {
            Resident unknownAge = unspecifiedAge.removeFirst();
            processedList.add(unknownAge);
        }

        return processedList;
    }
}

