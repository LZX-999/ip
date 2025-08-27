public class MarkCommand extends Command {
    private final Task t;
    private final int idx;

    MarkCommand(Task t, int idx) {
        this.t = t;
        this.idx = idx;
    }

    public void execute(TaskManager tasks, Ui ui, Storage storage) {
        tasks.mark(idx);
        ui.printMark(tasks.getTask(idx));
    }
}
