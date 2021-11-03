package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClearDishCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("ClearDishCommand.execute()");

    ClearDishCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        LOGGER.log(Level.INFO, "Start of process");

        DishList.clearList();
        LOGGER.log(Level.INFO, "Successfully cleared dishList");

        LOGGER.log(Level.INFO, "End of process");
    }
}
