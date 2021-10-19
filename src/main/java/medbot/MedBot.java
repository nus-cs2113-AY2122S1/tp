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

        PatientList patientList = new PatientList();
        MedicalStaffList staffList = new MedicalStaffList();
        Ui ui = new Ui();
        Storage storage = null;
        ViewType viewContext;
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

        CommandManager commandManager = new CommandManager(patientList,staffList);
        while (isInteracting) {
            String userInput = ui.readInput();
            try {
                viewContext = commandManager.getViewType();
                Command command = Parser.parseCommand(userInput, viewContext);
                commandManager.executeCommand(ui, storage, command);
                isInteracting = !command.isExit();

            } catch (MedBotException mbe) {
                ui.printOutput(mbe.getMessage());
            }
        }
    }
}
