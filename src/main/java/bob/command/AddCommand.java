package bob.command;

import java.io.IOException;

import bob.task.Task;
import bob.task.TaskManager;
import bob.ui.Ui;
import bob.storage.Storage;

public class AddCommand extends Command {
    private final Task t;

    public AddCommand(Task t) {
        this.t = t;
    }

    public void execute(TaskManager tasks, Ui ui, Storage storage) {
        tasks.addTask(t);
        ui.printAddEvent(t);
    }
}