/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GUI;

/**
 *
 * @author Khairul<Khairul Amirin UM>
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JojoGUI extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        try {
            
         
        FXMLLoader loader= new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        
        Pane pane= loader.load();
        
        MainMenuController mainMenuController= loader.getController();
        
        Scene scene= new Scene(pane,600,500);
        primaryStage.setScene(scene);
        
        primaryStage.setTitle("JojoLands");
        primaryStage.show();
    }//try
        
        
        catch (Exception e) {
            e.printStackTrace();
        }
    
}
}
