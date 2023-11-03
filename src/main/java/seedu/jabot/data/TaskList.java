package seedu.jabot.data;
import seedu.jabot.tasks.*;
import seedu.jabot.exceptions.DukeException;
import java.util.ArrayList;
/**
 * Represent the list where the tasks need to be added into.
 */
public class TaskList {
    protected ArrayList<Task> tList;
    protected static int maxTno = 0;

    public TaskList(){
        this.tList = new ArrayList<>();
    }

    public TaskList(ArrayList<String> tL) throws DukeException{
        Task tmpT;
        ArrayList<String> tdWords = new ArrayList<>();
        tList = new ArrayList<>();
        for(String tLine : tL) {
            int i = tLine.indexOf("|");
            for (int j = tLine.indexOf("|", i+1); i != -1; j=tLine.indexOf("|", i+1)) {
                if(j == -1){
                    if(tLine.contains("-")){
                        tdWords.add(tLine.substring(i+1, tLine.indexOf("-")).trim());
                        tdWords.add(tLine.substring(tLine.indexOf("-")+1).trim());
                    } else {
                        tdWords.add(tLine.substring(i+1).trim());
                    }
                } else {
                    tdWords.add(tLine.substring(i+1, j).trim());
                }
                i = j;
            }
            if (tLine.startsWith("T")) {
                tmpT = new toDo(tdWords.get(1));
            } else if (tLine.startsWith("D")) {
                tmpT = new Deadline(tdWords.get(1), tdWords.get(2));
            } else {
                tmpT = new Event(tdWords.get(1), tdWords.get(2), tdWords.get(3));
            }
            switch (tdWords.get(0)){
                case "0":
                    tmpT.markNotDone();
                    break;
                case "1":
                    tmpT.markAsDone();
            }
            tList.add(tmpT);
            maxTno++;
            tdWords.clear();
        }
    }

    /**
     * To add different tasks into the tasklist.
     *
     * @param cmd command input by user.
     * @param a Array list of parameters needed for the task.
     * @throws DukeException when underlying method throws the same Exception.
     */
    public void addTask(String cmd, ArrayList<String> a) throws DukeException{
        switch(cmd) {
            case "todo":
                this.tList.add(new toDo(a.get(0)));
                break;
            case "deadline":
                this.tList.add(new Deadline(a.get(0), a.get(1)));
                break;
            case "event":
                this.tList.add(new Event(a.get(0), a.get(1), a.get(2)));
                break;
        }
        System.out.println("Got it. I've added this task: " + System.lineSeparator() + this.tList.get(maxTno));
        maxTno++;
    }

    /**
     * To delete tasks into the tasklist.
     *
     * @param ind the task no shown in the list displayed.
     */
    public void delTask(int ind){
        this.tList.remove(ind);
        maxTno--;
    }

    /**
     * To mark tasks into the tasklist.
     *
     * @param ind the task no shown in the list displayed.
     */
    public void markTask(int ind) throws DukeException{
        if (this.tList.isEmpty()){
            throw new DukeException("No task in the list!");
        } else {
            this.tList.get(ind - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() + this.tList.get(ind - 1) + System.lineSeparator());
        }
    }

    /**
     * To unmark tasks into the tasklist.
     *
     * @param ind the task no shown in the list displayed.
     */
    public void unmarkTask(int ind) throws DukeException{
        if (this.tList.isEmpty()){
            throw new DukeException("No task in the list!");
        } else {
            this.tList.get(ind - 1).markNotDone();
            System.out.println("OK, I've unmarked this task as not done yet:" + System.lineSeparator() + this.tList.get(ind - 1) + System.lineSeparator());
        }
    }

    /**
     * To display tasks added into tasklist as a list.
     *
     * @throws DukeException throw exception if the list is empty.
     */
    public void showList() throws DukeException {
        if (this.tList.isEmpty()) {
            throw new DukeException("No task in the list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            int i = 1;
            for (Task task : this.tList) {
                System.out.println(i + "." + task);
                i++;
            }
        }
    }

    /**
     * To print max no of tasks in the list when doing specific input commands.
     *
     */
    public void showNumTask(){
        System.out.println("Now you have " + maxTno + " tasks in the list.");
    }

}
