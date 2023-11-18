package seedu.jabot.data;
import seedu.jabot.tasks.*;
import seedu.jabot.exceptions.DukeException;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 * Represent the list where the tasks need to be added into.
 */
public class TaskList {
    protected ArrayList<Task> tempList;
    protected static int maxTaskNo = 0;
    public TaskList(){
        this.tempList = new ArrayList<>();
    }

    public TaskList(ArrayList<String> tL) throws DukeException{
        toDo tmptoDo;
        Deadline tmpDeadline;
        Event tmpEvent;
        ArrayList<String> tdWords = new ArrayList<>();
        tempList = new ArrayList<>();
        for(String tLine : tL) {
            int i = tLine.indexOf("|");
            for (int j = tLine.indexOf("|", i+1); i != -1; j=tLine.indexOf("|", i+1)) {
                if(j == -1){
                    if(tLine.contains("-")){
                        tdWords.add(tLine.substring(i+2, tLine.indexOf("-")).trim());
                        tdWords.add(tLine.substring(tLine.indexOf("-")+1, tLine.indexOf("Status")).trim());
                    } else if(!tLine.contains("Status")) {
                        tdWords.add(tLine.substring(i+2));
                    } else {
                        tdWords.add(tLine.substring(i+2, tLine.indexOf("Status")).trim());
                    }
                } else {
                    tdWords.add(tLine.substring(i+2, j).trim());
                }
                i = j;
            }

            switch (tdWords.get(0)){
                case "0":
                    if (tLine.startsWith("T")) {
                        tmptoDo = new toDo(tdWords.get(1));
                        tmptoDo.markNotDone();
                        tempList.add(tmptoDo);
                    } else if (tLine.startsWith("D")) {
                        tmpDeadline = new Deadline(tdWords.get(1));
                        tmpDeadline.setBy(tdWords.get(2));
                        tmpDeadline.markNotDone();
                        tmpDeadline.isDue();
                        tempList.add(tmpDeadline);
                    } else {
                        tmpEvent = new Event(tdWords.get(1));
                        tmpEvent.setFrom(tdWords.get(2));
                        tmpEvent.setTo(tdWords.get(3));
                        tmpEvent.markNotDone();
                        tmpEvent.isDue();
                        tempList.add(tmpEvent);
                    }
                    break;
                case "1":
                    if (tLine.startsWith("T")) {
                        tmptoDo = new toDo(tdWords.get(1));
                        tmptoDo.markAsDone();
                        tempList.add(tmptoDo);
                    } else if (tLine.startsWith("D")) {
                        tmpDeadline = new Deadline(tdWords.get(1));
                        tmpDeadline.setBy(tdWords.get(2));
                        tmpDeadline.markAsDone();
                        tmpDeadline.isDue();
                        tempList.add(tmpDeadline);
                    } else {
                        tmpEvent = new Event(tdWords.get(1));
                        tmpEvent.setFrom(tdWords.get(2));
                        tmpEvent.setTo(tdWords.get(3));
                        tmpEvent.markAsDone();
                        tmpEvent.isDue();
                        tempList.add(tmpEvent);
                    }
                    break;
            }
            maxTaskNo++;
            tdWords.clear();
        }
    }

    /**
     * To add different tasks into the task list.
     *
     * @param cmd command input by user.
     * @param taskData Array list of parameters needed for the task.
     * @throws DukeException when underlying method throws the same Exception.
     */
    public void addTask(String cmd, ArrayList<String> taskData) throws DukeException{
        switch(cmd) {
            case "todo":
                this.tempList.add(new toDo(taskData.get(0)));
                break;
            case "deadline":
                Deadline tDeadline = new Deadline(taskData.get(0));
                tDeadline.setBy(taskData.get(1));
                tDeadline.isDue();
                this.tempList.add(tDeadline);
                break;
            case "event":
                Event tEvent = new Event(taskData.get(0));
                tEvent.setFrom(taskData.get(1));
                tEvent.setTo(taskData.get(2));
                tEvent.isDue();
                tempList.add(tEvent);
                break;
        }
        System.out.println("Got it. I've added this task: " + System.lineSeparator() + this.tempList.get(maxTaskNo) + System.lineSeparator());
        maxTaskNo++;
    }

    /**
     * To delete tasks into the task list.
     *
     * @param index the task no shown in the list displayed.
     */
    public void deleteTask(int index) throws DukeException{
        try{
            if (this.tempList.isEmpty()){
                throw new DukeException("No task in the list!");
            } else {
                this.tempList.remove(index);
                maxTaskNo--;
            }
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("Task number does not exist! Please try again!" + System.lineSeparator());
        }

    }

