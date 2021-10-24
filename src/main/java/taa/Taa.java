package taa;

import taa.command.Command;
import taa.exception.TaaException;
import taa.logger.TaaLogger;
import taa.module.ModuleList;
import taa.storage.Storage;

public class Taa {
    private static final String DATA_FILENAME = "./data/taa_data.json";

    private ModuleList moduleList;
    private final Ui ui;
    private final Storage storage;

    public Taa() {
        this.ui = new Ui();
        this.storage = new Storage(DATA_FILENAME);
    }

    public Taa(boolean isLoggingEnabled) {
        this();
        TaaLogger.setEnabled(isLoggingEnabled);
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
                command.checkArgument();
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
        boolean isLoggingEnabled = false;
        for (String arg : args) {
            if (arg.equals("-log")) {
                isLoggingEnabled = true;
            }
        }

        Taa taa = new Taa(isLoggingEnabled);
        taa.run();
    }
}
