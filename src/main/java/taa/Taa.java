package taa;

import taa.command.Command;
import taa.exception.TaaException;
import taa.module.ModuleList;

public class Taa {
    private static final String DATA_FILENAME = "./data/taa_data.json";

    private ModuleList moduleList;
    private final Ui ui = new Ui();
    private final Storage storage = new Storage(DATA_FILENAME);

    public Taa() {

    }

    public void run() {
        loadModuleListFromStorage();
        assert moduleList != null;

        ui.printWelcomeMessage();

        boolean isExit = false;
        do {
            String userInput = ui.getUserInput();

            try {
                Command command = Parser.parseUserInput(userInput);
                command.execute(moduleList, ui, storage);
                isExit = command.isExit();
            } catch (TaaException e) {
                ui.printException(e.getMessage());
            }
        } while (!isExit);
    }

    private void loadModuleListFromStorage() {
        ModuleList savedModuleList = null;
        try {
            savedModuleList = storage.load();
        } catch (TaaException e) {
            ui.printException(e.getMessage());
        }

        if (savedModuleList == null) {
            moduleList = new ModuleList();
        } else {
            moduleList = savedModuleList;
        }
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Taa taa = new Taa();
        taa.run();
    }
}
