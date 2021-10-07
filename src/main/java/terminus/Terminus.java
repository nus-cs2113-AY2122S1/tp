package terminus;

import java.io.IOException;
import java.nio.file.Path;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.exception.InvalidTimeFormatException;
import terminus.module.NusModule;
import terminus.parser.CommandParser;
import terminus.parser.MainCommandParser;
import terminus.storage.ModuleStorage;
import terminus.ui.Ui;

public class Terminus {

    private Ui ui;
    private CommandParser parser;
    private String workspace;

    private ModuleStorage moduleStorage;
    private NusModule nusModule;
    
    private static final String INVALID_ARGUMENT_FORMAT_MESSAGE = "Format: %s";
    private static final Path DATA_DIRECTORY = Path.of(System.getProperty("user.dir"), "data");
    private static final String MAIN_JSON = "main.json";

    /**
     * Main entry-point for the terminus.Terminus application.
     */
    public static void main(String[] args) {
        new Terminus().run();
    }

    /**
     * Start the program.
     */
    public void run() {
        start();
        runCommandsUntilExit();
        exit();
    }

    private void start() {
        this.ui = new Ui();
        this.parser = MainCommandParser.getInstance();
        this.workspace = "";
        this.moduleStorage = new ModuleStorage(DATA_DIRECTORY.resolve(MAIN_JSON));
        try {
            this.nusModule = moduleStorage.loadFile();
            if (this.nusModule == null) {
                this.nusModule = new NusModule();
            }
            this.moduleStorage.saveFile(nusModule);
            this.ui.printParserBanner(this.parser, this.nusModule);
        } catch (IOException e) {
            ui.printSection(
                "Unable to save/load file: " + DATA_DIRECTORY.resolve(MAIN_JSON),
                "TermiNUS may still run, but your changes may not be saved."
            );
        }
    }

    private void runCommandsUntilExit() {
        while (true) {
            String input = ui.requestCommand(workspace);

            Command currentCommand = null;
            try {
                currentCommand = parser.parseCommand(input);
                CommandResult result = currentCommand.execute(ui, nusModule);
                if (result.isOk()) {
                    this.moduleStorage.saveFile(nusModule);
                }
                
                boolean isExitCommand = result.isOk() && result.isExit();
                boolean isWorkspaceCommand = result.isOk() && result.getAdditionalData() != null;
                if (isExitCommand) {
                    break;
                } else if (isWorkspaceCommand) {
                    parser = result.getAdditionalData();
                    workspace = parser.getWorkspace();
                    ui.printParserBanner(parser, nusModule);
                } else if (!result.isOk()) {
                    ui.printSection(result.getErrorMessage());
                }
            } catch (InvalidCommandException | InvalidTimeFormatException e) {
                ui.printSection(e.getMessage());
            } catch (InvalidArgumentException e) {
                if (e.getFormat() != null) {
                    ui.printSection(e.getMessage(), String.format(INVALID_ARGUMENT_FORMAT_MESSAGE, e.getFormat()));   
                } else {
                    ui.printSection(e.getMessage());
                }
            } catch (IOException e) {
                ui.printSection(
                    "Unable to save/load file: " + DATA_DIRECTORY.resolve(MAIN_JSON),
                    "TermiNUS may still run, but your changes may not be saved."
                );
            }
        }
    }

    private void exit() {
        this.ui.printExitMessage();
    }
}
