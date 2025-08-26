public class Task {
    private String taskName;
    private boolean isDone;
    private String tag;

    Task(String name, String tag, boolean isDone) {
        this.taskName = name;
        this.isDone = isDone;
        this.tag = tag;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getTag() {
        return this.tag;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String returnDone() {
        return isDone ? "X" : " ";
    }

    public String saveString() {
        return "";
    }

    public String toString() {
        String box = "[" + (isDone ? "X" : "") + "]";
        return box + " " + taskName;
    }
}
