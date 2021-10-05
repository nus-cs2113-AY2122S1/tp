package terminus;

import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.parser.CommandParser;
import terminus.parser.MainCommandParser;
import terminus.ui.Ui;

public class Terminus {
    
    private Ui ui;
    private CommandParser parser;
    private String workspace;
    
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
        this.ui.printBanner();
        this.ui.printParserBanner(this.parser);
    }
    
    private void runCommandsUntilExit() {
        while (true) {
            String input = ui.requestCommand(workspace);
            
            Command currentCommand = null;
            try {
                currentCommand = parser.parseCommand(input);
                CommandResult result = currentCommand.execute(ui,null);
                if (result.isOk() && result.isExit()) {
                    break;
                } else if (result.isOk() && result.getAdditionalData() != null) {
                    parser = result.getAdditionalData();
                    workspace = parser.getWorkspace();
                    ui.printParserBanner(parser);
                } else if (!result.isOk()) {
                    ui.printSection(result.getErrorMessage());
                }
            } catch (InvalidCommandException e) {
                ui.printSection(e.getMessage());
            } catch (InvalidArgumentException e) {
                ui.printSection(e.getMessage());
            }
            
        }
    } 
    
    private void exit() {
        this.ui.printExitMessage();
    }
    
}
