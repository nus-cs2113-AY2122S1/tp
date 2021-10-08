package medbot;

import medbot.command.Command;

import java.io.FileNotFoundException;

public class MedBot {
    public static void main(String[] args) {
        interactWithUser();
    }

    public static void interactWithUser() {

        PatientList patientList = new PatientList();
        Ui ui = new Ui();
        Storage storage = null;
        boolean isInteracting = true;

        ui.printWelcomeMessage();
        try {
            storage = new Storage();
            storage.loadStorage(patientList);
        } catch (FileNotFoundException | MedBotException e) {
            System.out.println(e.getMessage());
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
