 package ga_wia1002;
import java.util.*;

public class SavageGardenRestaurant extends JOJOLandsRestaurant {
    public SavageGardenRestaurant() {
        super();
    }

    @Override
    public void processOrders() {
        List<Resident> processedList = SavageGardenRule.processOrder(waitingList, currentDay);
        orderProcessingList.addAll(processedList);
    }}
class SavageGardenRule {
    public static List<Resident> processOrder(List<Resident> waitingList, int currentDay) {
         LinkedList<Resident> processedList = new LinkedList<>();
         int count = 1;
         int index = 1;

         while (!waitingList.isEmpty()) {
             if (count == currentDay) {
                 // Add the removed person to the end of the processed list
                 processedList.addLast(waitingList.remove(index - 1));
                 count = 1;

                 if (index >= waitingList.size()) {
                     // Reached the end of the waiting list, start over from the last person in reverse
                     Collections.reverse(waitingList);
                     index = 1;
                 }
             } 
             else {
                 count++;
                 index++;

                 if (index >= waitingList.size()) {
                     // Reached the end of the waiting list, start over from the first person
                     Collections.reverse(waitingList);
                     index = 1;
                 }
             }
         }

         return processedList;
     }
    }