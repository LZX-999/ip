public class Bob {

    private void start() {
        Ui ui = new Ui();
        ui.printLogo();
        ui.printWelcome();
        Parser parser = new Parser();
        boolean run = true;
        while (run) {
            String input = ui.readInput();
            if (input.equals("bye")) {
                run = false;
                ui.printBye();
                break;
            }
            parser.run(input);
        }

    }
    
    public static void main(String[] args) {
        Bob bob = new Bob();
        bob.start();
    }
}
