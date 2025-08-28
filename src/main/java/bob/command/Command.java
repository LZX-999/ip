package bob.command;

import bob.storage.Storage;
import bob.task.TaskManager;
import bob.ui.Ui;

public abstract class Command {

    public abstract void execute(TaskManager t, Ui ui, Storage storage);

    public boolean isBye() {
        return false;
    }
}
