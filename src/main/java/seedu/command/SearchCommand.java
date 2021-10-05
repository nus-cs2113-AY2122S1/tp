package seedu.command;

import seedu.online.NusMods;
import seedu.storage.ModStorage;
import seedu.ui.TextUi;

import java.io.IOException;

public class SearchCommand extends Command {
    private final String searchTerm;
    private final boolean localFlag;

    public SearchCommand(String searchTerm, boolean localFlag) {
        this.searchTerm = searchTerm;
        this.localFlag = localFlag;
    }

    public void execute() {
        if (!localFlag) {
            try {
                NusMods.searchModsOnline(searchTerm);
            } catch (IOException e) {
                TextUi.printNoConnectionMessage();
                ModStorage.searchModsOffline(searchTerm);
            }
        } else {
            ModStorage.searchModsOffline(searchTerm);
        }
    }
}
