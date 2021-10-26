package seedu.command;

import seedu.online.NusMods;
import seedu.storage.ModStorage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateCommand extends Command {

    private static Logger logger = Logger.getLogger("");

    public UpdateCommand() {
    }

    /**
     * Fetches full information for all mods from NUSMods API and saves them into local storage.
     */
    public void execute() {
        try {
            logger.log(Level.INFO, "Updating local data");
            NusMods.updateSequence();
            logger.log(Level.INFO, "Update complete");
        } catch (IOException e) {
            logger.log(Level.WARNING, "UPDATE ERROR");
            e.printStackTrace();
        }
    }
}
