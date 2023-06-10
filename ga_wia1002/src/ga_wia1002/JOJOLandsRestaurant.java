package ga_wia1002;

import java.util.ArrayList;
import java.util.List;

import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class JOJOLandsRestaurant {
    protected List<Resident> waitingList;
    protected List<Resident> orderProcessingList;
    protected String[] menu;
    JOJOLandsGame game = new JOJOLandsGame();
    int currentDay=game.getCurrentDay();
   
    
    public JOJOLandsRestaurant(){
        waitingList=new ArrayList<>();
        orderProcessingList=new ArrayList<>();
    }

    public JOJOLandsRestaurant(List<Resident> waitingList) {
        this.waitingList =waitingList;
        orderProcessingList = new ArrayList<>();

    }

    public int getCurrentDay() {
        return currentDay;
    }

    public void setWaitingList(List<Resident> waitingList) {
        this.waitingList = waitingList;
    }
    
    public List<Resident> getWaitingList() {
        return waitingList;
    }


    public void addCustomer(Resident customer) {
        waitingList.add(customer);
        orderProcessingList.add(customer);
    }
    
    public void viewWaitingList() {
        System.out.println("Waiting List: ");
        System.out.println("+----+------------------------+-----+--------+-----------------------------------------------+");
        System.out.println("| No | Name                   | Age | Gender | Order                                         |");
        System.out.println("+----+------------------------+-----+--------+-----------------------------------------------+");

        for (int i = 0; i < waitingList.size(); i++) {
            Resident customer = waitingList.get(i);
            System.out.printf("| %-2d | %-22s | %-3d | %-6s |%-46s |%n", i + 1, customer.getName(), customer.getAge(),
                    customer.getGender(),customer.getOrder());
        }

        System.out.println("+----+------------------------+-----+--------+-----------------------------------------------+-");
    }

    public void displayOrderProcessingList() {
        System.out.println("Order Processing List: ");
        System.out.println("+----+------------------------+-----+--------+-----------------------------------------------+");
        System.out.println("| No | Name                   | Age | Gender | Order                                         |");
        System.out.println("+----+------------------------+-----+--------+-----------------------------------------------+");

    
        for (int i = 0; i < orderProcessingList.size(); i++) {
            Resident customer = orderProcessingList.get(i);
            System.out.printf("| %-2d | %-22s | %-3d | %-6s |%-46s |%n", i + 1, customer.getName(), customer.getAge(),
                    customer.getGender(),customer.getOrder());
            
        }
        
   
        
      
        
        
       

        System.out.println("+----+------------------------+-----+--------+-----------------------------------------------+-");
    }
    
    //store order
    public void storeOrder(){
        try{
          //location
          String filePath="src\\ga_wia1002\\"+"jadeGarden"+"Order.txt";
          
    PrintWriter writer= new PrintWriter(new FileOutputStream(filePath));
    
        for (int i = 0; i < orderProcessingList.size(); i++) {
            Resident customer = orderProcessingList.get(i);
            
            //store
            writer.printf("| %-2d | %-22s | %-3d | %-6s |%-46s |%n", i + 1, customer.getName(), customer.getAge(),
                    customer.getGender(),customer.getOrder());
        }
        
        writer.close();
        
        
      
        }
        
        catch (IOException ie){
            ie.printStackTrace();
        }
    }

    public abstract void processOrders();

}

