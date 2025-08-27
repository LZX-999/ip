public class ListCommand extends Command {
    private final Task t;

    ListCommand(Task t) {
        this.t = t;
    }

    public void execute(TaskManager tasks, Ui ui, Storage storage) {
        tasks.printTask();
    }
}
