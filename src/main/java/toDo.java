public class toDo extends Task {
    protected boolean tDo;
    public toDo(String description){
        super(description);
    }

    public String toString(){
        return "[T]" + super.toString();
    }
}
