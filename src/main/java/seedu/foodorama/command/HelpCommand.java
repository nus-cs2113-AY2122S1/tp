package seedu.foodorama.command;

import seedu.foodorama.Ui;

import java.util.ArrayList;

/**
 * Shows the user a list of all available commands with the correct formats.
 * Format: help
 *
 * @author Rakesh12000
 */
public class HelpCommand extends Command {
    private static final Ui UI = new Ui();

    /**
     * User command to display the help message.
     * @param parameters user input parameters
     *
     * @author Rakesh12000
     */
    @Override
    public void execute(ArrayList<String> parameters) {
        UI.printHelpMsg();
    }
}
