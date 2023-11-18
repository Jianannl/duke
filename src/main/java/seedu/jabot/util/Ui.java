package seedu.jabot.util;

import java.util.Scanner;
/**
 * Represent the user interface to show messages and read inputs
 */
public class Ui {
    protected String logo = " _____   _____   _____    _____   ______\n"
            + "|___  | /  _  \\ |  _  \\  /     \\ |_    _|\n"
            + " _  | | | |_| | |  _  / /  / \\  \\  |  |   \n"
            + "| |_| | |  _  | |  _  \\ \\  \\ /  /  |  |  \n"
            + "|____/  |_| |_| |_____/  \\_____/   |__|   \n";

    protected String dividerL = "________________________________________";
    protected Scanner in = new Scanner(System.in);
    protected String Line;
    public Ui(){}

    /**
     * To load the welcome screen.
     */
    public void welcomeScreen(){
        System.out.println(System.lineSeparator() + logo);
        System.out.println("Hello! I'm JABOT" + System.lineSeparator() + "What can I do for you?" + System.lineSeparator());
    }

    /**
     * To show a divider on every command and reply from jabot.
     */
    public void showLine(){
        System.out.println(dividerL + System.lineSeparator());
    }

    /**
     * To show error msg when jabot encounter problem.
     * @param errMsg the message related to the error
     */
    public void showError(String errMsg){
        System.out.println(errMsg);
    }
    public String readCommand(){
        this.Line = this.in.nextLine();
        return this.Line;
    }

    /**
     * To show message when exiting jabot.
     */
    public void sayBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * To show saving message when saving.
     */
    public void saveMsg(){
        System.out.println("Saved. You're good to go! (FYI, task.txt is in desktop folder~)");
    }
}
