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

    public void execute() {
        try {
            logger.log(Level.INFO, "Updating local data");
            NusMods.update();
            logger.log(Level.INFO, "Update complete");
        } catch (IOException | ModStorage.FileErrorException e) {
            logger.log(Level.WARNING, "UPDATE ERROR");
            e.printStackTrace();
        }
    }
}
