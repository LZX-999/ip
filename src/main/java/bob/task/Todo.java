package bob.task;

public class Todo extends Task {
    public Todo(String s, boolean isDone, int id) {
        super(s, "T", isDone, id);
    }

    @Override
    public String saveString() {
        return String.format("%s|%s|%s|%s",
            "T", super.getTaskName(), super.returnDone(), Integer.toString(super.getId()));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
