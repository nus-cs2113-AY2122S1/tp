package seedu.command;

import seedu.ui.TextUi;

public class ShowCommand extends Command {

    private final String searchTerm;

    public ShowCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public void execute() {
        TextUi.showMod(modList, searchTerm);
    }
}
