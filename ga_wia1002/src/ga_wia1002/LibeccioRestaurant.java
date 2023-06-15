package ga_wia1002;

import java.util.*;

public class LibeccioRestaurant extends JOJOLandsRestaurant {
    private int currentDay;
    public LibeccioRestaurant(int currentDay) {
        super();
        this.currentDay=currentDay;
    }

    @Override
    public void processOrders() {
        orderProcessingList.addAll(LibeccioRule.processOrder(waitingList, currentDay));
    }
}
class LibeccioRule{
    public static List<Resident> processOrder(List<Resident> waitingList, int currentDay) {
         List<Resident> waitingListCopy = new ArrayList<>(waitingList);
         LinkedList<Resident> processedList = new LinkedList<>();
         int count = 1;     //person said number start from 1
         int index = 1;

         while (!waitingListCopy.isEmpty()) {
             if (count %currentDay==0) {
                 // Add the removed person to the end of the processed list
                 processedList.addLast(waitingListCopy.remove(index - 1));      //eg: person in 1st position is index 0 so need index-1 , person choosen served last

                 if (index > waitingListCopy.size()) {     //check this person's index out of 
                     // Reached the end of the waiting list, start over from the 1st person
                     index = 1; //back to 1st person's index
                 }
                 count ++;  //go to next person's count after remove the person
                 //no need to change index as previous person's is removed, now the index of next person is the same as the previous removed person,eg: ABC 012,B removed, now index for C is 1,replaced B location
             } 
             else {     //if person's number not the multiple of the day
                 count++;   //next person's count
                 index++;   //next person's index

                 if (index > waitingListCopy.size()) {
                     // Reached the end of the waiting list, start over from the first person
                     index = 1; 
                 }
             }
         }
         Collections.reverse(processedList);    //make sure it back to First in last out order
         return processedList;
     }
    }
