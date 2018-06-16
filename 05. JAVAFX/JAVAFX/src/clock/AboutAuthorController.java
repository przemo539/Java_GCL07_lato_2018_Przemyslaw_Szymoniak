/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Witajcie
 */
public class AboutAuthorController implements Initializable {
     /**
     * Initializes the controller class.
     */
     @FXML
    private void handleCloseEvent(ActionEvent event) {
           Platform.exit();
    }
         @FXML
    private void handleCloseWindowEvent(ActionEvent event) {
            Platform.exit();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
