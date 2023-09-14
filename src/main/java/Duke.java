import java.util.Scanner;
import java.util.Arrays;
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
        String[] myList = new String[100];
        int acount = 0;
        int wcount = 1;

        while(true){
            if(line.equals("list")){
                String[] dlist = Arrays.copyOf(myList, acount);
                for(String words : dlist){
                    System.out.println(wcount + ". " + words);
                    wcount++;
                }
                wcount = 1;
                System.out.println();
                line = in.nextLine();
            } else if(line.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else{
                System.out.println("added: " + line + System.lineSeparator());
                myList[acount] = line;
                acount++;
                line = in.nextLine();
            }
        }
    }
}
