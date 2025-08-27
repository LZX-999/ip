package bob.command;

import bob.task.TaskManager;
import bob.ui.Ui;
import bob.storage.Storage;

public abstract class Command {
    
    public abstract void execute(TaskManager t, Ui ui, Storage storage);

    public boolean isBye() {
        return false;
    }
}
