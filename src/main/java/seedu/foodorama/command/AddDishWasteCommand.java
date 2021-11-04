package seedu.foodorama.command;


import seedu.foodorama.Dish;
import seedu.foodorama.DishList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddDishWasteCommand extends Command {
    private static Logger logger = Logger.getLogger("AddDishWasteCommand.execute()");
    private static final Ui UI = new Ui();

    AddDishWasteCommand() {
        LoggerManager.setupLogger(logger);
    }

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        logger.log(Level.INFO, "Start of process");
        String dish = parameters.get(0);
        int dishIndex;
        if (isNumber(dish)) {
            if(isInteger(dish)) {
                dishIndex = Integer.parseInt(dish) - 1;
            } else {
                throw new FoodoramaException(UI.getInvalidIndexMsg());
            }
        } else {
            dishIndex = DishList.find(dish);
        }
        //Input validation for dishIndex
        //Possible edge case (user give 0 as input)
        if (dishIndex == -1) {
            logger.log(Level.INFO, "Dish does not exist");
            throw new FoodoramaException(UI.getDishNotExistMsg(parameters.get(0)));
        } else if (dishIndex < 0 || dishIndex >= DishList.dishList.size()) {
            logger.log(Level.INFO, "Dish index is wrong");
            throw new FoodoramaException(UI.getDishIndexExceedSizeMsg());
        } else {
            assert (dishIndex != -1) : "The dishIndex cannot be -1";
            try {
                Dish currentDish = DishList.dishList.get(dishIndex);
                currentDish.addWaste();
                logger.log(Level.INFO, "Successfully recorded Dish waste of "
                        + dish);
            } catch (FoodoramaException e) {
                throw new FoodoramaException(e.getMessage());
            }
        }
        logger.log(Level.INFO, "End of process");
    }

    public boolean isNumber(String numberString) {
        try {
            double number = Double.parseDouble(numberString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isInteger(String numberString) {
        if(isNumber(numberString)) {
            double number = Double.parseDouble(numberString);
            return Math.rint(number) - number == 0;
        } else {
            return false;
        }
    }

}
