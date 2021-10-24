package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClearDishCommand extends Command {
    private static final Logger logger = Logger.getLogger("ClearDishCommand.execute()");

    ClearDishCommand() {
        LoggerManager.setupLogger(logger);
    }

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        logger.log(Level.INFO, "Start of process");

        DishList.clearList();
        logger.log(Level.INFO, "Successfully cleared dishList");

        logger.log(Level.INFO, "End of process");
    }
}
