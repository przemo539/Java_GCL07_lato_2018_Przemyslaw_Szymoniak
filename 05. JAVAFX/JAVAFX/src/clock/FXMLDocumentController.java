/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 *
 * @author Witajcie
 */
public class FXMLDocumentController implements Initializable {
    
    private GraphicsContext gc ;
    
    @FXML    private Canvas canvasBox;
    
    @FXML    private TableView<Alarms> alarmTable; 
    @FXML    private TableColumn<Alarms, String> NameColumn;
    @FXML    private TableColumn<Alarms, Integer> hourColumn;
    @FXML    private TableColumn<Alarms, Integer> minuteColumn;
 
    
     private  ObservableList<Alarms> listOfAlarmses = FXCollections.observableArrayList();
    

    
    @FXML
    private void handleCloseEvent(ActionEvent event) {
           Platform.exit();
    }
    @FXML
    private void handleCleanTable(ActionEvent event) {
           listOfAlarmses.clear();
    }
    @FXML
    private void handleRemoveIten(ActionEvent event) {
         Alarms selectedItem = alarmTable.getSelectionModel().getSelectedItem();
        alarmTable.getItems().remove(selectedItem);
        listOfAlarmses.remove(selectedItem);
          
    }

    
    @FXML
    private void handleAddAlarm(ActionEvent event){
            AddAlarm addAlarm = new AddAlarm();
            Alarms.setAlarm(listOfAlarmses);
            Stage bufor = new Stage();
            try {
                addAlarm.start(bufor);
             } catch (Exception ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
             }             
    }



    @FXML
    private void handleAboutAuthor(){
        System.out.println("CLICKE AUTHOR");
        aboutAuthor oAuthor = new aboutAuthor();
        Stage bufor = new Stage();
        try {
            oAuthor.start(bufor);
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //alarmTable.setItems(listOfAlarmses);
        
        NameColumn.setCellValueFactory(new PropertyValueFactory<Alarms, String>("names"));
        System.out.println(NameColumn);
        hourColumn.setCellValueFactory(new PropertyValueFactory<Alarms, Integer>("hours"));
        minuteColumn.setCellValueFactory(new PropertyValueFactory<Alarms, Integer>("minutes"));
        
        gc = canvasBox.getGraphicsContext2D();     
        timer.start();

    }   

    
    AnimationTimer timer = new AnimationTimer(){
        
        private double delayTime;
        @Override
        public void handle(long y){
            alarmTable.setItems(listOfAlarmses);
            double time = (double)System.currentTimeMillis();
            time+=(3600000*2);
            if(time > delayTime){
                for (int i = 0; i < listOfAlarmses.size(); i++) {
                    //System.out.println(listOfAlarmses.get(i));
                    (listOfAlarmses.get(i)).checkAlarm(time);
                }
                
                gc.clearRect(0, 0, canvasBox.getWidth(), canvasBox.getHeight());
                gc.beginPath();
                gc.closePath();
                drawClock();
                calcSecond(time); 
                calcMinutes(time);
                calcHours(time);
                delayTime = time+1000.0;
            }
           }

        
    };
    
    private void drawClock(){
        //OVAL
        gc.setLineWidth(1);  
        gc.setStroke(Color.BLACK);       
        gc.strokeOval(0,0,379,379);
        gc.setFill(Color.LIGHTGREY);
        gc.fillOval(0,0,379,379);
        gc.setStroke(Color.RED); 
        gc.fillOval(20,20,340,340);
        gc.strokeOval(20,20,340,340);
        gc.strokeOval(188,188,4,4);
        
        //TIME LINE
        gc.setStroke(Color.BLACK);
        gc.strokeLine(0, 190, 20, 190);
        gc.strokeLine(360, 190, 380, 190);
        gc.strokeLine(190, 0, 190, 20);
        gc.strokeLine(190, 360, 190, 380);
        gc.stroke();
    }
    
    private void calcSecond(double time) {
        time = (double) (time%60000/60000.0*Math.PI*2);
        double center = canvasBox.getWidth()/2;
        double[] seconds = {center+(double)(Math.sin(time)*185), center-(double)(Math.cos(time)*185)};
        
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(3);
        gc.strokeLine(190, 190, seconds[0], seconds[1]);
        gc.stroke();
      }

    private void calcMinutes(double time) {
        time = (double) (time%3600000/3600000.0*Math.PI*2);
        double center = canvasBox.getWidth()/2;
        double[] minutes = {center+(double)(Math.sin(time)*160), center-(double)(Math.cos(time)*160)};
        
        gc.setStroke(Color.RED);
        gc.setLineWidth(5);
        gc.moveTo(190, 190);
        gc.strokeLine(190, 190, minutes[0], minutes[1]);
        gc.stroke();
      }
    private void calcHours(double time) {
        time = (double) (time%43200000/43200000.0*Math.PI*2);
        double center = canvasBox.getWidth()/2;
        double[] hours = {center+(double)(Math.sin(time)*140), center-(double)(Math.cos(time)*140)};
        
        gc.setStroke(Color.RED);
        gc.setLineWidth(5);
        gc.strokeLine(190, 190, hours[0], hours[1]);
        gc.stroke();
      }
    
}
