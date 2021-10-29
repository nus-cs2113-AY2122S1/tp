package seedu.command;

import seedu.command.flags.SearchFlags;
import seedu.online.NusMods;
import seedu.storage.ModStorage;
import seedu.ui.TextUi;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchCommand extends Command {

    public static final String commandSyntax = "search <MODULE_CODE>";
    public static final String commandAction =
            "Searches for modules that match the search expression";
    private static Logger logger = Logger.getLogger("");
    private final String searchTerm;
    private final SearchFlags searchFlags;

    public SearchCommand(String searchTerm, SearchFlags searchFlags) {
        this.searchTerm = searchTerm;
        this.searchFlags = searchFlags;
    }

    /**
     * Searches for mods. Defaults to online search unless quick flag is specified, and or if it is
     * unable to reach the NUSMods API. Prints all mods that matches the search term and all specified
     * flags, and the total count of matching mods. Also displays a warning message for offline searches.
     */
    public void execute() {
        boolean isQuickSearch = searchFlags.getHasQuickFlag();
        if (!isQuickSearch) {
            try {
                NusMods.searchModsOnline(searchTerm, searchFlags);
                logger.log(Level.INFO, "Online search done");
            } catch (IOException e) {
                TextUi.printNoConnectionMessage();
                logger.log(Level.INFO, "Unable to retrieve data from NUSMods, searching offline");
                ModStorage.searchModsOffline(searchTerm, searchFlags);
                logger.log(Level.INFO, "Offline search done");
            }
        } else {
            ModStorage.searchModsOffline(searchTerm, searchFlags);
            logger.log(Level.INFO, "Manual offline search done");
        }
    }
}
