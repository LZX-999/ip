package bob.command;

import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskManager;
import bob.ui.Ui;

public class FindCommand extends Command {
    private final Task task;
    private final int idx;

    public FindCommand(Task task, int idx) {
        this.task = task;
        this.idx = idx;
    }

    public void execute(TaskManager tasks, Ui ui, Storage storage) {
        ui.printLine();
        tasks.find(task.getTaskName());
        ui.printLine();
    }
}
