public abstract class Command {
    
    public abstract void execute(TaskManager t, Ui ui, Storage storage);

    public boolean isBye() {
        return false;
    }
}
