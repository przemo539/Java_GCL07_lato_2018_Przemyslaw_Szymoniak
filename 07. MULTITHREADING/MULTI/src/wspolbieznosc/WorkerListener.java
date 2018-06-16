/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspolbieznosc;

/**
 *
 * @author Witajcie
 */
public interface WorkerListener {
    void onWorkerStarted();
    void onWorkerStoped();
    void onTaskStarted( int taskNumber, String taskName );
    void onTaskCompleted( int taskNumber, String taskName );
}
