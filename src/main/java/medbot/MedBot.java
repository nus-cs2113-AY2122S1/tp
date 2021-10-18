package medbot;

import medbot.command.Command;

import java.io.FileNotFoundException;
import medbot.exceptions.MedBotException;

public class MedBot {
    public static void main(String[] args) {
        interactWithUser();
    }

    public static void interactWithUser() {

        PatientList patientList = new PatientList();
        Ui ui = new Ui();
        Storage storage = null;
        boolean isInteracting = true;

        ui.printWelcomeMessageOne();
        try {
            storage = new Storage();
            String loadStorageErrorMessage = storage.loadStorage(patientList);

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
                isInteracting = !command.isExit();
                command.execute(patientList, ui);

                storage.saveData(patientList);

            } catch (MedBotException mbe) {
                ui.printOutput(mbe.getMessage());
            }
        }
    }
}
