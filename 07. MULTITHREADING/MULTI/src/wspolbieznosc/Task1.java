/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspolbieznosc;


public class Task1 implements Task {

    @Override
    public void run(int taskNumber) throws InterruptedException {
        long t1=System.currentTimeMillis();
        while(t1+100>System.currentTimeMillis())
        {
            if(Thread.interrupted())
                break;
        }
    }
    
}
