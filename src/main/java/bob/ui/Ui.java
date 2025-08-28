package bob.ui;

import java.util.Scanner;

import bob.task.Task;

/**
 * Class responsible for handling Ui
 */
public class Ui {
    private static final String BOB_LOGO = " /$$$$$$$   /$$$$$$  /$$$$$$$ \n" + //
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
    private final String HORITZONAL_LINES = "____________________________________________________________";
    private Scanner sc = new Scanner(System.in);

    /**
     * Constructor for Ui
     */
    public Ui() {

    }

    /**
     * Method for printing logo
     */
    public void printLogo() {
        printLine();
        System.out.println("Hello from\n" + BOB_LOGO);
        printLine();
    }

    /**
     * Method for printing line
     */
    public void printLine() {
        System.out.println(HORITZONAL_LINES);
    }

    /**
     * Method for reading user input
     * 
     * @return user input in the form of string
     */
    public String readInput() {
        String ret = "";
        ret = sc.nextLine();
        return ret;
    }

    /**
     * Method for printing marked task
     * 
     * @param t Marked task for printing
     */
    public void printMark(Task t) {
        printLine();
        System.out.println("Tasked marked as done: ");
        System.out.println(t);
        printLine();
    }

    /**
     * Method for printing unmarked task
     * 
     * @param t Unmarked task for printing
     */
    public void printUnmark(Task t) {
        printLine();
        System.out.println("Tasked unmarked as not done: ");
        System.out.println(t);
        printLine();
    }

    /**
     * Method for printing added event
     * 
     * @param t Event to be printed
     */
    public void printAddEvent(Task t) {
        printLine();
        System.out.println("Added task");
        System.out.println(t);
        printLine();
    }

    /**
     * Method for printing welcome message
     */
    public void printWelcome() {
        printLine();
        System.out.println("Bob: Hello! I'm Bob! What can I do for you?");
        printLine();
    }

    /**
     * Method for printing bye message
     */
    public void printBye() {
        printLine();
        System.out.println("Bob: Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Method for printing bot usage
     */
    public void printUsage() {
        printLine();
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

    /**
     * Method for printing deleted task
     * 
     * @param t Deleted task to be printed
     */
    public void printDelete(Task t) {
        printLine();
        System.out.println("Deleted task");
        System.out.println(t);
        printLine();
    }

}
