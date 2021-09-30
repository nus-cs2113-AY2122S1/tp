package seedu.command;

import seedu.ui.TextUi;

public class SearchCommand extends Command {

    private final String SEARCHTERM;

    public SearchCommand(String searchTerm) {
        this.SEARCHTERM = searchTerm;
    }

    public void execute() {
        TextUi.searchMods(modList, SEARCHTERM);
    }
}
