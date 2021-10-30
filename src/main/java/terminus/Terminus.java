package terminus;

import java.io.IOException;
import java.nio.file.Path;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.exception.InvalidFileException;
import terminus.module.ModuleManager;
import terminus.parser.CommandParser;
import terminus.parser.MainCommandParser;
import terminus.storage.StorageActionEnum;
import terminus.storage.StorageManager;
import terminus.storage.StorageTypeEnum;
import terminus.ui.Ui;

public class Terminus {

    public static final String[] INVALID_JSON_MESSAGE = {
        "Invalid file data detected.",
        "TermiNUS will still run, but the file will be overwritten when the next command is ran.",
        "To save your current file, close your terminal (do not run exit).",
        "Otherwise, you can continue using the program :)"
    };
    private Ui ui;
    private CommandParser parser;
    private String workspace;

    private ModuleManager moduleManager;
    private StorageManager storageManager;

    private static final Path DATA_DIRECTORY = Path.of(System.getProperty("user.dir"), "data");
    private static final String MAIN_JSON = "main.json";

    /**
     * Enters the main entry-point for the terminus.Terminus application.
     */
    public static void main(String[] args) {
        new Terminus().run();
    }

    /**
     * Starts the program.
     */
    public void run() {
        start();
        runCommandsUntilExit();
        exit();
    }

    private void start() {
        try {
            TerminusLogger.initializeLogger();
            TerminusLogger.info("Starting Terminus...");
            this.ui = Ui.getInstance();
            this.parser = MainCommandParser.getInstance();
            this.workspace = "";
            this.storageManager = new StorageManager(DATA_DIRECTORY, MAIN_JSON);
            this.moduleManager = this.storageManager.initialize();
        } catch (IOException e) {
            TerminusLogger.warning("Log file loading has failed.", e.fillInStackTrace());
            handleIoException(e);
        } catch (InvalidFileException e) {
            ui.printSection(e.getMessage());
        } finally {
            if (this.moduleManager == null) {
                TerminusLogger.warning("File not found.");
                TerminusLogger.warning("Creating new NusModule instance...");
                this.moduleManager = new ModuleManager();
            } else {
                TerminusLogger.info("File loaded.");
            }
            this.ui.printParserBanner(this.parser, this.moduleManager);
        }
        TerminusLogger.info("Terminus has started.");
    }

    private void runCommandsUntilExit() {
        while (true) {
            assert workspace != null : "Workspace should always have a value";
            String input = ui.requestCommand(workspace);
            TerminusLogger.debug("User entered: " + input);
            assert input != null : "Input should not be null.";

            Command currentCommand;
            try {
                currentCommand = parser.parseCommand(input);
                CommandResult result = currentCommand.execute(moduleManager);

                boolean isExitCommand = result.isExit();
                boolean isWorkspaceCommand = result.getNewCommandParser() != null;
                if (isExitCommand) {
                    break;
                } else if (isWorkspaceCommand) {
                    parser = result.getNewCommandParser();
                    assert parser != null : "commandParser is not null";
                    workspace = parser.getWorkspace();
                    ui.printParserBanner(parser, moduleManager);
                } else {
                    ui.printSection(result.getMessage());
                }

                // Perform related storage changes
                if (result.hasChange()) {
                    // Pass to Storage to handle the request
                    String affectedModule = result.getModule();
                    StorageActionEnum storageAction = result.getStorageAction();
                    StorageTypeEnum storageType = result.getStorageType();
                    String deletedItemName = result.getDeletedItemName();
                    storageManager.execute(moduleManager, affectedModule, deletedItemName, storageAction, storageType);
                }

                // Update JSON File
                TerminusLogger.info("Saving data into file...");
                this.storageManager.execute(moduleManager, null,
                        null, StorageActionEnum.UPDATE, StorageTypeEnum.JSON);
                TerminusLogger.info("Save completed.");

            } catch (InvalidCommandException e) {
                TerminusLogger.warning("Invalid input provided: " + input, e.fillInStackTrace());
                ui.printSection(e.getMessage());
            } catch (InvalidArgumentException e) {
                TerminusLogger.warning("Invalid input provided: " + input, e.fillInStackTrace());

                // Check if the exception specified a correct command format for the user to follow.
                if (e.getFormat() != null) {
                    // Print the format of the command along with the error message to the user.
                    ui.printSection(e.getMessage(),
                            String.format(Messages.INVALID_ARGUMENT_FORMAT_MESSAGE, e.getFormat()));
                } else {
                    ui.printSection(e.getMessage());
                }
            } catch (InvalidFileException e) {
                ui.printSection(e.getMessage());
                ui.printSection(Messages.ERROR_STORAGE_DISABLE_RESPONSE);
                //storageManager.setDisabled(true);
            }
        }
    }

    private void handleIoException(IOException e) {
        TerminusLogger.severe("Save file is inaccessible.");
        TerminusLogger.severe(e.getMessage(), e.getCause());
        ui.printSection(
                String.format(Messages.ERROR_MESSAGE_FILE, e.getMessage()),
                "TermiNUS may still run, but your changes may not be saved.",
                "Check 'terminus.log' for more information."
        );
    }

    private void exit() {
        TerminusLogger.info("Saving data into file...");
        try {
            storageManager.setDisabled(false);
            this.storageManager.save(moduleManager);
            TerminusLogger.info("Save completed.");
        } catch (InvalidFileException e) {
            TerminusLogger.warning("File saving has failed.");
            ui.printSection(e.getMessage());
        }
        this.ui.printExitMessage();
    }
}
