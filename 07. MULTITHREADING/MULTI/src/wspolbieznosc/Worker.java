/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspolbieznosc;

import java.util.LinkedList;
import java.util.Queue;



/**
 *
 * @author Witajcie
 */
public class Worker {
    private WorkerListener WorkerListen;
    public Thread thread;
    Queue<Wkolejce> ListOfTask;
    private boolean working;
    

    public Worker(String workerName) {

        thread=new Thread("Worker"   +   workerName   +   "Thread"){
            @Override
            public void run() {
                
                int taskNumber=1;
                WorkerListen.onWorkerStarted();
                    System.out.println("name: "+thread.getName());
                    while(working){
                        
                        try {
                            if(ListOfTask.size() >0){
                                Wkolejce kolejka = ListOfTask.poll();
                               String nazwa = kolejka.getNameTask();
                                Task task = kolejka.getTask();
                                WorkerListen.onTaskStarted(taskNumber,nazwa);
                                task.run(taskNumber);
                                WorkerListen.onTaskCompleted(taskNumber, nazwa);
                                
                                taskNumber++;
                            }
                               
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                 
   
                WorkerListen.onWorkerStoped();
                working = false;
            }
        };
        ListOfTask = new LinkedList<>();
        working = false;
    }
    
    void enqueueTask(String taskName, Task task ){
       ListOfTask.offer(new Wkolejce(taskName, task));
    }
    
    public synchronized void start(){
        
            working = true;
            thread.start();
        
    }
   public synchronized void stop(){
       
            thread.interrupt();  
             working = false;
       
    }
    void setListener( WorkerListener Work){
       WorkerListen = Work;
    }

    boolean isStarted(){
        return thread.isAlive();        
    }

    boolean isWorking(){
        return  working;
    }

}
