package ga_wia1002;
import java.util.*;

public class CafeDeuxMagotsRestaurant extends JOJOLandsRestaurant {
    public CafeDeuxMagotsRestaurant() {
        super();
    }

    @Override
    public void processOrders() {
        LinkedList<Resident> waitingCustomer = new LinkedList<>(waitingList);
        List<Resident> processedList = CafeDeuxMagotsRule.processOrder(waitingCustomer);
        orderProcessingList.addAll(processedList);
    }
}

class CafeDeuxMagotsRule {
    public static List<Resident> processOrder(LinkedList<Resident> waitingList) {
        List<Resident> processedList = new ArrayList<>();
        LinkedList<Resident> knownAge = new LinkedList<>();
        Queue<Resident> unknownAge = new LinkedList<>();
        List<Resident> correctOrder = new LinkedList<>();

        // Separate customers into appropriate queues based on age
        for (Resident customer : waitingList) {
            if (customer.getAge() == -1) {
                unknownAge.offer(customer);
            } else {
                knownAge.add(customer);
            }
        }

        // Sort knownAge in ascending order
        knownAge.sort(Comparator.comparingInt(Resident::getAge));

// Remove customers from waitingList and rearrange them in correct order
while (!knownAge.isEmpty()||!unknownAge.isEmpty()) {
    if (!knownAge.isEmpty()) {
        if(knownAge.size()==1){
            correctOrder.add(knownAge.removeLast());
        }
        else{
        correctOrder.add(knownAge.removeLast());  // Add oldest
        correctOrder.add(knownAge.removeFirst()); // Add youngest
        } }
    else {
            correctOrder.add(unknownAge.poll());
        }
    }
        // Add customers with age = -1 at the end
        processedList.addAll(correctOrder);

        return processedList;
    }
}

