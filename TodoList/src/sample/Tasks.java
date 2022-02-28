package sample;

import java.util.Date;

public class Tasks {
    private String task;
    private String taskDescription;
    private String dueDate;

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getTask() {
        return task;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    @Override
    public String toString() {
        return task;
    }
}
