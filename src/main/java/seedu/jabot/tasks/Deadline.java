package seedu.jabot.tasks;
import seedu.jabot.exceptions.DukeException;

/**
 * Represent a task which is a deadline. A <code>Deadline</code> object is represented
 * by the description of the task and the date to complete the task. e.g. <code>deadline, today</code>
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
    }

    /**
     * Formatting of additional information on the deadline to be saved into a file.
     */
    public String toSave(){
        return "D | " + super.toSave() + " | " + by;
    }

    /**
     * Formatting to show a deadline task in a list.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
