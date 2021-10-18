package seedu.duke.command;

import seedu.duke.IngredientList;
import seedu.duke.Ui;
import seedu.duke.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClearIngrCommand extends Command {
    private static final Logger logger = Logger.getLogger("ClearIngrCommand.execute()");

    ClearIngrCommand() {
        LoggerManager.setupLogger(logger);
    }

    @Override
    public void execute(ArrayList<String> parameters) {
        Ui ui = new Ui();
        logger.log(Level.INFO, "Start of process");
        IngredientList.clearList();
        logger.log(Level.INFO, "Successfully cleared ingredientList");
        System.out.println(ui.getIngrListCleared());
        logger.log(Level.INFO, "End of process");
    }
}
