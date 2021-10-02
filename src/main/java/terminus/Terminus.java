package terminus;

import terminus.command.Command;
import terminus.command.CommandResult;
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
    }
    
    private void runCommandsUntilExit() {
        while (true) {
            String input = ui.requestCommand(workspace);
            Command currentCommand = parser.parseCommand(input);
            if (currentCommand == null) {
                ui.printSection("Command not found");
                continue;
            }
            CommandResult result = currentCommand.execute(ui,null);
            if (result.isOk() && result.isExit()) {
                break;
            } else if (result.isOk() && result.getAdditionalData() != null) {
                this.parser = result.getAdditionalData();
                this.workspace = this.parser.getWorkspace();
            } else if( !result.isOk()) {
                ui.printSection(result.getErrorMessage());
            }
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            ui.printSection(input);
        }
    } 
    
    private void exit() {
        this.ui.printExitMessage();
    }
    
}
