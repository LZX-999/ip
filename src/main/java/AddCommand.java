import java.io.IOException;

public class AddCommand extends Command {
    private final Task t;

    AddCommand(Task t) {
        this.t = t;
    }

    public void execute(TaskManager tasks, Ui ui, Storage storage) {
        tasks.addTask(t);
        ui.printAddEvent(t);
    }
}