/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.util.HashMap;

/**
 *
 * @author Abdul Hadi
 */
public class testing {
    
    
    //bubble sort
    public static void bubbleSort(double[] sales_arr){
        
        int N= sales_arr.length;
        boolean swap= true;
        
        for (int i=0;i< N-1; i++){
            swap= false;
            
            for (int j=0; j<N-i-1; j++){
                
                if (sales_arr[j+1]> sales_arr[j]){
                    double temp= sales_arr[j+1];
                    sales_arr[j+1]= sales_arr[j];
                    sales_arr[j]= temp;
                    swap=true;
                }
            }//j
            
            if (swap==false){
                break;
            }
        }
    }
    
    public static void main(String[] args) {
        double[] arr= {10,5,6,145};
        
        bubbleSort(arr);
        
        for (double d: arr){
            System.out.println(d);
        }
        
        
        
        HashMap<String, Integer> hashMap = new HashMap<>(); // Declare the HashMap outside the try-catch block

try {
    // Initialize the HashMap inside the try block
    //hashMap = new HashMap<>();
    hashMap.put("key", 10);
    // Other code that modifies or uses the HashMap
} catch (Exception e) {
    // Handle the exception
    e.printStackTrace();
}

// You can access the data of the HashMap outside the try-catch block
if (hashMap != null) {
    Integer value = hashMap.get("key");
    if (value != null) {
        System.out.println(value);
    } else {
        System.out.println("Key not found in the HashMap.");
    }
} else {
    System.out.println("HashMap was not initialized or an exception occurred.");
}

        System.out.println(hashMap.get("key"));

    }
}
