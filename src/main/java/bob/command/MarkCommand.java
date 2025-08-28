package bob.command;

import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskManager;
import bob.ui.Ui;

public class MarkCommand extends Command {
    private final Task t;
    private final int idx;

    public MarkCommand(Task t, int idx) {
        this.t = t;
        this.idx = idx;
    }

    public void execute(TaskManager tasks, Ui ui, Storage storage) {
        tasks.mark(idx);
        ui.printMark(tasks.getTask(idx));
    }
}
