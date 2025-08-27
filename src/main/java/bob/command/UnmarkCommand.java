package bob.command;

import bob.task.Task;
import bob.task.TaskManager;
import bob.storage.Storage;
import bob.ui.Ui;

public class UnmarkCommand extends Command {
    private final Task t;
    private final int idx;

    public UnmarkCommand(Task t, int idx) {
        this.t = t;
        this.idx = idx;
    }

    public void execute(TaskManager tasks, Ui ui, Storage storage) {
        tasks.unmark(idx);
        ui.printUnmark(tasks.getTask(idx));

    }
}
