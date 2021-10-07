package seedu.command;

import seedu.command.flags.SearchFlags;
import seedu.online.NusMods;
import seedu.storage.ModStorage;
import seedu.ui.TextUi;

import java.io.IOException;

public class SearchCommand extends Command {
    private final String searchTerm;
    private final SearchFlags searchFlags;

    public SearchCommand(String searchTerm, SearchFlags searchFlags) {
        this.searchTerm = searchTerm;
        this.searchFlags = searchFlags;
    }

    public void execute() {
        boolean isQuickSearch = searchFlags.getHasQuickFlag();
        if (!isQuickSearch) {
            try {
                NusMods.searchModsOnline(searchTerm, searchFlags);
            } catch (IOException e) {
                TextUi.printNoConnectionMessage();
                ModStorage.searchModsOffline(searchTerm, searchFlags);
            }
        } else {
            ModStorage.searchModsOffline(searchTerm, searchFlags);
        }
    }
}
