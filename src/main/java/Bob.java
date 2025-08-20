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
        System.out.println("Bob: Hello! I'm Bob! What can I do for you?");
        System.out.println(lines);
        ArrayList<Task> lists = new ArrayList<>();
        boolean run = true;
        Scanner sc = new Scanner(System.in);
        while (run) {
            String input = sc.nextLine();
            String[] parts = input.split(" ", 2);
            String firstArgs = parts[0];
            switch (firstArgs) {
            case "bye":
                run = false;
                System.out.println(lines);
                System.out.println("Bob: Bye. Hope to see you again soon!");
                System.out.println(lines);
                break;
            case "list":
                int count = 1;
                for (Task s : lists) {
                    System.out.println(count + ". " + s);
                    count++;
                }
                break;
            case "todo":
                System.out.println(lines);
                Todo todo = new Todo(parts[1]);
                lists.add(todo);
                System.out.println("Bob: Added new todo " + todo);
                System.out.println(lines);
                break;
            case "deadline": {
                System.out.println(lines);
                String getArgs = parts[1];
                String[] splitted = getArgs.split("/");
                int idx = input.indexOf("/by") + 4;
                String date = input.substring(idx, input.length());
                Deadline deadline = new Deadline(splitted[0], date);
                lists.add(deadline);
                System.out.println("Bob: Added new deadline " + deadline);
                System.out.println(lines);
                break;
            }
            case "event": {
                System.out.println(lines);
                int fromIdx = input.indexOf("/from") + 6;
                int toIdx = input.indexOf("/to");
                String description = input.substring(6, fromIdx - 6).trim();
                String fromTime = input.substring(fromIdx, toIdx).trim();
                String toTime = input.substring(toIdx + 4).trim();
                Event event = new Event(description, fromTime, toTime);
                lists.add(event);
                System.out.println("Bob: Added new event " + event);
                System.out.println(lines);
                break;
            }
            case "mark": {
                System.out.println(lines);
                int idx = Integer.parseInt(parts[1]);
                lists.get(idx - 1).markDone();
                System.out.println("Tasked marked as done: ");
                System.out.println(lists.get(idx - 1));
                System.out.println(lines);
                break;
            }
            case "unmark": {
                System.out.println(lines);
                int idx = Integer.parseInt(parts[1]);
                lists.get(idx - 1).unmarkDone();
                System.out.println("Tasked marked as not done: ");
                System.out.println(lists.get(idx - 1));
                System.out.println(lines);
                break;
            }
            default:
                System.out.println(lines);
                System.out.println("Bob: Unknown command");
                System.out.println(lines);
                break;
            }
        }
        sc.close();
    }
}
