package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LinkCommand extends Command {
    private static final Logger logger = Logger.getLogger("AddingDishIngrCommand.execute()");
    private static final Ui ui = new Ui();

    LinkCommand() {
        LoggerManager.setupLogger(logger);
    }

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        logger.log(Level.INFO, "Start of process");
        int dishIndex = DishList.find(parameters.get(0));

        if (dishIndex == -1) {
            logger.log(Level.INFO, "Dish does not exist", dishIndex);
            throw new FoodoramaException(ui.getDishNotExistMsg(parameters.get(0)));
        } else {
            DishList.dishList.get(dishIndex).addPart(parameters.get(1));
            logger.log(Level.INFO, "Successfully added dish ingredient");
        }
        logger.log(Level.INFO, "End of process");
    }
}
