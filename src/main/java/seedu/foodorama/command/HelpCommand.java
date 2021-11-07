package seedu.foodorama.command;

import seedu.foodorama.Ui;

import java.util.ArrayList;

public class HelpCommand extends Command {
    private static final Ui UI = new Ui();

    /**
     * Calls the help command in Ui
     * @param parameters user input parameters
     *
     * @author Rakesh12000
     */
    @Override
    public void execute(ArrayList<String> parameters) {
        UI.printHelpMsg();
    }
}
