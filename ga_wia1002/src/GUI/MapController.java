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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MapController {
    
    @FXML
    private Pane pane;
    
    @FXML
   private ImageView map;
    
    @FXML
    Button backBtn;

     public void backButton() {
        try {
            // Load the new FXML file
            FXMLLoader mapLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Pane pane = mapLoader.load();

            MainMenuController mainMenuController= mapLoader.getController();
            
            // Get the stage from the Start button
            Stage stage = (Stage) backBtn.getScene().getWindow();

            // Set the new scene on the stage
            Scene scene = new Scene(pane,600,500);
            stage.setScene(scene);
            
            stage.setTitle("Main Menu");
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
            // Handle any exceptions that occur during loading or switching scenes
        }
    }
    public void initialize(){
        
    }
}
