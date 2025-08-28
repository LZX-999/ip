package bob.command;

import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskManager;
import bob.ui.Ui;

public class ListCommand extends Command {
    private final Task t;

    public ListCommand(Task t) {
        this.t = t;
    }

    public void execute(TaskManager tasks, Ui ui, Storage storage) {
        tasks.printTask();
    }
}
