import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    private LocalDate from;
    private LocalDate to;

    Event(String s, LocalDate from, LocalDate to, boolean isDone) {
        super(s, "E", isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String saveString() {
        return String.format("%s|%s|%s|%s|%s", 
            "E", super.getTaskName(), from.format(INPUT_FORMAT), to.format(INPUT_FORMAT), super.returnDone());
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMAT) + " to: " + to.format(OUTPUT_FORMAT) + ")"; 
    }
    
}
