package seedu.foodorama.command;


import seedu.foodorama.Dish;
import seedu.foodorama.DishList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allows the user to add wastage to a particular dish in the dish list.
 * Format: add dish waste [DISH_NAME] or add dish waste [DISH_INDEX]
 *
 * @author Dniv-ra
 */
public class AddDishWasteCommand extends Command {
    private static Logger logger = Logger.getLogger("AddDishWasteCommand.execute()");
    private static final Ui UI = new Ui();
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_OFFSET = 1;

    AddDishWasteCommand() {
        LoggerManager.setupLogger(logger);
    }

    /**
     * User command to add wastage to a dish in the dish list.
     * The [DISH_NAME]/[DISH_INDEX] to add wastage to is stored in parameters.
     * The method checks if either [DISH_NAME] or [DISH_INDEX] is input by the user, and finds the corresponding
     * dish in the dish list. The method throws an exception if the dish does not exist or the dish index is not within
     * the boundaries of the dish list.
     *
     * <p>If no exceptions are thrown, the user will be prompted to add wastage weight for [DISH_NAME] or [DISH_INDEX].
     * </p>
     *
     * @param parameters contains the [DISH_NAME] or [DISH_INDEX] to add wastage to
     * @throws FoodoramaException when the dish to add weight to does not exist or the dish index is not within
     *      boundaries of the dish list
     * @author Dniv-ra
     */
    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        logger.log(Level.INFO, "Start of process");
        String dish = parameters.get(INDEX_ZERO);
        int dishIndex;
        if (isNumber(dish)) {
            if (isInteger(dish)) {
                dishIndex = Integer.parseInt(dish) - INDEX_OFFSET;
            } else {
                throw new FoodoramaException(UI.getInvalidIndexMsg());
            }
        } else {
            dishIndex = DishList.find(dish);
        }
        //Input validation for dishIndex
        //Possible edge case (user give 0 as input)
        if (dish.isBlank()) {
            throw new FoodoramaException(UI.getDishNameMissingMsg());
        // If dish cannot be found and is not a number
        } else if (dishIndex == -1 && !isNumber(dish)) {
            logger.log(Level.INFO, "Dish does not exist");
            throw new FoodoramaException(UI.getDishNotExistMsg(parameters.get(INDEX_ZERO)));
        // If dish index is out of bounds
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

    /**
     * Checks if the parameter numberString is a number.
     *
     * @param numberString the String to check if it is a number
     * @return true is the String is a number, false if it is not a number
     * @author Rakesh12000
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
     * @return true is the String is an integer, false if it is not an integer
     * @author Dniv-ra
     */
    public boolean isInteger(String numberString) {
        if (isNumber(numberString)) {
            double number = Double.parseDouble(numberString);
            // Check if integer when rounded number - number == 0
            if (Math.rint(number) - number == 0) {
                return (number < Integer.MAX_VALUE && number > Integer.MIN_VALUE);
            }
        }
        return false;
    }

}
