package seedu.jabot.util;
import seedu.jabot.tasks.Task;
import seedu.jabot.exceptions.DukeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Represent the file path where the file with the list needs to be
 * saved/load from
 */
public class Storage {
    protected File sF;
    protected FileWriter wF;
    protected Scanner scanF;
    public Storage(String filePath){
        this.sF = new File(filePath);
    }

    /**
     * To save the list of task into a file at a file path.
     *
     * @param aT the list of tasks.
     * @throws DukeException when IOException is catch.
     */
    public void saveFile(ArrayList<Task> aT) throws DukeException {
        try {
            if(!this.sF.exists()){
                this.sF.createNewFile();
                System.out.println("File did not exist! A new file named Task.txt was created on the desktop.");
            }
            this.wF = new FileWriter(this.sF);
            for (Task task : aT) {
                String tFormat = task.toSave();
                this.wF.write(tFormat);
                this.wF.write(System.lineSeparator());
            }
            this.wF.flush();
            this.wF.close();
        } catch (IOException e){
            throw new DukeException("File error! Unable to save.");
        }
    }

    /**
     * To load the list of task that was saved to the file path.
     *
     * @return list of tasks loaded from the file path
     * @throws DukeException when IOException is catch.
     */
    public ArrayList<String> loadFile() throws DukeException {
        ArrayList<String> tAL = new ArrayList<>();
        try {
            this.scanF = new Scanner(this.sF);
            while (this.scanF.hasNext()) {
                tAL.add(this.scanF.nextLine());
            }
            return tAL;
        } catch (FileNotFoundException e){
            throw new DukeException("No file to load!");
        }
    }
}
