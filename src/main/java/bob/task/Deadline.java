package bob.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDate by;

    public Deadline(String s, LocalDate by, boolean isDone, int id) {
        super(s, "D", isDone, id);
        this.by = by;
    }

    @Override
    public String saveString() {
        return String.format("%s|%s|%s|%s|%s", "D", super.getTaskName(), by.format(INPUT_FORMAT), super.returnDone(),
                Integer.toString(super.getId()));
    }

    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}
