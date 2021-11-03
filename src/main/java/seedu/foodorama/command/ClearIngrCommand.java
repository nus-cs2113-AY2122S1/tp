package seedu.foodorama.command;

import seedu.foodorama.IngredientList;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClearIngrCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("ClearIngrCommand.execute()");

    ClearIngrCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    @Override
    public void execute(ArrayList<String> parameters) {
        LOGGER.log(Level.INFO, "Start of process");

        IngredientList.clearList();
        LOGGER.log(Level.INFO, "Successfully cleared ingredientList");

        LOGGER.log(Level.INFO, "End of process");
    }
}
