import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    private LocalDate by;

    Deadline(String s, LocalDate by, boolean isDone) {
        super(s, "D", isDone);
        this.by = by;
    }

    @Override
    public String saveString() {
        return String.format("%s|%s|%s|%s", 
            "D", super.getTaskName(), by.format(INPUT_FORMAT), super.returnDone());
    }
    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}
