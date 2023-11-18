package seedu.jabot.tasks;
import seedu.jabot.util.Ui;
import seedu.jabot.exceptions.DukeException;
import java.time.LocalDateTime;

/**
 * Represent a task which is an event. An <code>Event</code> object is represented
 * by the description of the task and the date period of the event. e.g. <code>event, today, tomorrow</code>
 */
public class Event extends Task{
    protected Object eFrom;
    protected Object eTo;
    protected String due = "NA";
    private final Ui ui = new Ui();
    public Event(String description){
        super(description);
    }
    public void setFrom(Object from) throws DukeException {
        boolean dateFrom = from instanceof LocalDateTime;

        boolean stringFrom = from instanceof String;

        if(dateFrom){
            this.eFrom = from;

        } else if(stringFrom){

            this.eFrom = setDate((String) from);

        }
    }
    public void setTo(Object to) throws DukeException{
        boolean datetimeFrom = this.eFrom instanceof LocalDateTime;
        boolean dateTo = to instanceof LocalDateTime;
        boolean stringTo = to instanceof String;
        //Object to and eFrom should be in this three instance
        assert (datetimeFrom || dateTo || stringTo);
        if(dateTo){
            if(datetimeFrom && checkDatePeriod((LocalDateTime) this.eFrom, (LocalDateTime) to)){
                this.eTo = to;
            } else {
                throw new DukeException("To date should be after from date! Please try again.");
            }
        } else if (stringTo && setDate((String) to) instanceof LocalDateTime){
            if(datetimeFrom && checkDatePeriod((LocalDateTime) this.eFrom, (LocalDateTime) setDate((String) to))){
                this.eTo = setDate((String) to);
            } else {
                throw new DukeException("To date should be after from date! Please try again.");
            }
            this.eTo = setDate((String) to);
        }
    }
    public Object getEventFrom() {
        return eFrom;
    }

    public Object getEventTo() {
        return eTo;
    }

    public boolean checkDescription(String word){
        return this.description.contains(word);
    }

    public boolean isDue(){
        LocalDateTime timeNow = LocalDateTime.now();
        boolean dateTime = this.eFrom instanceof LocalDateTime;
        boolean done = this.isDone;
        if(this.due.equals("POSTPONED")){
            return false;
        } else if(dateTime && !done){
            if(((LocalDateTime) this.eFrom).isBefore(timeNow)){
                this.due = "DUE";
                return true;
            } else {
                this.due = "NOT DUE";
            }
        }
        return false;
    }
    public void postponeEvent(){
        this.due = "POSTPONED";
    }
    public void rescheduleEvent() throws DukeException{
        boolean checkFrom;
        boolean checkTo;
        System.out.println("Please set new Event from date: ");
        String rescheduleFrom = ui.readCommand();
        this.eFrom = setDate(rescheduleFrom);
        checkFrom = this.eFrom instanceof LocalDateTime;
        System.out.println("Please set new Event to date: ");
        String rescheduleTo = ui.readCommand();
        this.eTo = setDate(rescheduleTo);
        checkTo = this.eTo instanceof LocalDateTime;
        while((checkFrom && checkTo) && !(checkDatePeriod((LocalDateTime) this.eFrom, (LocalDateTime) this.eTo))){
            System.out.println("To date should be after from date! Please key again.");
            rescheduleTo = ui.readCommand();
            this.eTo = setDate(rescheduleTo);
        }
        System.out.println("Event has been rescheduled to " + formatToString(this.eFrom) + "-" + formatToString(this.eTo));
    }
    public boolean checkDatePeriod(LocalDateTime start, LocalDateTime end){
        return start.isBefore(end);
    }
    /**
     * Formatting of additional information on the event to be saved into a file.
     */
    public String toSave() {
        String stringFrom = formatToString(this.eFrom);
        String stringTo = formatToString(this.eTo);
        return "E | " + super.toSave() + " | " + stringFrom + " - " + stringTo + " Status: " + this.due;
    }

    /**
     * Formatting to show an event task in a list.
     */
    public String toString(){
        String strFrom = formatToString(this.eFrom);
        String strTo = formatToString(this.eTo);
        return "[E]" + super.toString() + " (from: " + strFrom + " to: " + strTo + ")" + " Status: " + this.due;
    }
}
