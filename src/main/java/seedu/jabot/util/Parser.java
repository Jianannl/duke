package seedu.jabot.util;
import seedu.jabot.data.Command;
import seedu.jabot.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represent an underlying component to transform the inputs by the user to
 * useful data to run to operations. A <code>Parser</code> object is not represented by anything.
 * Mainly methods to transform the data.
 */
public class Parser {
    protected Command c;

    public Parser(){}

    /**
     * Transforming user inputs into key data for other methods.
     *
     * @param sLine inputs keyed by the users.
     * @return useful inputs split to perform various operations.
     * @throws DukeException if task number is invalid or when the input commands are incorrect.
     */
    public Command parse(String sLine) throws DukeException {
        try{
            if (sLine.trim().equalsIgnoreCase("list")) {
                this.c = new Command("list");
            } else if (sLine.trim().equalsIgnoreCase("bye")) {
                this.c = new Command("bye");
            } else if (sLine.trim().equalsIgnoreCase("save")){
                this.c = new Command("save");
            }
            else if (sLine.trim().toLowerCase().startsWith("mark")) {
                int tNum = Integer.parseInt(sLine.substring(sLine.indexOf("mark") + 5));
                this.c = new Command("mark", tNum - 1);
            } else if (sLine.trim().toLowerCase().startsWith("unmark")) {
                int tNum = Integer.parseInt(sLine.substring(sLine.indexOf("unmark") + 7));
                this.c = new Command("unmark", tNum- 1);
            } else if (sLine.trim().toLowerCase().startsWith("snooze")){
                int tNum = Integer.parseInt(sLine.substring(sLine.indexOf("snooze") + 7));
                this.c = new Command("snooze", tNum - 1);
            } else if (sLine.trim().toLowerCase().startsWith("reschedule")){
                int tNum = Integer.parseInt(sLine.substring(sLine.indexOf("reschedule") + 11));
                this.c = new Command("reschedule", tNum - 1);
            } else if (sLine.trim().toLowerCase().startsWith("postpone")){
                int tNum = Integer.parseInt(sLine.substring(sLine.indexOf("postpone") + 9));
                this.c = new Command("postpone", tNum- 1);
            } else if (sLine.trim().toLowerCase().startsWith("todo")) {
                String tdTask = sLine.substring(sLine.indexOf("todo") + 5).trim();
                if (tdTask.isBlank()) {
                    throw new DukeException("No todo specified! Please input a todo.");
                }
                this.c = new Command("todo", tdTask);
            } else if (sLine.trim().toLowerCase().startsWith("deadline")) {
                String dTask = sLine.substring(sLine.indexOf("deadline") + 9, sLine.indexOf("/by")).trim();
                String dDate = sLine.substring(sLine.indexOf("/by") + 4).trim();
                if (dTask.isBlank() || dDate.isBlank()) {
                    throw new DukeException("No deadline or date specified! Please try again.");
                }
                this.c = new Command("deadline", dTask, (dDate));
            } else if (sLine.trim().toLowerCase().startsWith("event")) {
                String etTask = sLine.substring(sLine.indexOf("event") + 6, sLine.indexOf("/from")).trim();
                String etFrom = sLine.substring(sLine.indexOf("/from") + 6, sLine.indexOf("/to")).trim();
                String etTo = sLine.substring(sLine.indexOf("/to") + 4).trim();
                if(etTask.isBlank() || etFrom.isBlank() || etTo.isBlank()){
                    throw new DukeException("No event or date specified! Please try again.");
                }
                this.c = new Command("event", etTask, (etFrom), (etTo));

            } else if (sLine.trim().toLowerCase().startsWith("delete")) {
                int tNum = Integer.parseInt(sLine.substring(sLine.indexOf("delete") + 7));
                this.c = new Command("delete", tNum-1);
            } else if (sLine.trim().toLowerCase().startsWith("find")){
                String fTask = sLine.substring(sLine.indexOf("find") + 4).trim();
                if(fTask.startsWith("/date")){
                    fTask = fTask.substring(fTask.indexOf("/date") + 5).trim();
                    LocalDate tmpD = convertDate(fTask);
                    this.c = new Command("find", tmpD);
                }
                else {
                    this.c = new Command("find", fTask);
                }
            } else {
                throw new DukeException("Pardon? I do not understand your input. Please try again" + System.lineSeparator());
            }
            return this.c;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid Input! please key in a number." + System.lineSeparator());
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("Invalid Input! Please key in a correct input.");
        }

    }

    public LocalDate convertDate(String aDate) throws DukeException {
        DateTimeFormatter dtFormatter;
        try {
            switch (aDate.charAt(2)) {
                case '/':
                    dtFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    break;
                case '-':
                    dtFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    break;
                default:
                    dtFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
                    break;
            }
            return LocalDate.parse(aDate, dtFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format! Please input date-time in the format of dd/MM/yyyy (24hr format)");
        }
    }
}
