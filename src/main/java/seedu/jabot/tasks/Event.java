package seedu.jabot.tasks;

/**
 * Represent a task which is an event. An <code>Event</code> object is represented
 * by the description of the task and the date period of the event. e.g. <code>event, today, tomorrow</code>
 */
public class Event extends Task{

    protected String eFrom;
    protected String eTo;
    public Event(String description, String from, String to){
        super(description);
        this.eFrom = from;
        this.eTo = to;

    }

    /**
     * Formatting of additional information on the event to be saved into a file.
     */
    public String toSave(){
        return "E | " + super.toSave() + " | " + this.eFrom + " - " + this.eTo;
    }

    /**
     * Formatting to show an event task in a list.
     */
    public String toString(){
        return "[E]" + super.toString() + " (from: " + this.eFrom + " to: " + this.eTo + ")";
    }
}
