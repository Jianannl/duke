package seedu.jabot.util;
import seedu.jabot.data.Command;
import seedu.jabot.exceptions.DukeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                    this.c = new Command("mark", tNum);
            } else if (sLine.trim().toLowerCase().startsWith("unmark")) {
                    int tNum = Integer.parseInt(sLine.substring(sLine.indexOf("unmark") + 7));
                    this.c = new Command("unmark", tNum);

            } else if (sLine.trim().toLowerCase().startsWith("todo")) {
                    String tdTask = sLine.substring(sLine.indexOf("todo") + 5).trim();
                    if (tdTask.isBlank()) {
                        throw new IndexOutOfBoundsException();
                    }
                    this.c = new Command("todo", tdTask);
            } else if (sLine.trim().toLowerCase().startsWith("deadline")) {
                    String dTask = sLine.substring(sLine.indexOf("deadline") + 9, sLine.indexOf("/by")).trim();
                    String dDate = sLine.substring(sLine.indexOf("/by") + 4).trim();
                    if (dTask.isBlank()) {
                        throw new IndexOutOfBoundsException();
                    }
                    this.c = new Command("deadline", dTask, checkForDate(dDate));
            } else if (sLine.trim().toLowerCase().startsWith("event")) {
                    String etTask = sLine.substring(sLine.indexOf("event") + 6, sLine.indexOf("/from")).trim();
                    String etFrom = sLine.substring(sLine.indexOf("/from") + 6, sLine.indexOf("/to")).trim();
                    String etTo = sLine.substring(sLine.indexOf("/to") + 4).trim();
                    this.c = new Command("event", etTask, checkForDate(etFrom), checkForDate(etTo));

            } else if (sLine.trim().toLowerCase().startsWith("delete")) {
                    int tNum = Integer.parseInt(sLine.substring(sLine.indexOf("delete") + 7));
                    this.c = new Command("delete", tNum);
            } else {
                    throw new DukeException("Pardon? I do not understand your input. Please try again" + System.lineSeparator());
            }
            return this.c;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Oh No! No or invalid Command specified." + System.lineSeparator());
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid Input! please key in a number." + System.lineSeparator());
        }

    }

    /**
     * Format date inputs into another date-time format.
     *
     * @param aDate inputs keyed by the users.
     * @return date-time in dd MM yyyy HH:mm AM/PM format
     * @throws DukeException if input date is not in a valid format.
     */
    public String checkForDate(String aDate) throws DukeException {
        DateTimeFormatter dtFormatter;
        String dateTM = "(\\d{2}[-/\\s]?){2}\\d{4} \\d{2}[:\\s]?\\d{2}";
        Pattern dTPatt = Pattern.compile(dateTM);
        Matcher dTMatch = dTPatt.matcher(aDate);
        try {
            if (dTMatch.matches()) {
                if(aDate.indexOf(':') == -1){
                    switch (aDate.charAt(2)) {
                        case '/':
                            dtFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                            break;
                        case '-':
                            dtFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                            break;
                        default :
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
                        default:
                            dtFormatter = DateTimeFormatter.ofPattern("ddMMyyyy HH:mm");
                            break;
                    }
                }
                LocalDateTime dDT = LocalDateTime.parse(aDate, dtFormatter);
                return dDT.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mma"));
            } else {
                return aDate;
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date-time format! Please input date-time in the format of dd/MM/yyyy HH:mm(24hr format)");
        }
    }
}
