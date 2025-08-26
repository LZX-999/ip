public class Event extends Task {
    private String from;
    private String to;

    Event(String s, String from, String to, boolean isDone) {
        super(s, "E", isDone);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")"; 
    }
    
}
