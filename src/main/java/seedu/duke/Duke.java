package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.module.ModuleList;

public class Duke {
    private final ModuleList modules;
    private final Ui ui;

    public Duke() {
        this.modules = new ModuleList();
        this.ui = new Ui();
    }

    public void run() {
        ui.printWelcomeMessage();

        boolean isExit = false;
        do {
            String userInput = ui.getUserInput();

            try {
                Command command = Parser.parseUserInput(userInput);
                command.execute(modules, ui);
                isExit = command.isExit();
            } catch (CustomException e) {
                ui.printException(e.getMessage());
            }
        } while (!isExit);
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
