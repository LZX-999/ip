public class Todo extends Task {
    Todo(String s) {
        super(s, "T");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
