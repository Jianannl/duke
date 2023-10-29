public class Event extends Task{

    protected String eFrom;
    protected String eTo;
    public Event(String description, String from, String to){
        super(description);
        this.eFrom = from;
        this.eTo = to;
    }
    public String toString(){
        return "[E]" + super.toString() + "(from: " + this.eFrom + "to: " + this.eTo + ")";
    }
}
