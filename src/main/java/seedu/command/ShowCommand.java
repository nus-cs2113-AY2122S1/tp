package seedu.command;

import seedu.ui.TextUi;

public class ShowCommand extends Command {

    private final String SEARCHTERM;

    public ShowCommand(String searchTerm) {
        this.SEARCHTERM = searchTerm;
    }

    public void execute() {
        TextUi.showMod(modList, SEARCHTERM);
    }
}
