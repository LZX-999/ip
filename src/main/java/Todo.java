public class Todo extends Task {
    Todo(String s, boolean isDone) {
        super(s, "T", isDone);
    }

    @Override
    public String saveString() {
        return String.format("%s|%s|%s",
            "T", super.getTaskName(), super.returnDone());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
