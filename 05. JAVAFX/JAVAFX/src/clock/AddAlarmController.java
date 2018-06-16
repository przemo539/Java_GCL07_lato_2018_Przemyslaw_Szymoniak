/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author Witajcie
 */
public class AddAlarmController implements Initializable {

    @FXML TextField nameOfAlarm;
    @FXML TextField hoursOfAlarm;
    @FXML TextField minutOfAlarm;
    @FXML Label errorText;
    @FXML Label okLabel;
    ObservableList<Alarms> lista;

    @FXML
    private void clickedButton(ActionEvent event){
        String name = nameOfAlarm.getText();
        int hour = -1000000;
        int minute= -100000;
          try{
            hour = Integer.parseInt(hoursOfAlarm.getText());
            minute = Integer.parseInt(minutOfAlarm.getText());
          }catch (Exception InvocationTargetException) {
             errorText.setText("Niepoprawne dena!");
        }
          okLabel.setText("");
          if(hour == -1000000 && minute ==-100000){
               errorText.setText("Niepoprawne dane!");
          }else if(name.length() <3){
            errorText.setText("Zbyt krotka nazwa alarmu");
        }else if(name.length() > 30){
            errorText.setText("Zbyt długa nazwa alarmu");
        }else if(hour <0 || hour >23){
            errorText.setText("Niepoprawna godzina!");
        }else if(minute <0 || minute >60){
             errorText.setText("Niepoprawne minuty!");
        }else{
             errorText.setText("");
             okLabel.setText("Dodano poprawnie");
            lista = Alarms.getAlarm();
            lista.add(new Alarms(name, hour, minute));
            
            Comparator<Alarms> comparator = Comparator.comparingInt(Alarms::getHours).thenComparingInt(Alarms::getMinutes);
            //comparator = comparator.reversed();
            FXCollections.sort(lista, comparator);
        }
        System.out.println("Clicked");        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    


    
}
