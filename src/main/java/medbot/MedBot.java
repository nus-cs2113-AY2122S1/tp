package medbot;

import medbot.command.Command;

import java.io.FileNotFoundException;

import medbot.exceptions.MedBotException;
import medbot.list.MedicalStaffList;
import medbot.list.PatientList;
import medbot.utilities.ViewType;

public class MedBot {
    public static void main(String[] args) {
        interactWithUser();
    }

    public static void interactWithUser() {

        Scheduler scheduler = new Scheduler();
        Ui ui = new Ui();
        Storage storage = null;
        ViewType viewContext;
        boolean isInteracting = true;

        ui.printWelcomeMessageOne();
        try {
            storage = new Storage();
            String loadStorageErrorMessage = storage.loadStorage(scheduler.getPatientList());

            if (!loadStorageErrorMessage.isBlank()) {
                ui.printOutput(loadStorageErrorMessage);
            }
            ui.printWelcomeMessageTwo();

        } catch (FileNotFoundException | MedBotException e) {
            ui.printOutput(e.getMessage());
        }

        while (isInteracting) {
            String userInput = ui.readInput();
            try {
                Command command = Parser.parseCommand(userInput);
                command.execute(scheduler, ui);
                storage.saveData(scheduler.getPatientList());
                isInteracting = !command.isExit();

            } catch (MedBotException mbe) {
                ui.printOutput(mbe.getMessage());
            }
        }
    }
}
