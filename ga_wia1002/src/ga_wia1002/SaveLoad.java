/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ga_wia1002;

/**
 *
 * @author nordi
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class SaveLoad {

    private Container content;
    private Container outputContent;

    public SaveLoad(){
        content = new Container();
        outputContent = new Container();
    }

    public void addContentToSave(Object data){
        content.add(data);
    }

    public void save(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Where would you like to save the game? Enter the file path:");
        String filePath = scanner.nextLine();
        try {
            FileOutputStream file = new FileOutputStream(filePath + ".bin");
            ObjectOutputStream saving = new ObjectOutputStream(file);
            saving.writeObject(content);
            file.close();
            saving.close();
            System.out.println("Game saved successfully.");
        } catch (Exception e){
            System.out.println("Failed to save the game.");
        }
    }

    public Container load(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file path to load the game:");
        String filePath = scanner.nextLine();
        try {
            FileInputStream file = new FileInputStream(filePath + ".bin");
            ObjectInputStream loading = new ObjectInputStream(file);
            outputContent = (Container) loading.readObject();
            file.close();
            loading.close();
            System.out.println("Game loaded successfully.");
            return outputContent;
        } catch (Exception e){
            System.out.println("Failed to load the game.");
            e.printStackTrace();
        }
        return null;
    }
}
