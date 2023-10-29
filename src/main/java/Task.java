public class Task {
    protected String description;
    protected boolean isDone;

    private static int maxT = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markNotDone(){
        this.isDone = false;
    }

    public static int getMaxT(){
        return maxT;
    }
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

