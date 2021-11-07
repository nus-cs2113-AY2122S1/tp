package seedu.foodorama.command;

import seedu.foodorama.IngredientList;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allows the user to clear the list of ingredients.
 * Format: clear ingr
 *
 * @author Rakesh12000
 */
public class ClearIngrCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("ClearIngrCommand.execute()");

    ClearIngrCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    /**
     * User command to clear the list of ingredients.
     * This method calls .clear() methods in IngredientList, clearing the list of ingredients.
     *
     * @author Rakesh12000
     */
    @Override
    public void execute(ArrayList<String> parameters) {
        LOGGER.log(Level.INFO, "Start of process");

        IngredientList.clearList();
        LOGGER.log(Level.INFO, "Successfully cleared ingredientList");

        LOGGER.log(Level.INFO, "End of process");
    }
}
