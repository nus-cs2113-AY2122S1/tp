package taa;

import taa.command.Command;
import taa.exception.TaaException;
import taa.module.ModuleList;

public class Taa {
    private final ModuleList modules;
    private final Ui ui;

    public Taa() {
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
            } catch (TaaException e) {
                ui.printException(e.getMessage());
            }
        } while (!isExit);
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Taa().run();
    }
}
