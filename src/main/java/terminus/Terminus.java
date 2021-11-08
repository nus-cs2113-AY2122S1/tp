package terminus;

import java.io.IOException;
import java.nio.file.Path;
import java.util.NoSuchElementException;
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
import terminus.storage.StorageManager;
import terminus.ui.Ui;

public class Terminus {

    private final Ui ui;
    private CommandParser parser;
    private String workspace;

    private ModuleManager moduleManager;
    private StorageManager storageManager;
    private Path dataDirectory;

    private static final Path DATA_DIRECTORY = Path.of(System.getProperty("user.dir"), "data");
    private static final String MAIN_JSON = "main.json";

    /**
     * Enters the main entry-point for the terminus.Terminus application.
     */
    public static void main(String[] args) {
        new Terminus().start();
    }
    
    Terminus() {
        this(Ui.getInstance(), MainCommandParser.getInstance(), DATA_DIRECTORY);
    }
    
    Terminus(Ui ui, CommandParser parser, Path dataDirectory) {
        this.ui = ui;
        this.parser = parser;
        this.dataDirectory = dataDirectory;
    }

    /**
     * Starts the program.
     */
    public void start() {
        initialize();
        runCommandsUntilExit();
        exit();
    }

    /**
     * Initializes all the file-related information for TermiNUS.
     */
    void initialize() {
        try {
            TerminusLogger.initializeLogger();
        } catch (IOException e) {
            ui.printSection(
                String.format(Messages.ERROR_MESSAGE_FILE, e.getMessage()),
                "TermiNUS is unable to interact with the logging file.",
                "Any logs generated in this session will not be saved."
            );
        }

        TerminusLogger.info("Starting Terminus...");
        try {
            this.workspace = "";
            this.storageManager = new StorageManager(dataDirectory, MAIN_JSON);
            this.moduleManager = this.storageManager.initialize();
        } catch (InvalidFileException e) {
            TerminusLogger.warning("Data file loading has failed.", e.fillInStackTrace());
            ui.printSection(e.getMessage(),
                "TermiNUS may still run, but your data may not have loaded properly.",
                "Check 'terminus.log' for more information."
            );
        }

        if (this.moduleManager == null) {
            TerminusLogger.warning("File not found.");
            TerminusLogger.warning("Creating new NusModule instance...");
            this.moduleManager = new ModuleManager();
        } else {
            TerminusLogger.info("File loaded.");
        }
        this.ui.printParserBanner(this.parser, this.moduleManager);
        
        TerminusLogger.info("Terminus has started.");
    }

    CommandResult handleUserInput(String input) {
        try {
            Command command = parser.parseCommand(input);
            return command.execute(moduleManager);
        } catch (InvalidCommandException e) {
            TerminusLogger.warning(e.getMessage(), e.fillInStackTrace());
            ui.printSection(e.getMessage());
        } catch (InvalidArgumentException e) {
            TerminusLogger.warning(e.getMessage(), e.fillInStackTrace());
            if (e.getFormat() != null) {
                // Print the format of the command along with the error message to the user, if specified.
                ui.printSection(e.getMessage(), String.format(Messages.INVALID_ARGUMENT_FORMAT_MESSAGE, e.getFormat()));
            } else {
                ui.printSection(e.getMessage());
            }
        }
        return null;
    }
    
    void handleCommandResult(CommandResult commandResult) {
        boolean isWorkspaceCommand = commandResult.getNewCommandParser() != null;
        if (isWorkspaceCommand) {
            parser = commandResult.getNewCommandParser();
            assert parser != null : "parser should not be null";
            workspace = parser.getWorkspace();
            ui.printParserBanner(parser, moduleManager);
        } else {
            ui.printSection(commandResult.getMessage());
        }
    }

    void handleStorage(CommandResult commandResult) {
        try {
            // Pass to Storage to handle the request if there are any changes to file.
            if (commandResult.hasChange()) {
                storageManager.executeCommandResult(moduleManager, commandResult);
            }

            // Update JSON File
            TerminusLogger.info("Saving data into file...");
            this.storageManager.updateMainJsonFile(moduleManager);
        } catch (InvalidFileException ex) {
            this.storageManager.setDisabled(true);
            ui.printSection(ex.getMessage());
            ui.printSection(Messages.ERROR_STORAGE_DISABLE_RESPONSE);
        }
    }

    void runCommandsUntilExit() {
        while (true) {
            try {
                assert workspace != null : "Workspace should always have a value";

                String input = ui.requestCommand(workspace);
                CommandResult commandResult = handleUserInput(input);

                // Error occurred, just continue to next command.
                if (commandResult == null) {
                    continue;
                }
                // Stop the program if it is exit.
                if (commandResult.isExit()) {
                    break;
                }

                handleCommandResult(commandResult);
                handleStorage(commandResult);
            } catch (NoSuchElementException e) {
                if (e.getMessage().equals("No line found")) {
                    ui.printSection("", "Force-quitting detected.", "TermiNUS will attempt to quit.");
                    return;
                }
            } catch (Exception e) {
                ui.printSection("An unexpected error has occurred: ", e.getMessage());
                TerminusLogger.severe(e.getMessage(), e.fillInStackTrace());
            }
        }
    }

    void exit() {
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
