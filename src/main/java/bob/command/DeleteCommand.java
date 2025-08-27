package bob.command;

import bob.task.Task;
import bob.task.TaskManager;
import bob.storage.Storage;
import bob.ui.Ui;

public class DeleteCommand extends Command {
    private final Task t;
    private final int idx;

    public DeleteCommand(Task t, int idx) {
        this.t = t;
        this.idx = idx;
    }

    public void execute(TaskManager tasks, Ui ui, Storage storage) {
        Task del = tasks.deleteTask(idx);
        ui.printDelete(del);
    }
}
