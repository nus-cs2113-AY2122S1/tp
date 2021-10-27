package medbot;

import medbot.command.Command;
import medbot.exceptions.MedBotException;
import medbot.parser.Parser;
import medbot.storage.StorageManager;
import medbot.ui.Ui;

import java.io.FileNotFoundException;

public class MedBot {
    public static void main(String[] args) {
        interactWithUser();
    }

    /**
     * Runs a loop to get inputs from the user and executes the commands
     * until an 'exit' command is given.
     */
    public static void interactWithUser() {
        Scheduler scheduler = new Scheduler();
        Ui ui = new Ui();
        StorageManager storageManager = null;

        ui.printWelcomeMessageOne();
        try {
            storageManager = new StorageManager(scheduler, ui);
        } catch (FileNotFoundException | MedBotException e) {
            ui.printOutput(e.getMessage());
        }
        ui.printWelcomeMessageTwo();
        boolean isInteracting = true;
        while (isInteracting) {
            String userInput = ui.readInput();
            try {
                Command command = Parser.parseCommand(userInput);
                command.execute(scheduler, ui);

                assert storageManager != null;
                storageManager.saveToStorage(scheduler);
                isInteracting = !command.isExit();

            } catch (MedBotException mbe) {
                ui.printOutput(mbe.getMessage() + System.lineSeparator());
            }
        }
    }
}
