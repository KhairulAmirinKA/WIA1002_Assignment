package ga_wia1002;

import java.util.*;

public class JadeGardenRestaurant extends JOJOLandsRestaurant {
    public JadeGardenRestaurant() {
        
    }

    @Override
    public void processOrders() {
        orderProcessingList.addAll(JadeGardenRule.processOrder(waitingList));
    }
}

class JadeGardenRule {
    public static List<Resident> processOrder(List<Resident> waitingList) {
        LinkedList<Resident> waitingListCopy = new LinkedList<>(waitingList);
        List<Resident> processedList = new ArrayList<>();
        
        while (!waitingListCopy.isEmpty()) {
            if(waitingListCopy.size()>1){
                processedList.add(waitingListCopy.removeFirst());
                processedList.add(waitingListCopy.removeLast());
            }else{
                processedList.add(waitingListCopy.removeLast());
            }
        }
        return processedList;
    }
}