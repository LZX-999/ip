package bob.command;

import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskManager;
import bob.ui.Ui;

public class ByeCommand extends Command {
    private final Task t;

    public ByeCommand(Task t) {
        this.t = t;
    }

    public void execute(TaskManager tasks, Ui ui, Storage storage) {
        ui.printBye();
    }

    @Override
    public boolean isBye() {
        return true;
    }
}
