package seedu.duke.command;

import seedu.duke.DishList;
import seedu.duke.Ui;
import seedu.duke.exceptions.FoodoramaException;
import seedu.duke.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddDishIngrCommand extends Command {
    private static Logger logger = Logger.getLogger("AddingDishIngrCommand.execute()");

    AddDishIngrCommand() {
        LoggerManager.setupLogger(logger);
    }

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        Ui ui = new Ui();
        logger.log(Level.INFO, "Start of process");
        int dishIndex = DishList.find(parameters.get(0));
        if (dishIndex == -1) {
            logger.log(Level.INFO, "Dish does not exist", dishIndex);
            throw new FoodoramaException("The dish " + parameters.get(0) + " does not exist");
        } else {
            DishList.dishList.get(dishIndex).addConstituent(parameters.get(1));
            logger.log(Level.INFO, "Successfully added dish ingredient");
        }
        logger.log(Level.INFO, "End of process");
    }
}
