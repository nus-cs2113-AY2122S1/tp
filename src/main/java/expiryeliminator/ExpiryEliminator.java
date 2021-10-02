package expiryeliminator;

import expiryeliminator.commands.Command;
import expiryeliminator.parser.Parser;
import expiryeliminator.ui.Ui;

public class ExpiryEliminator {
    private static Parser parser;
    private static Ui ui;

    /**
     * Initialises the ExpiryEliminator application.
     */
    public ExpiryEliminator() {
        ui = new Ui();
    }

    //@@author bernardboey-reused
    // Reused from https://github.com/bernardboey/ip/blob/master/src/main/java/duke/ui/Ui.java
    // with minor modifications

    /**
     * Runs the program.
     */
    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            final String userInput = ui.getUserInput();
            final Command command = Parser.parseCommand(userInput);
            isExit = command.isExit();
            final String feedback = command.execute();
            ui.showToUser(feedback);
        }
    }
    //@author

    /**
     * Main entry-point for the ExpiryEliminator application.
     */
    public static void main(String[] args) {
        new ExpiryEliminator().run();
    }
}
