package org.cbillow.headfirst.command;

import java.util.Arrays;
import java.util.List;

/**
 * @author Created by Cbillow
 * @date 16/1/13
 * @time 15:12
 */
public class ProductManager {

    public static final int TASK_NUMBER_IN_DAY = 4;

    private List<Task> taskList;

    public ProductManager(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void receive(Task task) {
        taskList.add(task);
    }

    public void assign() {
        Task[] copy = new Task[taskList.size() > TASK_NUMBER_IN_DAY ? taskList.size() - TASK_NUMBER_IN_DAY : 0];
        for (int i = 0; i < TASK_NUMBER_IN_DAY && i < taskList.size(); i++) {
            taskList.get(i).handle();
        }
        System.arraycopy(taskList.toArray(), TASK_NUMBER_IN_DAY > taskList.size() ? taskList.size() : TASK_NUMBER_IN_DAY, copy, 0, copy.length);
        taskList = Arrays.asList(copy);
    }

    public void printTaskList() {
        if (taskList == null || taskList.size() == 0) {
            System.out.println("-----当前无任务-----");
            return;
        }
        System.out.println("-----当前剩下的任务列表-----");
        for (Task task : taskList) {
            System.out.println(task);
        }
        System.out.println("---------------------");

    }

}
