package seedu.command;

import seedu.module.Module;
import seedu.online.NusMods;
import seedu.ui.TextUi;

import java.util.logging.Logger;

public class ShowCommand extends Command {
    private static Logger logger = Logger.getLogger("");

    private final String searchTerm;

    public ShowCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Shows full mod information as fetched from NUSMods API. If it is unable to reach the API, it will
     * print an error message and attempt to fetch information from local data.
     */
    public void execute() {
        String moduleCode = searchTerm.toUpperCase();
        Module module = NusMods.fetchMod(moduleCode);
        if (module != null) {
            TextUi.printModFullDescription(module);
        } else {
            TextUi.printNotFoundMessage();
        }
    }
}
