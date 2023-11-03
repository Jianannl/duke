package seedu.jabot.data;
import seedu.jabot.exceptions.DukeException;
import seedu.jabot.util.*;
import java.util.ArrayList;

/**
 * Represent the input commands keyed by users
 * which was processed by the parser. A <code>Command</code> make up of
 * the specifc commands and the data needed to do the specific operation.
 * e.g. <code>todo, running</code>
 */
public class Command {
    protected String tComd;
    protected int tInd;
    protected ArrayList<String> tTmp = new ArrayList<>();
    protected boolean iExit = false;

    public Command(String cmd){
        this.tComd = cmd;
    }
    public Command(String cmd, int ind){
        this.tComd = cmd;
        this.tInd = ind;
    }
    public Command(String cmd, String sTask){
        this.tComd = cmd;
        this.tTmp.add(sTask);
    }
    public Command(String cmd, String sTask, String sFrom){
        this.tComd = cmd;
        this.tTmp.add(sTask);
        this.tTmp.add(sFrom);
    }
    public Command(String cmd, String sTask, String sFrom, String sTo){
        this.tComd = cmd;
        this.tTmp.add(sTask);
        this.tTmp.add(sFrom);
        this.tTmp.add(sTo);
    }

    /**
     * Executing different commands input into jabot.
     *
     * @param tsk list of tasks added.
     * @param ui User interface of jabot.
     * @param sre to save/load of task list.
     * @return nothing. It is to execute the different command methods for the tasks.
     * @throws IllegalArgumentException If zone is <= 0.
     */
    public void execute(TaskList tsk, Ui ui, Storage sre) throws DukeException{
            switch(this.tComd){
                case "list":
                    tsk.showList();
                    break;
                case "bye":
                    this.iExit = true;
                    ui.sayBye();
                    break;
                case "delete":
                    tsk.delTask(tInd);
                    tsk.showNumTask();
                    break;
                case "mark":
                    tsk.markTask(tInd);
                    tsk.showNumTask();
                    break;
                case "unmark":
                    tsk.unmarkTask(tInd);
                    tsk.showNumTask();
                    break;
                case "save":
                    sre.saveFile(tsk.tList);
                    ui.saveMsg();
                    break;
                default:
                    tsk.addTask(tComd, tTmp);
                    tsk.showNumTask();
                    break;
            }
    }

    public boolean isExit(){
        return this.iExit;
    }
}
