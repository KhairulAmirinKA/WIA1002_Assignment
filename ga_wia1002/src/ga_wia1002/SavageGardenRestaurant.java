 package ga_wia1002;
import java.util.*;

public class SavageGardenRestaurant extends JOJOLandsRestaurant {
    private int currentDay;
    public SavageGardenRestaurant(int currentDay) {
        super();
        this.currentDay=currentDay;
    }

    @Override
    public void processOrders() {
        List<Resident> processedList = SavageGardenRule.processOrder(waitingList, currentDay);
        orderProcessingList.addAll(processedList);
    }}
class SavageGardenRule {
    public static List<Resident> processOrder(List<Resident> waitingList, int currentDay) {
         List<Resident> waitingListCopy = new ArrayList<>(waitingList);
         LinkedList<Resident> processedList = new LinkedList<>();
         int count = 1;
         int index = 1;

         while (!waitingListCopy.isEmpty()) {
             if (count == currentDay) {
                 // Add the removed person to the end of the processed list
                 processedList.addLast(waitingListCopy.remove(index - 1));
                 count = 1;

                 if (index >= waitingListCopy.size()) {
                     // Reached the end of the waiting list, start over from the last person in reverse
                     Collections.reverse(waitingListCopy);
                     index = 1;
                 }
             } 
             else {
                 count++;
                 index++;

                 if (index >= waitingListCopy.size()) {
                     // Reached the end of the waiting list, start over from the first person
                     Collections.reverse(waitingListCopy);
                     index = 1;
                 }
             }
         }

         return processedList;
     }
    }