public class Task {
    private String name;
    private boolean isDone;
    private String tag;

    Task(String name, String tag) {
        this.name = name;
        isDone = false;
        this.tag = tag;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String returnDone() {
        return isDone ? "X" : "";
    }

    public String toString() {
        String box = "[" + (isDone ? "X" : "") + "]";
        return box + " " + name;
    }
}
