package seedu.duke.command;

import seedu.duke.DishList;
import seedu.duke.IngredientList;
import seedu.duke.Ui;
import seedu.duke.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClearAllCommand extends Command {
    private static final Logger logger = Logger.getLogger("ClearAllCommand.execute()");

    ClearAllCommand() {
        LoggerManager.setupLogger(logger);
    }

    @Override
    public void execute(ArrayList<String> parameters) {
        Ui ui = new Ui();
        logger.log(Level.INFO, "Start of process");
        DishList.clearList();
        IngredientList.clearList();
        logger.log(Level.INFO, "Successfully cleared both lists");
        System.out.println(ui.getAllCleared());
        logger.log(Level.INFO, "End of process");
    }
}
