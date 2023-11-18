package seedu.jabot.data;
import seedu.jabot.exceptions.DukeException;
import seedu.jabot.tasks.toDo;
import seedu.jabot.tasks.Deadline;
import seedu.jabot.tasks.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class TaskListTest {
    protected ArrayList<String> inputLine = new ArrayList<>();

    @Test
    public void TaskList_testLoadingTasks() throws DukeException {
        inputLine.add("T | 0 | cleaning");
        inputLine.add("D | 1 | wash up | afternoon Status: NA");
        inputLine.add("E | 0 | super party | 23 Nov 2023 00:00AM - 25 Nov 2023 23:59PM Status: NOT DUE");
        TaskList testTaskList = new TaskList(inputLine);
        //check task created correctly as toDo
        assertTrue(testTaskList.tempList.get(0) instanceof toDo );
        //check task created correctly as Deadline
        assertTrue(testTaskList.tempList.get(1) instanceof Deadline );
        //check task created correctly as Event
        assertTrue(testTaskList.tempList.get(2) instanceof Event );
        //check size of task list is equal to the number of task added
        assertEquals(3,testTaskList.tempList.size());
    }
}
