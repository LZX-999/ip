public class Event extends Task {
    private String from;
    private String to;

    Event(String s, String from, String to, boolean isDone) {
        super(s, "E", isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String saveString() {
        return String.format("%s|%s|%s|%s", 
            "E", super.getTaskName(), this.from, this.to, super.returnDone());
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")"; 
    }
    
}
