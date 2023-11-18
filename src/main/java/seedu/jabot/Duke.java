package seedu.jabot;
import seedu.jabot.data.*;
import seedu.jabot.util.*;
import seedu.jabot.exceptions.DukeException;

import java.io.File;

/**
 * Represent the initialisation of jabot
 * A <code>Duke</code> make up of the file path to load the saved
 * task list. e.g. <code>C:/duke</code>
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private final Ui ui;
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
        Parser parser = new Parser();
        storage = new Storage(System.getProperty("user.home")+ File.separator + "Desktop/task.txt");
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = parser.parse(fullCommand);
                //assert that command c should not null, otherwise not operation will be executed
                assert c != null;
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
        new Duke(System.getProperty("user.home") + File.separator + "Desktop/task.txt").run();
    }
}