    /**
     * To mark tasks into the task list.
     *
     * @param index the task no shown in the list displayed.
     */
    public void markTask(int index) throws DukeException{
        boolean aDeadline = this.tempList.get(index) instanceof Deadline;
        boolean anEvent = this.tempList.get(index) instanceof Event;
        try {
            if (this.tempList.isEmpty()){
                throw new DukeException("No task in the list!");
            } else {
                this.tempList.get(index).markAsDone();
                if (aDeadline){
                    ((Deadline) this.tempList.get(index)).isDue();
                }
                if(anEvent){
                    ((Event) this.tempList.get(index)).isDue();
                }
                System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() + this.tempList.get(index) + System.lineSeparator());
            }

        } catch (IndexOutOfBoundsException e){
            throw new DukeException("Task number does not exist! Please try again!" + System.lineSeparator());
        }
    }

    /**
     * To unmark tasks into the task list.
     *
     * @param index the task no shown in the list displayed.
     */
    public void unmarkTask(int index) throws DukeException{
        boolean aDeadline = this.tempList.get(index) instanceof Deadline;
        boolean anEvent = this.tempList.get(index) instanceof Event;
        try {
            if (this.tempList.isEmpty()){
                throw new DukeException("No task in the list!");
            } else {
                this.tempList.get(index).markNotDone();
                if (aDeadline){
                    ((Deadline) this.tempList.get(index)).isDue();
                }
                if(anEvent){
                    ((Event) this.tempList.get(index)).isDue();
                }
                System.out.println("OK, I've unmarked this task as not done yet:" + System.lineSeparator() + this.tempList.get(index) + System.lineSeparator());
            }
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("Task number does not exist! Please try again!" + System.lineSeparator());
        }



    }

    /**
     * To display tasks added into task list as a list.
     *
     * @throws DukeException throw exception if the list is empty.
     */
    public void showList() throws DukeException {
        if (this.tempList.isEmpty()) {
            throw new DukeException("No task in the list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            int i = 1;
            for (Task task : this.tempList) {
                System.out.println(i + "." + task);
                i++;
            }
        }
    }
    public void findTask(Object taskFind){
        int lNo = 1;
        System.out.println("List of Tasks found with this specified statement:");
        if(taskFind instanceof String){
            String checkW = (String) taskFind;
            for(Task checkT : this.tempList){
                if(checkT instanceof toDo && ((toDo) checkT).checkDescription(checkW)){
                    System.out.println(lNo + "." + checkT);
                } else if(checkT instanceof Deadline && ((Deadline) checkT).checkDescription(checkW)){
                    System.out.println(lNo + "." + checkT);
                } else if(checkT instanceof Event && ((Event) checkT).checkDescription(checkW)){
                    System.out.println(lNo + "." + checkT);
                }
                lNo++;
            }
        } else {
            LocalDate checkD = (LocalDate) taskFind;
            for(Task checkT : this.tempList){
                if(checkT instanceof Deadline && checkT.checkDate(((Deadline) checkT).getBy(), checkD)){
                    System.out.println(lNo + "." + checkT);
                } else if(checkT instanceof Event){
                    boolean cFrom = checkT.checkDate(((Event) checkT).getEventFrom(), checkD);
                    boolean cTo = checkT.checkDate(((Event) checkT).getEventTo(), checkD);
                    if(cFrom || cTo){
                        System.out.println(lNo + "." + checkT);
                    }
                }
                lNo++;
            }

        }
    }
    public void dueTasks(){
        int dueTaskNum = 0;
        for (Task task : this.tempList){
            if (task instanceof Deadline && ((Deadline) task).isDue()){
                dueTaskNum++;
            } else if (task instanceof Event && ((Event) task).isDue()){
                dueTaskNum++;
            }
        }
        if(dueTaskNum > 0) {
            System.out.println("There are " + dueTaskNum + " tasks that is due.");
            System.out.println("please use snooze/reschedule/postpone along with task number according to the list to set another date");
        }
    }
    public void snoozeTask(int index) throws DukeException {
       boolean ifDeadline = tempList.get(index) instanceof Deadline;
       boolean ifEvent = tempList.get(index) instanceof Event;
       if(ifDeadline){
          LocalDateTime datetimeBy = (LocalDateTime) ((Deadline) tempList.get(index)).getBy();
          ((Deadline) tempList.get(index)).setBy(datetimeBy.plusDays(7));
          System.out.println("Deadline snoozed for 7 days!");
       } else if (ifEvent){
           LocalDateTime datetimeFrom = (LocalDateTime) ((Event) tempList.get(index)).getEventFrom();
           LocalDateTime datetimeTo = (LocalDateTime) ((Event) tempList.get(index)).getEventTo();
           ((Event) tempList.get(index)).setFrom(datetimeFrom.plusDays(7));
           ((Event) tempList.get(index)).setTo(datetimeTo.plusDays(7));
           System.out.println("Event snoozed for 7 days!");
       }
    }
    public void postponeTask(int index){
        boolean ifDeadline = tempList.get(index) instanceof Deadline;
        boolean ifEvent = tempList.get(index) instanceof Event;
        if(ifDeadline){
            ((Deadline) tempList.get(index)).postponeDeadline();
        } else if(ifEvent){
            ((Event) tempList.get(index)).postponeEvent();
        }
    }
    public void rescheduleTask(int index) throws DukeException{
        boolean isDeadline = tempList.get(index) instanceof Deadline;
        boolean isEvent = tempList.get(index) instanceof Event;
        if(isDeadline){
            ((Deadline) tempList.get(index)).rescheduleDeadline();
        } else if(isEvent){
            ((Event) tempList.get(index)).rescheduleEvent();
        }
    }
    /**
     * To print max no of tasks in the list when doing specific input commands.
     *
     */
    public void showNumTask(){
        System.out.println("Now you have " + maxTaskNo + " tasks in the list." + System.lineSeparator());
    }

}
