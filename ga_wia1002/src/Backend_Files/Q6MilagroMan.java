/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend_Files;

/**
 *
 * @author Khairul<Khairul Amirin UM>
 */
import java.util.Scanner;

public class Q6MilagroMan {
    
    static Scanner sc= new Scanner(System.in);
    
    public static void main(String[] args) {
        
        System.out.println("Restaurant: Milagro Man Mode");
        
        int choice; //either 1 or 2 or 3
        
        while (true){
        System.out.println("[1] Modify Food Prices");
        System.out.println("[2] View Sales Information");
        System.out.println("[3] Exit Milagro Man");
        
            System.out.print("\nSelect: ");
            choice= sc.nextInt();
            
            //for exit
            if (choice==3) {
                break;
            }
            
            //decision
            switch(choice){
                
                case 1: 
                    modifyFoodPrices(); 
                    break;
                
                case 2:
                    salesInfo();
                    break;
            }
        
        
            System.out.println("===========================================");
        }//loop
        
        
        
        
   
        
    }
    
    //food price
    private static void modifyFoodPrices(){
        double newPrice;
        int startDay, endDay;
        
        System.out.print("Enter new price: $");
        newPrice= sc.nextDouble();
        
        System.out.print("Enter Start Day: ");
        startDay= sc.nextInt();
        
        System.out.print("Enter End Day: ");
        endDay= sc.nextInt();
        
        
    }//modify price
    
    //sales info
    private static void salesInfo(){
        System.out.println("Sales information");
        
        System.out.println("[1] View Sales");
        System.out.println("[2] View Aggregated Information");
        System.out.println(" [A] Minimum Sales\n" +
            " [B] Maximum Sales\n" +
            " [C] Top k Highest Sales\n" +
            " [D] Total and Average Sales");
        
        System.out.println("[3] Exit");
    }//salesinfo
    
            
}
