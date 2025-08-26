public class Deadline extends Task {
    private String by;

    Deadline(String s, String by, boolean isDone) {
        super(s, "D", isDone);
        this.by = by;
    }

    @Override
    public String saveString() {
        return String.format("%s|%s|%s|%s", 
            "D", super.getTaskName(), by, super.returnDone());
    }
    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " + by + ")";
    }
}
