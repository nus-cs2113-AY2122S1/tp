package seedu.command;

import seedu.online.NusMods;
import seedu.storage.ModStorage;
import seedu.ui.TextUi;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowCommand extends Command {
    private static Logger logger = Logger.getLogger("");

    private final String searchTerm;

    public ShowCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public void execute() {
        String moduleCode = searchTerm.toUpperCase();
        try {
            NusMods.showModOnline(moduleCode);
            logger.log(Level.INFO, "Online search done");
        } catch (IOException e) {
            TextUi.printNoConnectionMessage();
            logger.log(Level.INFO, "Unable to retrieve data from NUSMods, searching offline");
            ModStorage.showModOffline(moduleCode);
            logger.log(Level.INFO, "Offline search done");
        }
    }
}
