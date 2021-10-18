package seedu.duke.command;

import seedu.duke.DishList;
import seedu.duke.Ui;
import seedu.duke.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClearDishCommand extends Command {
    private static final Logger logger = Logger.getLogger("ClearDishCommand.execute()");

    ClearDishCommand() {
        LoggerManager.setupLogger(logger);
    }

    @Override
    public void execute(ArrayList<String> parameters) {
        Ui ui = new Ui();
        logger.log(Level.INFO, "Start of process");
        DishList.clearList();
        logger.log(Level.INFO, "Successfully cleared dishList");
        System.out.println(ui.getDishListCleared());
        logger.log(Level.INFO, "End of process");
    }
}
