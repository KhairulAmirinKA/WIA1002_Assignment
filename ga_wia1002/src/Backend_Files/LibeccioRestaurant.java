package ga_wia1002;

import java.util.*;

public class LibeccioRestaurant extends JOJOLandsRestaurant {
    public LibeccioRestaurant(String[] menu) {
        super(menu);
    }

    @Override
    public void processOrders() {
        orderProcessingList.addAll(LibeccioRule.processOrder(waitingList, day));
    }
}
class LibeccioRule{
    public static List<Customer> processOrder(List<Customer> waitingList, int day) {
        LinkedList<Customer> processedList = new LinkedList<>();
        int currentDay = day;   //get the current day
        int count =1;
        int index=0;
        
        while(!waitingList.isEmpty()){
            if(count%currentDay==0){
                processedList.addLast(waitingList.remove(index));
                index--;
            }
            count++;
            index++;
            
            if(index>=waitingList.size()){
                //reached the end of the waiting list, start over from begining
                index=0;
            }
        }
        //reverse the processed list to maintain the order of serving last
        Collections.reverse(processedList);
        return processedList;
    }
}
    
