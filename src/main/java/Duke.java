import java.util.Scanner;
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

        while(true){
            if(line.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else{
                System.out.println(line + System.lineSeparator());
                line = in.nextLine();
            }
        }
    }
}
