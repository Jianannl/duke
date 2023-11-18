package seedu.jabot.tasks;
import seedu.jabot.exceptions.DukeException;
import seedu.jabot.util.Ui;
import java.time.LocalDateTime;

/**
 * Represent a task which is a deadline. A <code>Deadline</code> object is represented
 * by the description of the task and the date to complete the task. e.g. <code>deadline, today</code>
 */
public class Deadline extends Task {

    protected Object by;
    protected String due = "NA";
    private final Ui ui = new Ui();
    public Deadline(String description) {
        super(description);
    }
    public void setBy(Object deadlineDate) throws DukeException {
        if(deadlineDate instanceof LocalDateTime) {
            this.by = deadlineDate;
        } else {
            this.by = setDate((String) deadlineDate);
        }
    }
    public Object getBy() {
        return this.by;
    }
    public boolean checkDescription(String word){
        return this.description.contains(word);
    }
    public void postponeDeadline(){
        this.due = "POSTPONED";
    }
    public void rescheduleDeadline() throws DukeException{
        System.out.println("Please set Deadline: ");
        String rescheduleBy = ui.readCommand();
        this.by = setDate(rescheduleBy);
        System.out.println("Deadline postponed to " + formatToString(this.by));
    }
    public boolean isDue(){
        LocalDateTime timeNow = LocalDateTime.now();
        boolean dateTime = this.by instanceof LocalDateTime;
        boolean done = this.isDone;
        if(this.due.equals("POSTPONED")){
            return false;
        } else if(dateTime && !done){
            if(((LocalDateTime) this.by).isBefore(timeNow)){
                this.due = "DUE";
                return true;
            } else {
                this.due = "NOT DUE";
            }
        }
        return false;
    }
    /**
     * Formatting of additional information on the deadline to be saved into a file.
     */
    public String toSave() {
        String tmpString = formatToString(this.by);
        return "D | " + super.toSave() + " | " + tmpString + " Status: " + this.due;
    }
    /**
     * Formatting to show a deadline task in a list.
     */
    @Override
    public String toString() {
        String tString = formatToString(this.by);
        return "[D]" + super.toString() + " (by: " + tString + ")" + " Status: " + this.due;
    }
}
