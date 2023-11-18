package seedu.jabot.data;
import seedu.jabot.exceptions.DukeException;
import seedu.jabot.util.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represent the input commands keyed by users
 * which will be processed by the parser. A <code>Command</code> make up of
 * the specific commands and the data needed to do the specific operation.
 * e.g. <code>todo, running</code>
 */
public class Command {
    protected String tCommand;
    protected String tValue = null;
    protected LocalDate tDate = null;
    protected int tIndex;
    protected ArrayList<String> tTmp = new ArrayList<>();
    protected boolean iExit = false;
    public Command(String cmd){
        this.tCommand = cmd;
    }
    public Command(String cmd, int index){
        this.tCommand = cmd;
        this.tIndex = index;
    }
    public Command(String cmd, String sTask){
        this.tCommand = cmd;
        if(cmd.equals("find")){
            this.tValue = sTask;
        } else {
            this.tTmp.add(sTask);
        }
    }
    public Command(String cmd, LocalDate sTask){
        this.tCommand = cmd;
        this.tDate = sTask;
    }
    public Command(String cmd, String sTask, String sFrom){
        this.tCommand = cmd;
        this.tTmp.add(sTask);
        this.tTmp.add(sFrom);
    }
    public Command(String cmd, String sTask, String sFrom, String sTo){
        this.tCommand = cmd;
        this.tTmp.add(sTask);
        this.tTmp.add(sFrom);
        this.tTmp.add(sTo);
    }

    /**
     * Executing different commands that are input into jabot.
     *
     * @param tsk list of tasks added.
     * @param ui User interface of jabot.
     * @param sre to save/load of task list.
     * @throws DukeException If sub-methods in task list throw exceptions
     */
    public void execute(TaskList tsk, Ui ui, Storage sre) throws DukeException{
            switch(this.tCommand){
                case "list":
                    tsk.showList();
                    tsk.dueTasks();
                    break;
                case "find":
                    if(tValue != null){
                        tsk.findTask(tValue);
                    } else {
                        tsk.findTask(tDate);
                    }
                    tsk.dueTasks();
                    break;
                case "bye":
                    this.iExit = true;
                    ui.sayBye();
                    break;
                case "delete":
                    tsk.deleteTask(this.tIndex);
                    tsk.showNumTask();
                    tsk.dueTasks();
                    break;
                case "mark":
                    tsk.markTask(this.tIndex);
                    tsk.showNumTask();
                    tsk.dueTasks();
                    break;
                case "unmark":
                    tsk.unmarkTask(this.tIndex);
                    tsk.showNumTask();
                    tsk.dueTasks();
                    break;
                case "save":
                    sre.saveFile(tsk.tempList);
                    ui.saveMsg();
                    break;
                case "snooze":
                    tsk.snoozeTask(this.tIndex);
                    tsk.dueTasks();
                    break;
                case "reschedule":
                    tsk.rescheduleTask(this.tIndex);
                    tsk.dueTasks();
                    break;
                case "postpone":
                    tsk.postponeTask(this.tIndex);
                    tsk.dueTasks();
                    break;
                default:
                    //assert that tTmp size should be more than or equal to 2,
                    //if not addTask will not be executed correctly
                    assert tTmp.size() >=2;
                    tsk.addTask(tCommand, tTmp);
                    tsk.showNumTask();
                    tsk.dueTasks();
                    break;
            }
    }

    public boolean isExit(){
        return this.iExit;
    }
}
