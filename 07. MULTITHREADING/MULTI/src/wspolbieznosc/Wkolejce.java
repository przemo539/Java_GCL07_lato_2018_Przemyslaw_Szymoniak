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
public class Wkolejce {
    private String nameTask;
    private Task task;

    public Wkolejce(String inputNameTask, Task inputTask){
        nameTask = inputNameTask;
        task = inputTask;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}

