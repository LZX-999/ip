public class Task {
    private String name;
    private boolean isDone;
    private String tag;

    Task(String name, String tag, boolean isDone) {
        this.name = name;
        isDone = isDone;
        this.tag = tag;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String getTag() {
        return this.tag;
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
