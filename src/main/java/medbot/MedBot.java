package medbot;

import medbot.command.Command;
import medbot.exceptions.MedBotException;
import medbot.parser.Parser;
import medbot.storage.Storage;
import medbot.storage.StorageManager;
import medbot.ui.Ui;

import java.io.IOException;

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
        StorageManager storageManager = new StorageManager();
        boolean isInteracting = true;

        ui.printWelcomeMessageOne();
        try {
            storageManager.initializeStorages(scheduler, ui);

        } catch (MedBotException e) {
            ui.printOutput(e.getMessage());
            isInteracting = false;
        }
        if (isInteracting) {
            ui.printWelcomeMessageTwo();
        }

        while (isInteracting) {
            String userInput = ui.readInput();
            try {
                Command command = Parser.parseCommand(userInput);
                command.execute(scheduler, ui);

                storageManager.saveToStorage(scheduler);
                isInteracting = !command.isExit();

            } catch (IOException e) {
                ui.printOutput(Storage.ERROR_SAVE_STORAGE + Storage.ERROR_MOVE_STORAGE_FILES
                        + System.lineSeparator());
                isInteracting = false;

            } catch (MedBotException mbe) {
                ui.printOutput(mbe.getMessage() + System.lineSeparator());
            }
        }
    }
}
