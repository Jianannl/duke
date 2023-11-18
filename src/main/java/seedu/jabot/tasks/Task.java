package seedu.jabot.tasks;

import seedu.jabot.exceptions.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represent a task. A <code>Task</code> object is represented
 * by the description of the task and whether it is done or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * To get the status of whether a task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * To mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * To mark a task as not done.
     */
    public void markNotDone(){
        this.isDone = false;
    }

    /**
     * To format the data of a task to be saved into a file.
     */
    public String toSave(){
        String SI;
        if(this.getStatusIcon().equals("X")){
            SI = "1";
        } else{
            SI = "0";
        }
        return SI + " | " + this.description;
    }
    public Object setDate(String aDate) throws DukeException {
        DateTimeFormatter dtFormatter;
        String dateTM = "\\d{2}[-/\\s]?(\\d{2}|[a-zA-Z]{3})[-/\\s]?\\d{4} \\d{2}[:\\s]?\\d{2}(AM|PM)?";
        Pattern dTpattern = Pattern.compile(dateTM);
        Matcher dTMatch = dTpattern.matcher(aDate);
        try {
            if (dTMatch.matches()) {
                if (aDate.indexOf(':') == -1) {
                    switch (aDate.charAt(2)) {
                        case '/':
                            dtFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                            break;
                        case '-':
                            dtFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                            break;
                        case ' ':
                            dtFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HHmma");
                            break;
                        default:
                            dtFormatter = DateTimeFormatter.ofPattern("ddMMyyyy HHmm");
                            break;
                    }
                } else {
                    switch (aDate.charAt(2)) {
                        case '/':
                            dtFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                            break;
                        case '-':
                            dtFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                            break;
                        case ' ':
                            dtFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mma");
                            break;
                        default:
                            dtFormatter = DateTimeFormatter.ofPattern("ddMMyyyy HH:mm");
                            break;
                    }
                }
                return LocalDateTime.parse(aDate, dtFormatter);
            } else {
                return aDate;
            }
        } catch (DateTimeParseException e){
            throw new DukeException("Invalid date-time format! Please input date-time in the format of dd/MM/yyyy HH:mm(24hr format)");
        }
    }
    public boolean checkDate(Object dateToCheck, LocalDate date){
        if(dateToCheck instanceof LocalDateTime){
            return ((LocalDateTime) dateToCheck).toLocalDate().isEqual(date);
        } else {
            return false;
        }
    }

    public String formatToString(Object taskD){
        LocalDateTime tDate;
        String tString = null;
        if(taskD instanceof LocalDateTime){
            tDate = (LocalDateTime) taskD;
            tString = tDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mma"));
        } else if(taskD instanceof String){
            tString = (String) taskD;
        }
        return tString;
    }
    /**
     * To change how the information of the task should be output in a list
     */
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

