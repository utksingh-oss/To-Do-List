package sample;

public class Tasks {
    private String task="";
    private String taskDescription="";
    private String dueDate="";

    public void setTask(String task) { this.task = task; }

    public void setTaskDescription(String taskDescription) { this.taskDescription = taskDescription; }

    public void setDueDate(String dueDate) {this.dueDate = dueDate; }

    public String getTask(){ return this.task;}

    public String getTaskDescription() {return this.taskDescription;}

    public String getDueDate(){return this.dueDate;}

    @Override
    public String toString() {
        return task;
    }
}
