package bob;

import bob.ui.Ui;
import bob.storage.Storage;
import bob.task.TaskManager;
import bob.command.Command;
import bob.parser.Parser;
import bob.exception.InvalidEventUsageException;

public class Bob {

    private void start() {
        Ui ui = new Ui();
        ui.printLogo();
        ui.printWelcome();
        Storage storage = new Storage();
        TaskManager manager = new TaskManager(storage);
        Parser parser = new Parser(manager);
        boolean isBye = false;
        while (!isBye) {
            String input = ui.readInput();
            try {
                Command c = parser.run(input);
                if (c == null) {
                    throw new InvalidEventUsageException("");
                }
                c.execute(manager, ui, storage);
                isBye = c.isBye();
            } catch (InvalidEventUsageException e) {
                continue;
            }
        }

    }

    public static void main(String[] args) {
        Bob bob = new Bob();
        bob.start();
    }
}
