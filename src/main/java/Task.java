public class Task {
    private String name;
    private boolean isDone;

    Task(String name) {
        this.name = name;
        isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String toString() {
        String box = "[" + (isDone ? "X" : "") + "]";
        return box + " " + name;
    }
}
