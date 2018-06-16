/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.AudioClip;

/**
 *
 * @author Witajcie
 */
public class Alarms {
    static ObservableList<Alarms> listOfAlarmse;
    private String names;
    private int minutes;
    private int hours;

    public static ObservableList<Alarms> getAlarm() {
        return listOfAlarmse;
    }

    public static void setAlarm(ObservableList<Alarms> alarm) {
        Alarms.listOfAlarmse = alarm;
    }

    
    
    public String getNames() {
        return names;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHours() {
        return hours;
    }
    private int seconds;

    public Alarms(String names, int hours, int minutes) {
        //this.alarm = this;
        this.names = names;
        this.minutes = minutes;
        this.hours = hours;
        this.seconds = 0;
        System.out.println("dodano");
    }
    
    public Alarms() {
        names = "";
        this.minutes = -10;
        this.hours = -10;
    }
    
    public void addAlarms(String name, int hour, int minute){
     names = name;
     hours = hour;
     minutes = minute;
     seconds = 0;
    } 
    
    public void checkAlarm(double time){
        int hour = (int) ((time / (1000*60*60)) % 24);
        int minute = (int) ((time/(1000*60)) % 60);
        int second = (int) (time / 1000) % 60;
        if(hours == hour && minutes == minute && seconds == second){
            System.out.println("ALARM");
            notification();
           
        }
         //System.out.println("Nie dzwoni "+hours+" "+minutes+" "+seconds);
        
    }
    public void notification(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("ALARM");
        alert.setHeaderText(null);
        alert.setContentText("Nazwa alarmu: "+names);

        alert.show();
        final AudioClip sound = new AudioClip(Alarms.class.getResource("note.wav").toString());
        sound.play();

    }
}
