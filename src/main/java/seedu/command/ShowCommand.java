package seedu.command;

import seedu.online.NusMods;
import seedu.storage.ModStorage;
import seedu.ui.TextUi;

import java.io.IOException;

public class ShowCommand extends Command {

    private final String searchTerm;

    public ShowCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public void execute() {
        String moduleCode = searchTerm.toUpperCase();
        try {
            NusMods.showModOnline(moduleCode);
        } catch (IOException e) {
            TextUi.printNoConnectionMessage();
            ModStorage.showModOffline(moduleCode);
        }
    }
}
