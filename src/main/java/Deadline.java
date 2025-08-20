public class Deadline extends Task {
    private String by;

    Deadline(String s, String by) {
        super(s);
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " + by + ")";
    }
}
