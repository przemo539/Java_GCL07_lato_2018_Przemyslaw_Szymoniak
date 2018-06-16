/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspolbieznosc;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Witajcie
 */
public class Wspolbieznosc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
        System.out.println("Main thread started");
        System.out.println("Create new thread");
        Worker taskThread = new Worker("New");
        taskThread.setListener(new WorkerListener() {
            @Override
            public void onWorkerStarted() {
                System.out.println("Worker started");
            }
 
            @Override
            public void onWorkerStoped() {
                System.out.println("Worker stoped");
            }
 
            @Override
            public void onTaskStarted(int taskNumber, String taskName) {
                System.out.println("Task: " + taskNumber +" "+ taskName+" started");
            }
 
            @Override
            public void onTaskCompleted(int taskNumber, String taskName) {
                System.out.println("Task : " + taskNumber +" "+ taskName+" completed");
            }
        });
        
        try {
            taskThread.thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Wspolbieznosc.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        taskThread.enqueueTask("Task1()", new Task1());//25%
        taskThread.enqueueTask("Task2()", new Task2());//0%
        taskThread.enqueueTask("Task3()", new Task3());//25%
        taskThread.enqueueTask("Task4()", new Task4());//25%
        taskThread.enqueueTask("Task5()", new Task5());//25%
        taskThread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        taskThread.enqueueTask("Task6()", new Task1());//25%
        taskThread.enqueueTask("Task7()", new Task2());//0%
        taskThread.enqueueTask("Task8()", new Task3());//25%
        taskThread.enqueueTask("Task9()", new Task4());//25%
        taskThread.enqueueTask("Task10()", new Task5());//25%
        
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        taskThread.stop();
        System.out.println("Main thread stoped");
    }
    
}
