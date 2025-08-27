import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Bob {
    private static String lines = "____________________________________________________________";
    
    private static int delete(String input) {
        try {
            System.out.println(lines);
            System.out.println(lines);
            String[] parts = input.split(" ", 2);
            int idx = Integer.parseInt(parts[1]);
            return idx;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid number!");
            return -1;
        }
    }
    private static Task todo(String input) {
        try {
            System.out.println(lines);
            String[] parts = input.split(" ", 2);
            Todo todo = new Todo(parts[1], false);
            return todo;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Usage todo <Task name>!");
        }
    }
    
    private static Task deadline(String input) {
        Task ret;
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
                return null;
            }
            String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            ret = new Deadline(description, date, false);
            System.out.println("Bob: Added new deadline [D]" + ret.getTaskName() + " (by: " + formattedDate + ")");
            System.out.println(lines);
            return ret;
        } catch (InvalidEventUsageException e) {
            System.out.println("Usage: deadline <task desc> /by <yyyy-MM-dd>");
            return null;
        }
    }

    public static Task event(String input) {
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
            } catch (DateTimeParseException e) {
                System.out.println("Dates must be in yyyy-MM-dd format!");
                return null;
            } catch (InvalidEventUsageException e) {
                System.out.println("You cannot time travel bro");
                return null;
            }
            String formattedFrom = fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            String formattedTo = toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            Event event = new Event(description, fromDate, toDate, false);
            System.out.println("Bob: Added new event [E]" + event.getTaskName() + " (from: " + formattedFrom + " to: " + formattedTo + ")");
            System.out.println(lines);
            return event;
        } catch (InvalidEventUsageException e) {
            System.out.println("Usage: event <task desc> /from <yyyy-MM-dd> /to <yyyy-MM-dd>");
            return null;
        }
    }

    public static int unmark(String input) {
        try {
            System.out.println(lines);
            String[] parts = input.split(" ", 2);
            int idx = Integer.parseInt(parts[1]);
            return idx;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid number!");
            return -1;
        }  
    }

    public static int mark(String input) {
        try {
            System.out.println(lines);
            String[] parts = input.split(" ", 2);
            int idx = Integer.parseInt(parts[1]);
            return idx;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid number!");
            return -1;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Attempted to mark task that does not exists!");
            return -1;
        }
    }
    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskManager manager = new TaskManager();
        ui.printLogo();
        ui.printWelcome();
        boolean run = true;
        while (run) {
            String input = ui.readInput();
            String[] parts = input.split(" ", 2);
            String firstArgs = parts[0];
            switch (firstArgs) {
            case "bye":
                run = false;
                ui.printBye();
                break;
            case "list":
                ui.printLine();
                manager.printTask();
                ui.printLine();
                break;
            case "todo":
                try {
                    Task todo = todo(input);
                    if (todo == null) {
                        throw new InvalidEventUsageException("");
                    }
                    manager.addTask(todo);
                    ui.printAddEvent(todo);
                } catch (InvalidEventUsageException e) {
                    ui.printUsage();
                }
                    break;
            case "deadline": {
                try {
                    Task deadline = deadline(input);
                    if (deadline == null) {
                        throw new InvalidEventUsageException("");
                    }
                    manager.addTask(deadline);
                    ui.printAddEvent(deadline);
                } catch (InvalidEventUsageException e) {
                    ui.printUsage();
                }
                break;
            }
            case "event": {
                try {
                    Task event = event(input);
                    if (event == null) {
                        throw new InvalidEventUsageException("");
                    }
                    manager.addTask(event);
                    ui.printAddEvent(event);
                } catch (InvalidEventUsageException e) {

                } 
                break;
            }
            case "mark": {
                try {
                    int idx = mark(input);
                    Task marked = manager.mark(idx);
                    if (marked == null) {
                        throw new  InvalidEventUsageException( "");
                    } 
                    ui.printMark(marked);
                } catch (InvalidEventUsageException e) {
                    ui.printUsage();
                }
                break;
            }
            case "unmark": {
                try {
                    int idx = unmark(input);
                    Task unmarked = manager.unmark(idx);
                    if (unmarked == null) {
                        throw new InvalidEventUsageException("");
                    }
                    ui.printUnmark(unmarked);
                } catch (InvalidEventUsageException e) {
                        ui.printUsage();
                }
                break;
            }
            case "delete": {
                try {
                    int idx = delete(input);
                    Task deleted = manager.deleteTask(idx);
                    if (deleted == null) {
                        throw new InvalidEventUsageException("");
                    }
                    ui.printDelete(deleted);
                } catch (InvalidEventUsageException e) {
                    ui.printUsage();
                }
                break;
            }
            default:
                ui.printUsage();
                break;
            }
        }
    }
}
