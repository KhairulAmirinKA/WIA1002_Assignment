/* tester */
package Backend_Files;

import java.io.*;
import java.util.*;

public class JOJOLandsRestaurantSimulation {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.print("Select: ");
        int instruction = sc.nextInt();
        String location = "Savage Garden";    //######temporary
        
        if(instruction==2){ //View Waiting Lit and Order Processing List
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("Restaurant: "+location+"\n"); //"Jade Garden is temporary only, need to use Q1 location"

            System.out.print("Enter the number of customers: ");
            int numCustomers = sc.nextInt();
            //这个general的list很重要
            //generateCustomersFromCSV("residents.csv",numCustomers);会return customers的arraylist出来，比如5个顾客name，age，gender，order
            //所以最后会是List<Customer> customers = customers<Customers>(); return 的customers list的data全部会记录在这里
            //做一个叫customers的list出来，然后这个list里面的data（name,age,gender,order）会从generateCustomersFromCSV的这个fuction extract出来
            List<Customer> customers = generateCustomersFromCSV("residents.csv",numCustomers,location);

            //做一个selectedCustomers 的list出来，用来随机选72个里面哪几个进去吃东西
    //        List<Customer> selectedCustomers = selectRandomCustomers(customers);


            // Create restaurants, waitingOrder is the order have been done by the customers which processing and havent done yet
            String[] waitingOrder = getRandomFoodOrder(numCustomers, location);
            JOJOLandsRestaurant jadeGarden = new JadeGardenRestaurant(waitingOrder);
            JOJOLandsRestaurant cafeDeuxMagots = new CafeDeuxMagotsRestaurant(waitingOrder);
            JOJOLandsRestaurant trattoriaTrussardi = new TrattoriaTrussardiRestaurant(waitingOrder);
            JOJOLandsRestaurant libeccio = new LibeccioRestaurant(waitingOrder);
            JOJOLandsRestaurant savageGarden = new SavageGardenRestaurant(waitingOrder);

            // Add customers to waiting lists
            switch(location){
                    case "Jade Garden":
                        for (Customer customer : customers) {
                            jadeGarden.addCustomer(customer);
                        }
                        jadeGarden.processOrders();// Process orders
                        //viewWaitingList
                        System.out.println("Jade Garden: ");
                        jadeGarden.viewWaitingList();
                        jadeGarden.displayOrderProcessingList();
                        break;

                    case "Cafe Deux Magots":
                        for (Customer customer : customers) {
                            cafeDeuxMagots.addCustomer(customer);
                        }
                        cafeDeuxMagots.processOrders();
                        System.out.println("cafeDeuxMagots: ");
                        cafeDeuxMagots.viewWaitingList();
                        cafeDeuxMagots.displayOrderProcessingList();
                        break;

                    case "Trattoria Trussardi":
                        for (Customer customer : customers) {
                            trattoriaTrussardi.addCustomer(customer);
                        }
                        trattoriaTrussardi.processOrders();
                        System.out.println("trattoriaTrussardi: ");
                        trattoriaTrussardi.viewWaitingList();
                        trattoriaTrussardi.displayOrderProcessingList();
                        break;

                    case "Liberrio":
                        for (Customer customer : customers) {
                            libeccio.addCustomer(customer);
                        }
                        libeccio.processOrders();
                        System.out.println("libeccio: ");
                        libeccio.viewWaitingList();
                        libeccio.displayOrderProcessingList();                  
                        break;

                    case "Savage Garden":
                        for (Customer customer : customers) {
                            savageGarden.addCustomer(customer);
                        }        
                        savageGarden.processOrders();
                        System.out.println("savageGarden: ");
                        savageGarden.viewWaitingList();
                        savageGarden.displayOrderProcessingList();                    
                        break;
            }

            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------------------------------------");
        }
//combine with others file Q1 to Q8
        else{
            System.out.println("select 2 to view waiting list");
        }
    }
    private static List<Customer> generateCustomersFromCSV(String filename, int numberCustomers,String location) {//int numberCustomers -set number of customer,else will print out all 72 customers
        //做一个Arraylist 叫customers 来store extracted data
        List<Customer> customers = new ArrayList<>();

        //extract data
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {   
            String line;
            int count = 0;

            // Skip the first line (column headers)
            reader.readLine();

            while ((line = reader.readLine()) != null) {    //有data的话
                //这个if 是拿来control customer人数的，如果没有if的话，全部72个customers都会order
                if (count >= numberCustomers) { //1-n 比如n=5， 就只会重复5次，读5个customers
                    break;
                }
                //每个data都是用，分开的，所以split，然后把所有data 记录在data这个array里面 
                String[] data = line.split(",");
                //data=【Name0 Age1 Gender2 Residential3 Area4 Parents5】
                String name = data[0];
                String ageString = data[1];
                String gender = data[2];

                //这里file里面虽然没有order，但是我们要在这里放是因为我们的List用Customer作为data type，所以name，age，gender，order都要有在list里面
                //getRandomFoodOrder(1)的意思是会叫这个function，然后parameter（nOfCustomer=1） 然后【0】代表拿这个function弄出来的第一个element而已
                //这个function会return order【】 array， 所以会写成 String order = order【0】；的意思
                //parameter=1是因为只要1个顾客的order， 我们list会一个一个store，所以不用一次过来5-6个order，会error
                String order = getRandomFoodOrder(1,location)[0]; // Generate a random food order for each customer

                //if age = N/A in the file, we convert it to a value, so that age can become int
                int age;
                if (ageString.equals("N/A")) {
                    age = -1; // Assign a default age value or handle it according to your requirements

                //if age= number(eg:1-100), straight away change it to int(as now age=String)
                } else {
                    age = Integer.parseInt(ageString);
                }
                //做一个普通variable， datatype是Customer 然后store 全部data in Customer format，然后加进arraylist customers里面
                Customer customer = new Customer(name, age, gender, order);
                customers.add(customer);

                count++; //update count （让他在customers数量里面，不超过，不然72customers order又要来了）
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return customers;   //return arraylist customers
    }
    
    public static String[] getRandomFoodOrder(int numCustomers, String location) {
        if (numCustomers <= 0) {
            return new String[0]; // Return an empty array if there are no customers
        }

        Random random = new Random();
        String[] menu;

        switch (location) {
            case "Jade Garden":
                menu = new String[]{
                        "Braised Chicken in Black Bean Sauce",
                        "Braised Goose Web with Vermicelli",
                        "Deep-fried Hiroshima Oysters",
                        "Poached Tofu with Dried Shrimps",
                        "Scrambled Egg White with Milk"
                };
                break;

            case "Cafe Deux Magots":
                menu = new String[]{
                        "Sampling Matured Cheese Platter",
                        "Spring Lobster Salad",
                        "Spring Organic Omelette",
                        "Truffle-flavoured Poultry Supreme",
                        "White Asparagus"
                };
                break;

            case "Trattoria Trussardi":
                menu = new String[]{
                        "Caprese Salad",
                        "Creme caramel",
                        "Lamb Chops with Apple Sauce",
                        "Spaghetti alla Puttanesca"
                };
                break;

            case "Liberrio":
                menu = new String[]{
                        "Formaggio",
                        "Ghiaccio",
                        "Melone",
                        "Prosciutto and Pesci",
                        "Risotto",
                        "Zucchero and Sale"
                };
                break;

            case "Savage Garden":
                menu = new String[]{
                        "Abbacchio’s Tea",
                        "DIO’s Bread",
                        "Giorno’s Donuts",
                        "Joseph’s Tequila",
                        "Kakyoin’s Cherry",
                        "Kakyoin’s Porridge"
                };
                break;

            default:
                menu = new String[0]; // Empty menu
                break;
        }
        
        String[] orders = new String[numCustomers];

        for (int i = 0; i < numCustomers; i++) {
            int rand = random.nextInt(menu.length);
            orders[i] = menu[rand];
        }

        return orders;
    }
}
