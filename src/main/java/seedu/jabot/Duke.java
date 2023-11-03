package seedu.jabot;
import seedu.jabot.data.*;
import seedu.jabot.util.*;
import seedu.jabot.exceptions.DukeException;

/**
 * Represent the initialisation of jabot
 * A <code>Duke</code> make up of the file path to load the saved
 * task list. e.g. <code>C:/duke</code>
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser Parser;
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Executing the jabot using the class and methods created.
     */
    public void run() {
        ui.welcomeScreen();
        Parser = new Parser();
        storage = new Storage("C:/Users/leej32/Documents/duke/src/main/java/seedu/jabot/tasks.txt");
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * To invoke the run method
     */
    public static void main(String[] args) {
        new Duke("C:/Users/leej32/Documents/duke/src/main/java/seedu/jabot/tasks.txt").run();
    }
}

