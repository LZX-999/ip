import java.util.Scanner;
import java.util.ArrayList;

public class Bob {
    public static void main(String[] args) {
        String logo = " /$$$$$$$   /$$$$$$  /$$$$$$$ \n" + //
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
        System.out.println("Hello from\n" + logo);
        String lines = "____________________________________________________________";
        System.out.println(lines);
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        System.out.println(lines);
        ArrayList<Task> lists = new ArrayList<>();
        boolean run = true;
        Scanner sc = new Scanner(System.in);
        while (run) {
            System.out.print("Enter text: ");
            String input = sc.nextLine();
            String[] parts = input.split(" ");
            String firstArgs = parts[0];
            switch (firstArgs) {
                case "bye":
                    run = false;
                    System.out.println(lines);
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(lines);
                    break;
                case "list":
                    int count = 1;
                    for (Task s : lists) {
                        System.out.println(count + ". " + s);
                        count++;
                    }
                    break;
                case "mark":
                {
                    System.out.println(lines);
                    int idx = Integer.parseInt(parts[1]);
                    lists.get(idx - 1).markDone();
                    System.out.println("Tasked marked as done: ");
                    System.out.println(lists.get(idx - 1));
                    System.out.println(lines);
                    break;
                }
                case "unmark":
                {
                    System.out.println(lines);
                    int idx = Integer.parseInt(parts[1]);
                    lists.get(idx - 1).unmarkDone();
                    System.out.println("Tasked unmarked as not done: ");
                    System.out.println(lists.get(idx - 1));
                    System.out.println(lines);
                    break;
                }
                default:
                    System.out.println(lines);
                    System.out.println("added: " + input);
                    lists.add(new Task(input));
                    System.out.println(lines);
                    break;
            } 
        }
        sc.close();
    }
}
