package taa;

//@@author leyondlee

import taa.teachingclass.ClassList;
import taa.command.Command;
import taa.exception.TaaException;
import taa.logger.TaaLogger;
import taa.storage.Storage;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Taa {
    public static final String DATA_FOLDER = "./data";
    public static final String DATA_FILENAME = "taa_data.json";

    public static final int DECIMAL_PLACES = 2;

    private ClassList classList;
    private final Ui ui;
    private final Storage storage;

    public Taa() {
        this.ui = new Ui();

        Path dataFilePath = Paths.get(DATA_FOLDER, DATA_FILENAME);
        this.storage = new Storage(dataFilePath.toString());
    }

    public Taa(boolean isLoggingEnabled) {
        this();
        TaaLogger.setEnabled(isLoggingEnabled);
    }

    public void run() {
        ui.printWelcomeMessage();

        loadClassListFromStorage();
        assert classList != null;

        boolean isExit = false;
        do {
            String userInput = ui.getUserInput();
            if (userInput == null) {
                userInput = Command.COMMAND_EXIT;
            }

            try {
                Command command = Parser.parseUserInput(userInput);
                command.parseArgument();
                command.checkArgument();
                command.execute(classList, ui, storage);
                isExit = command.isExit();
            } catch (TaaException e) {
                ui.printException(e.getMessage());
            }
        } while (!isExit);
    }

    private void loadClassListFromStorage() {
        ClassList savedClassList = null;
        try {
            savedClassList = storage.load();
        } catch (TaaException e) {
            ui.printException(e.getMessage());
        }

        if (savedClassList == null) {
            classList = new ClassList();
        } else {
            classList = savedClassList;
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
