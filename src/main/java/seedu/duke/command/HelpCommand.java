package seedu.duke.command;

import seedu.duke.Ui;

import java.util.ArrayList;

public class HelpCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        Ui ui = new Ui();
        ui.printHelpMsg();
    }
}
