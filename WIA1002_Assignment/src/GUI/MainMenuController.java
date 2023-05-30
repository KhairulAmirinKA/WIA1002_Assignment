/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author Khairul<Khairul Amirin UM>
 */
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.stage.Stage;



public class MainMenuController {
    
    @FXML
    private Pane pane;
    
    @FXML
    Button start; //click this, will go to map
    
    @FXML
    private Button exit;
    
    
    public void startButton() {
        try {
            // Load the new FXML file
            FXMLLoader mapLoader = new FXMLLoader(getClass().getResource("Map.fxml"));
            Pane pane = mapLoader.load();

            MapController mapController= mapLoader.getController();
            
            // Get the stage from the Start button
            Stage stage = (Stage) start.getScene().getWindow();

            // Set the new scene on the stage
            Scene scene = new Scene(pane,600,500);
            stage.setScene(scene);
            
            stage.setTitle("Jojolands Map");
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
            // Handle any exceptions that occur during loading or switching scenes
        }
    }
    public void initialize(){
        
    }
}
