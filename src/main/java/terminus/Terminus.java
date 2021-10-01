package terminus;

import terminus.ui.Ui;

public class Terminus {
    
    private Ui ui;
    
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
        this.ui.printBanner();
    }
    
    private void runCommandsUntilExit() {
        while (true) {
            String input = ui.requestCommand("");
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
