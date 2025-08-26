import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Bob {
    private static ArrayList<Task> lists = new ArrayList<>();
    private static String lines = "____________________________________________________________";
    private static void list() {
        System.out.println(lines);
        int count = 1;
        for (Task s : lists) {
            System.out.println(count + ". " + s);
            count++;
        }
        System.out.println(lines);
    }
    private static void delete(String input) {
        try {
            System.out.println(lines);
            System.out.println(lines);
            String[] parts = input.split(" ", 2);
            int idx = Integer.parseInt(parts[1]);
            if (idx < 0 || idx - 1 >= lists.size()) {
                throw new IndexOutOfBoundsException();
            }
            Task task = lists.get(idx - 1);
            System.out.println("Bob: Deleting this task: ");
            System.out.println(task);
            lists.remove(idx - 1);
            System.out.println("You now have " + lists.size() + " tasks");
            System.out.println(lines);
            
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Provide valid task number! Usage delete <task number>");
        }
        catch (NumberFormatException e) {
            System.out.println("Enter a valid number!");
        }
    }
    private static void todo(String input) {
        try {
            System.out.println(lines);
            String[] parts = input.split(" ", 2);
            Todo todo = new Todo(parts[1], false);
            lists.add(todo);
            System.out.println("Bob: Added new todo " + todo);
            System.out.println(lines);

        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Usage todo <Task name>!");
        }
    }
    
    private static void deadline(String input) {
        try {
            System.out.println(lines);
            String pattern = "^deadline\\s+(.+)\\s+/by\\s+(.+)$";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(input);
            if (!m.matches()) {
                throw new InvalidEventUsageException("");
            }
            String description = m.group(1).trim();
            String byTime = m.group(2).trim();
            LocalDate date;
            try {
                date = LocalDate.parse(byTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                System.out.println("Date must be in yyyy-MM-dd format!");
                return;
            }
            String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            Deadline deadline = new Deadline(description, date, false);
            lists.add(deadline);
            System.out.println("Bob: Added new deadline [D]" + deadline.getTaskName() + " (by: " + formattedDate + ")");
            System.out.println(lines);
        }
        catch (InvalidEventUsageException e) {
            System.out.println("Usage: deadline <task desc> /by <yyyy-MM-dd>");
        }
    }

    public static void event(String input) {
        try {
            System.out.println(lines);
            String pattern = "^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)$";
            java.util.regex.Pattern r = java.util.regex.Pattern.compile(pattern);
            java.util.regex.Matcher m = r.matcher(input);
            if (!m.matches()) {
                throw new InvalidEventUsageException("");
            }
            String description = m.group(1).trim();
            String fromTime = m.group(2).trim();
            String toTime = m.group(3).trim();
            LocalDate fromDate, toDate;
            try {
                fromDate = LocalDate.parse(fromTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                toDate = LocalDate.parse(toTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (toDate.isBefore(fromDate)) {
                    throw new InvalidEventUsageException("");
                }
            } 
            catch (DateTimeParseException e) {
                System.out.println("Dates must be in yyyy-MM-dd format!");
                return;
            }
            catch (InvalidEventUsageException e) {
                System.out.println("You cannot time travel bro");
                return;
            }
            String formattedFrom = fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            String formattedTo = toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            Event event = new Event(description, fromDate, toDate, false);
            lists.add(event);
            System.out.println("Bob: Added new event [E]" + event.getTaskName() + " (from: " + formattedFrom + " to: " + formattedTo + ")");
            System.out.println(lines);
        }
        catch (InvalidEventUsageException e) {
            System.out.println("Usage: event <task desc> /from <yyyy-MM-dd> /to <yyyy-MM-dd>");
        }
    }

    public static void unmark(String input) {
        try {
            System.out.println(lines);
            String[] parts = input.split(" ", 2);
            int idx = Integer.parseInt(parts[1]);
            if (idx < 0 || idx - 1 >= lists.size()) {
                throw new IndexOutOfBoundsException();
            }
            lists.get(idx - 1).unmarkDone();
            System.out.println("Tasked marked as not done: ");
            System.out.println(lists.get(idx - 1));
            System.out.println(lines);
        }
        catch (NumberFormatException e) {
            System.out.println("Enter a valid number!");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Attempted to mark task that does not exists!");
        }   
    }

    public static void mark(String input) {
        try {
            System.out.println(lines);
            String[] parts = input.split(" ", 2);
            int idx = Integer.parseInt(parts[1]);
            if (idx < 0 || idx - 1 >= lists.size()) {
                throw new IndexOutOfBoundsException();
            }
            lists.get(idx - 1).markDone();
            System.out.println("Tasked marked as done: ");
            System.out.println(lists.get(idx - 1));
            System.out.println(lines);
        }
        catch (NumberFormatException e) {
            System.out.println("Enter a valid number!");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Attempted to mark task that does not exists!");
        }
    }
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
        System.out.println(lines);
        System.out.println("Bob: Hello! I'm Bob! What can I do for you?");
        System.out.println(lines);
        Storage storage = new Storage();
        try {
            lists = storage.loadData();
        }
        catch (IOException e) {
            System.out.println("Error while trying to load data");
        }
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
                list();
                break;
            case "todo":
                todo(input);
                try {
                    storage.saveTask(lists);
                }
                catch (IOException e) {
                    System.out.println("Task added but unable to save tasklist persistently");
                }
                break;
            case "deadline": {
                deadline(input);
                try {
                    storage.saveTask(lists);
                }
                catch (IOException e) {
                    System.out.println("Task added but unable to save tasklist persistently");
                }
                break;
            }
            case "event": {
                event(input);
                try {
                    storage.saveTask(lists);
                }
                catch (IOException e) {
                    System.out.println("Task added but unable to save tasklist persistently");
                }
                break;
            }
            case "mark": {
                mark(input);
                try {
                    storage.saveTask(lists);
                }
                catch (IOException e) {
                    System.out.println("Task marked but unable to save task persistently");
                }
                break;
            }
            case "unmark": {
                unmark(input);
                try {
                    storage.saveTask(lists);
                }
                catch (IOException e) {
                    System.out.println("Task unmarked but unable to save task persistently");
                }
                break;
            }
            case "delete": {
                delete(input);
                try {
                    storage.saveTask(lists);
                }
                catch (IOException e) {
                    System.out.println("Task deleted but unable to save tasklist persistently");
                }
                break;
            }
            default:
                System.out.println(lines);
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
                System.out.println(lines);
                break;
            }
        }
        sc.close();
    }
}
