import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Jabot" + System.lineSeparator() + "What can I do for you?");

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        ArrayList<Task> myList = new ArrayList<>();
        int aCount = 0;
        int wCount = 1;

        while(true){
            if(line.isBlank()){
                System.out.println("No input provided! Please input a task" + System.lineSeparator());
            } else if(line.equalsIgnoreCase("list")){
                if(myList.isEmpty()){
                    System.out.println("No task in the list!");
                }
                else {
                    System.out.println("Here are the tasks in your list:");
                    for(int i = 0; i < aCount; i++){
                        System.out.println(wCount + "." + myList.get(i));
                        wCount++;
                    }
                    wCount = 1;
                    System.out.println();
                }
            } else if(line.equalsIgnoreCase("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if(line.trim().toLowerCase().startsWith("mark")){
                if(myList.isEmpty()){
                    System.out.println("No task in the list to mark as completed!");
                }
                try{
                    line = line.replaceAll(" ", "");
                    int tNum = Integer.parseInt(line.substring(line.indexOf("mark") + 4));
                    Task sTask = myList.get(tNum -1);
                    sTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() + sTask + System.lineSeparator());
                    System.out.println();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Input! please key in a number to mark a task" + System.lineSeparator());
                }
            } else if(line.trim().toLowerCase().startsWith("unmark")){
                try{
                    if(myList.isEmpty()){
                        System.out.println("No task in the list to mark as not completed!");
                    }
                    line = line.replaceAll(" ", "");
                    int tNum = Integer.parseInt(line.substring(line.indexOf("unmark") + 6));
                    Task sTask = myList.get(tNum - 1);
                    sTask.markNotDone();
                    System.out.println("OK, I've unmarked this task as not done yet:" + System.lineSeparator() + sTask);
                    System.out.println();
                    //line = in.nextLine();
                } catch (NumberFormatException e){
                    System.out.println("Invalid Input! please key in a number to unmark a task" + System.lineSeparator());
                    //line = in.nextLine();
                }

            } else if(line.toLowerCase().startsWith("todo")){
                try{
                    String tdTask = line.substring(line.indexOf("todo") + 5);
                    if (tdTask.isBlank()) {
                        throw new StringIndexOutOfBoundsException();
                    }
                    myList.add(new toDo(tdTask));
                    System.out.println("Got it. I've added this task: " + System.lineSeparator() + myList.get(aCount));
                    aCount++;
                    System.out.println("Now you have " + aCount + " tasks in the list" + System.lineSeparator());
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Oh No! No or invalid task specified for todo.");
                }
            } else if(line.trim().toLowerCase().startsWith("deadline")){
                try{
                    String dTask = line.substring(line.indexOf("deadline") + 9, line.indexOf("/by"));
                    String dDate = line.substring(line.indexOf("/by") + 4);
                    if (dTask.isBlank()) {
                        throw new StringIndexOutOfBoundsException();
                    }
                    myList.add(new Deadline(dTask, dDate));
                    System.out.println("Got it. I've added this task: " + System.lineSeparator() + myList.get(aCount));
                    aCount++;
                    System.out.println("Now you have " + aCount + " tasks in the list" + System.lineSeparator());
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Oh No! No or invalid deadline specified");
                }
            } else if(line.toLowerCase().startsWith("event")){
                try {
                    String etTask = line.substring(line.indexOf("event") + 6, line.indexOf("/from"));
                    String etFrom = line.substring(line.indexOf("/from") + 6, line.indexOf("/to"));
                    String etTo = line.substring(line.indexOf("/to") + 4);
                    myList.add(new Event(etTask, etFrom, etTo));
                    System.out.println("Got it. I've added this task: " + System.lineSeparator() + myList.get(aCount));
                    aCount++;
                    System.out.println("Now you have " + aCount + " tasks in the list" + System.lineSeparator());
                } catch (StringIndexOutOfBoundsException e){
                    System.out.println("Oh No! No or invalid event specified.");
                }
            }
            else if (line.trim().toLowerCase().startsWith("delete")){
                try {
                    if(myList.isEmpty()){
                        System.out.println("No task in the list to delete!");
                    }
                    int tNum = Integer.parseInt(line.substring(line.indexOf("delete") + 7));
                    System.out.println("Noted. I've removed this task: " + System.lineSeparator() + myList.get(tNum-1));
                    myList.remove(tNum-1);
                    aCount--;
                    System.out.println("Now you have " + aCount + " tasks in the list" + System.lineSeparator());
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Invalid Input! Please key in a number to delete a task");
                }
            }
            else {
                System.out.println("added: " + line + System.lineSeparator());
                myList.add(new Task(line));
                aCount++;
            }
            line = in.nextLine();
        }
    }
}

