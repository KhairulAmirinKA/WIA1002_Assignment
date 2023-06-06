 package ga_wia1002;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SavageGardenRestaurant extends JOJOLandsRestaurant {
    public SavageGardenRestaurant(String[] menu) {
        super(menu);
    }

    @Override
    public void processOrders() {
        List<Customer> processedList = SavageGardenRule.processOrder(waitingList, day);
        orderProcessingList.addAll(processedList);
    }}
class SavageGardenRule {
    public static List<Customer> processOrder(List<Customer> waitingList, int day) {
        LinkedList<Customer> processedList = new LinkedList<>();
        int currentDay = day;
        int count = 1;
        int index = 0;
        boolean reverse = false;
        
        while (!waitingList.isEmpty()) {
            if (count == currentDay) {
                processedList.addLast(waitingList.remove(index));
                count = 1;
                
                if (index >= waitingList.size()) {
                    reverse = true;
                    index = waitingList.size() - 1;
                }
            } else {
                count++;
                if (reverse) {
                    index--;
                    if (index < 0) {
                        reverse = false;
                        index = 0;
                    }
                } else {
                    index++;
                    if (index >= waitingList.size()) {
                        reverse = true;
                        index = waitingList.size() - 1;
                    }
                }
            }
        }
        
        return processedList;
    }
}

//class SavageGardenRule {
//    public static List<Customer> processOrder(List<Customer> waitingList, int day) {
//        LinkedList<Customer> processedList = new LinkedList<>();
//        int count = 1;
//        int index = 1;
//        
//        while (!waitingList.isEmpty()) {
//            if (count == day) {
//                
//                // Add the removed person to the end of the processed list
//                processedList.addLast(waitingList.remove(index - 1));
//                count = 1;
//                
//                if (index >= waitingList.size()) {
//                    // Reached the end of the waiting list, start over from the last person in reverse
//                    index = waitingList.size();
//                }
//            } 
//            else {
//                count++;
//                index++;
//                
//                if (index > waitingList.size()) {
//                    // Reached the end of the waiting list, start over from the first person
//                    index = 1;
//                }
//            }
//        }
//        
//        return processedList;
//    }
//}


//
//class SavageGardenRule {
//    public static List<Customer> processOrder(List<Customer> waitingList, int day) {
//        List<Customer> processedList = new ArrayList<>();
//        LinkedList<Customer> waitingCustomer = new LinkedList<>(waitingList);
//        LinkedList<Customer> waiting = new LinkedList<>();
//        int size = waitingCustomer.size();
//        int targetIndex = 0;
//
//        while (processedList.size() < size) {
//            if (waitingCustomer.isEmpty()) {
//                break;  // Exit the loop if the waitingCustomer list is empty
//            }
//
//            if (targetIndex >= waitingCustomer.size()) {
//                targetIndex = -1;  // Start over from the beginning
//            }
//
//            if (waitingCustomer.size() == 1 || day == 1) { // Last element or 1st day
//                Customer customer = waitingCustomer.removeFirst();
//                processedList.add(customer);
//            } else if ((targetIndex + 1) % day == 0) { // Can divide by day (multiple of day)
//                Customer waitingcustomer = waitingCustomer.remove(targetIndex);
//                processedList.add(waitingcustomer);
//                targetIndex--; // Adjust the targetIndex to account for the removed customer
//            } else {
//                Customer waitingcustomer = waitingCustomer.remove(targetIndex);
//                waiting.add(waitingcustomer);
//                targetIndex--; // Adjust the targetIndex to account for the removed customer
//            }
//
//            targetIndex++;
//
//            if (waitingCustomer.isEmpty() && !waiting.isEmpty()) {
//                // Reverse order
//                Collections.reverse(waiting);
//                while (!waiting.isEmpty()) {
//                    Customer remove = waiting.remove();
//                    waitingCustomer.add(remove);
//                }
//                targetIndex=day-1;
//            }
//        }
//
//        return processedList;
//    }
//}


        
//        while(processedList.size()!=size){
//            if(waitingCustomer.size()-1==0||day==1){	//last element or 1st day
//                    Customer customer = waitingCustomer.removeFirst();//1,2,3,0  ABCDEFG    BDFE
//                    processedList.add(customer);
//                    }
//            else if(targetIndex%(day-1)==0){
//                    Customer customer = waitingCustomer.remove(targetIndex);//1,2,3,0  ABCDEFG    BDFE
//                    processedList.add(customer);
//                    targetIndex++;}	//get all the multiple of the day
//            else{
//                    Customer customer = waitingCustomer.remove(targetIndex);//1,2,3,0  ABCDEFG    BDFE
//                    waiting.add(customer);
//                    targetIndex++;}
//            
//            if(waitingCustomer.isEmpty()||targetIndex>waitingCustomer.size()-1){
//            //reversed order
//                Collections.reverse(waiting);
//                    for(Customer element: waiting){
//                        waitingCustomer.add(element);
//                    }
//            }