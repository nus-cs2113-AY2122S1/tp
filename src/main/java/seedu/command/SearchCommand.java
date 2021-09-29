package seedu.command;

import seedu.ui.TextUi;

public class SearchCommand extends Command {

    private final String searchTerm;

    public SearchCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public void execute() {
        TextUi.searchMods(modList, searchTerm);
    }
}
