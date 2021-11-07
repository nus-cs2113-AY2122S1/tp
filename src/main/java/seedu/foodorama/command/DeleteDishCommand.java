package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allows the user to remove a particular dish.
 * Format: del dish [DISH_NAME]/[INDEX]
 *
 * @author Rakesh12000
 */
public class DeleteDishCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("EditDishCommand");
    private static final Ui UI = new Ui();

    DeleteDishCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    /**
     * User command to remove a particular dish.
     * This method calls .delete(dishIndex) method in DishList, deleting the corresponding dish.
     *
     * @author Rakesh12000
     */
    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        LOGGER.log(Level.INFO, "Start of process");
        String dish = parameters.get(0);
        int dishIndex;
        if (isNumber(dish)) {
            if (isInteger(dish)) {
                dishIndex = Integer.parseInt(parameters.get(0)) - 1;
                LOGGER.log(Level.INFO, "Parameter is Integer " + dishIndex);
            } else {
                throw new FoodoramaException(UI.getInvalidIndexMsg());
            }
        } else if (!isNumber(dish) & dish.isEmpty()) {
            LOGGER.log(Level.INFO, "Parameter is Empty");
            throw new FoodoramaException(UI.getDishIndexMissingMsg());
        } else {
            dishIndex = DishList.find(dish);
        }
        if (!isNumber(dish) & dishIndex == -1) {
            LOGGER.log(Level.INFO, "Dish does not exist");
            throw new FoodoramaException(UI.getDishNotExistMsg(dish));
        } else if (dishIndex < 0 || dishIndex >= DishList.dishList.size()) {
            LOGGER.log(Level.INFO, "Dish Index is not within Dish List Range");
            throw new FoodoramaException(UI.getDishIndexExceedSizeMsg());
        } else {
            assert (dishIndex != -1) : "The dishIndex cannot be -1";
            DishList.delete(dishIndex);
        }
        LOGGER.log(Level.INFO, "Dish Name edited.");
        LOGGER.log(Level.INFO, "End of process.");
    }


    /**
     * Checks if the parameter numberString is a number.
     *
     * @param numberString the String to check if it is a number
     * @return true if the String is a number, false if it is not a number
     * @author Dniv-ra
     */
    public boolean isNumber(String numberString) {
        try {
            double number = Double.parseDouble(numberString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the parameter numberString is an integer.
     *
     * @param numberString the String to check if it is an integer
     * @return true if the String is an integer, false if it is not an integer
     * @author Dniv-ra
     */
    public boolean isInteger(String numberString) {
        if (isNumber(numberString)) {
            double number = Double.parseDouble(numberString);
            return Math.rint(number) - number == 0;
        } else {
            return false;
        }
    }
}
