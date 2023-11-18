package seedu.jabot.tasks;

/**
 * Represent a task which needs to be done. A <code>toDo</code> object is represented
 * by the description of the task. e.g. <code>cleaning</code>
 */
public class toDo extends Task {
    public toDo(String description){
        super(description);
    }
    public boolean checkDescription(String word){
        return this.description.contains(word);
    }
    /**
     * Formatting of additional information on the toDos to be saved into a file.
     */
    public String toSave(){
        return "T | " + super.toSave();
    }

    /**
     * Formatting to show the todo task in a list.
     */
    public String toString(){
        return "[T]" + super.toString();
    }
}
