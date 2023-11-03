package seedu.jabot.tasks;

/**
 * Represent a task. A <code>Task</code> object is represented
 * by the description of the task and whether it is done or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * To get the status of whether a task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * To mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * To mark a task as not done.
     */
    public void markNotDone(){
        this.isDone = false;
    }

    /**
     * To format the data of a task to be saved into a file.
     */
    public String toSave(){
        String SI;
        if(this.getStatusIcon().equals("X")){
            SI = "1";
        } else{
            SI = "0";
        }
        return SI + " | " + this.description;
    }

    /**
     * To change how the information of the task should be output in a list
     */
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

