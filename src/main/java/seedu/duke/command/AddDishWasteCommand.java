package seedu.duke.command;


import seedu.duke.Dish;
import seedu.duke.DishList;
import seedu.duke.Ui;
import seedu.duke.exceptions.FoodoramaException;
import seedu.duke.logger.LoggerManager;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddDishWasteCommand extends Command {
    private static Logger logger = Logger.getLogger("AddDishWasteCommand.execute()");

    AddDishWasteCommand() {
        LoggerManager.setupLogger(logger);
    }

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        Ui ui = new Ui();
        logger.log(Level.INFO, "Start of process");
        String dish = String.join(" ", parameters);
        int dishIndex = DishList.find(dish);
        System.out.println(ui.getLineDivider());
        if (dishIndex == -1) {
            logger.log(Level.INFO, "Dish does not exist", dishIndex);
            throw new FoodoramaException("The dish " + parameters.get(0) + " does not exist");
        } else {
            assert (dishIndex != -1) : "The dishIndex cannot be -1";
            try {
                Dish currentDish = DishList.dishList.get(dishIndex);
                currentDish.addWaste();
                logger.log(Level.INFO, "Successfully recorded Dish waste of "
                        + dish);
            } catch (FoodoramaException e) {
                //System.out.println(ui.getInvalidParamMsg());
                //System.out.println(ui.getLineDivider());
                throw new FoodoramaException(e.getMessage());
            }
        }
        System.out.println(ui.getLineDivider());
        logger.log(Level.INFO, "End of process");
    }

}
