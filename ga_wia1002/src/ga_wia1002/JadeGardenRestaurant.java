package ga_wia1002;

import java.util.ArrayList;
import java.util.List;

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
        List<Resident> processedList = new ArrayList<>();
        int left = 0;   //1st element
        int right = waitingList.size() - 1; //last element on right

        while (left <= right) {
            processedList.add(waitingList.get(left));
            if (left != right) {
                processedList.add(waitingList.get(right));
            }
            left++;
            right--;
        }

        return processedList;
    }
}