package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allows the user to clear the list of dishes.
 * Format: clear dish
 *
 * @author Rakesh12000
 */
public class ClearDishCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("ClearDishCommand.execute()");

    ClearDishCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    /**
     * User command to clear the list of dishes.
     * This method calls .clear() methods in DishList, clearing the list of dishes.
     *
     * @author Rakesh12000
     */
    @Override
    public void execute(ArrayList<String> parameters) {
        LOGGER.log(Level.INFO, "Start of process");

        DishList.clearList();
        LOGGER.log(Level.INFO, "Successfully cleared dishList");

        LOGGER.log(Level.INFO, "End of process");
    }
}
