package medbot;

import medbot.storage.PatientStorage;
import medbot.storage.StaffStorage;
import medbot.command.Command;
import medbot.exceptions.MedBotException;
import medbot.list.ListItemType;
import medbot.parser.Parser;
import medbot.ui.Ui;

import java.io.FileNotFoundException;

public class MedBot {
    public static void main(String[] args) {
        interactWithUser();
    }

    public static void interactWithUser() {

        Scheduler scheduler = new Scheduler();
        Ui ui = new Ui();
        PatientStorage patientStorage = null;
        StaffStorage staffStorage = null;
        boolean isInteracting = true;

        ui.printWelcomeMessageOne();
        try {
            patientStorage = new PatientStorage();
            staffStorage = new StaffStorage();
            String loadStorageErrorMessage = patientStorage.loadStorage(ListItemType.PATIENT,
                    scheduler.getPatientList());
            loadStorageErrorMessage += staffStorage.loadStorage(ListItemType.STAFF,
                    scheduler.getMedicalStaffList());

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
                assert patientStorage != null;
                assert staffStorage != null;

                patientStorage.saveData(scheduler.getPatientList());
                staffStorage.saveData(scheduler.getMedicalStaffList());
                isInteracting = !command.isExit();

            } catch (MedBotException mbe) {
                ui.printOutput(mbe.getMessage() + System.lineSeparator());
            }
        }
    }
}
