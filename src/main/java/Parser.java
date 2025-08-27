import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    Storage storage = new Storage();
    private TaskManager manager;
    Ui ui = new Ui();

    Parser(TaskManager manager) {
        this.manager = manager;
    }

    private static int delete(String input) {
        try {
            String[] parts = input.split(" ", 2);
            int idx = Integer.parseInt(parts[1]) - 1;
            return idx;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid number!");
            return -1;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Attempted to delete task that does not exists!");
            return -1;
        }
    }
    private Task todo(String input) {
        try {
            String[] parts = input.split(" ", 2);
            Todo todo = new Todo(parts[1], false, manager.assignId());
            return todo;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Usage todo <Task name>!");
        }
    }
    
    private Task deadline(String input) {
        Task ret;
        try {
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
            ret = new Deadline(description, date, false, manager.assignId());
            return ret;
        } catch (InvalidEventUsageException e) {
            System.out.println("Usage: deadline <task desc> /by <yyyy-MM-dd>");
            return null;
        }
    }

    private Task event(String input) {
        try {
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
            Event event = new Event(description, fromDate, toDate, false, manager.assignId());
            System.out.println("Bob: Added new event [E]" + event.getTaskName() + " (from: " + formattedFrom + " to: " + formattedTo + ")");
            return event;
        } catch (InvalidEventUsageException e) {
            System.out.println("Usage: event <task desc> /from <yyyy-MM-dd> /to <yyyy-MM-dd>");
            return null;
        }
    }

    private static int unmark(String input) {
        try {
            String[] parts = input.split(" ", 2);
            int idx = Integer.parseInt(parts[1]) - 1;
            return idx;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid number!");
            return -1;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Attempted to unmark task that does not exists!");
            return -1;
        }
    }

    private static int mark(String input) {
        try {
            String[] parts = input.split(" ", 2);
            int idx = Integer.parseInt(parts[1]) - 1;
            return idx;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid number!");
            return -1;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Attempted to mark task that does not exists!");
            return -1;
        }
    }
    
    private String parse(String input) {
        String[] splitString = input.split(" ", 2);
        return splitString[0]; 
    }

    public Command run(String input) {
        String firstArg = parse(input);
        switch (firstArg) {
            case "bye":
                return new ByeCommand(null);
            case "list":
                return new ListCommand(null);
            case "todo":
                try {
                    Task todo = todo(input);
                    if (todo == null) {
                        throw new InvalidEventUsageException("");
                    }
                    return new AddCommand(todo);
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
                    return new AddCommand(deadline);
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
                    return new AddCommand(event);
                } catch (InvalidEventUsageException e) {
                    ui.printUsage();
                } 
                break;
            }
            case "mark": {
                try {
                    int idx = mark(input);
                    Task marked = manager.getTask(idx);
                    if (marked == null || idx < 0) {
                        throw new  InvalidEventUsageException( "");
                    } 
                    return new MarkCommand(marked, idx);
                } catch (InvalidEventUsageException e) {
                    ui.printUsage();
                    return null;
                }
            }
            case "unmark": {
                try {
                    int idx = unmark(input);
                    Task unmarked = manager.getTask(idx);
                    if (unmarked == null || idx < 0) {
                        throw new InvalidEventUsageException("");
                    }
                    return new UnmarkCommand(unmarked, idx);
                } catch (InvalidEventUsageException e) {
                        ui.printUsage();
                        return null;
                }
            }
            case "delete": {
                try {
                    int idx = delete(input);
                    Task deleted = manager.getTask(idx);
                    if (deleted == null || idx < 0) {
                        throw new InvalidEventUsageException("");
                    }
                    return new DeleteCommand(deleted, idx);
                } catch (InvalidEventUsageException e) {
                    ui.printUsage();
                    return null;
                }
            }
            default:
                ui.printUsage();
                return null;
            }
            return null;
        }

    }

