import java.util.Scanner;

public class Ui {
    private final String LOGO = " /$$$$$$$   /$$$$$$  /$$$$$$$ \n" + //
                        "| $$__  $$ /$$__  $$| $$__  $$\n" + //
                        "| $$  \\ $$| $$  \\ $$| $$  \\ $$\n" + //
                        "| $$$$$$$ | $$  | $$| $$$$$$$ \n" + //
                        "| $$__  $$| $$  | $$| $$__  $$\n" + //
                        "| $$  \\ $$| $$  | $$| $$  \\ $$\n" + //
                        "| $$$$$$$/|  $$$$$$/| $$$$$$$/\n" + //
                        "|_______/  \\______/ |_______/ \n" + //
                        "                              \n" + //
                        "                              \n" + //
                        "                              ";
    private final String LINES = "____________________________________________________________";
    Scanner sc = new Scanner(System.in);
    
    Ui() {

    }

    public void printLogo() {
        printLine();
        System.out.println("Hello from\n" + LOGO);
        printLine();
    }

    public void printLine() {
        System.out.println(LINES);
    }

    public String readInput() {
        String  ret = "";
        ret = sc.nextLine();
        return ret;
    }

    public void printMark(Task t) {
        printLine();
        System.out.println("Tasked marked as done: ");
        System.out.println(t);
        printLine();
    }

    public void printUnmark(Task t) {
        printLine();
        System.out.println("Tasked unmarked as not done: ");
        System.out.println(t);
        printLine();
    }

    public void printAddEvent(Task t) {
        printLine();
        System.out.println("Added task");
        System.out.println(t);
        printLine();
    }

    public void printWelcome() {
        printLine();
        System.out.println("Bob: Hello! I'm Bob! What can I do for you?");
        printLine();
    }

    public void printBye() {
        printLine();
        System.out.println("Bob: Bye. Hope to see you again soon!");
        printLine();
    }

    public void printUsage() {
        printLine();
        System.out.println("Bob: Unknown command");
        System.out.println("""
                Commands:
                1. todo <task desc> (Add todo task)
                2. deadline <task desc> /by <time> (Add deadline)
                3. event <task desc> /from <start> /to <end> (Add event)
                4. list (list all your task)
                5. mark <index> (Mark task as done)
                6. unmark <index> (Unmark task as not done)
                7. bye (Exit)
                """);
        printLine();
    }



}
