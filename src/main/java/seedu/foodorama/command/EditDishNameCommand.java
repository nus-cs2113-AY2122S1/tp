package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allows the user to edit the name of a dish.
 * Format: edit dish name [DISH_NAME]/[INDEX]
 *
 * @author Rakesh12000
 */
public class EditDishNameCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("EditDishCommand");
    private static final Ui UI = new Ui();

    EditDishNameCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    /**
     * User command to edit the name of a dish in the dish list.
     * Parameters can either accept the [DISH_NAME] of the dish to be edited
     * or the [INDEX] of the dish in the dish list.
     * The method checks if the [DISH_INDEX] is an integer and is out of bounds of the size of the dish list
     * or if the [DISH_NAME] doesn't exist in the list and throws an exception.
     *
     * <p>If no exceptions are thrown, the user is prompted to provide the new name for the dish.</p>
     *
     * @param parameters contains the [DISH_NAME] or [DISH_INDEX] of the dish to edit name of
     * @throws FoodoramaException if [DISH_NAME] doesn't exist in the dish list or if [INDEX] is not an integer,
     *      [INDEX] is an integer that's out of bounds
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
            try {
                DishList.editName(dishIndex);
            } catch (FoodoramaException e) {
                throw new FoodoramaException(e.getMessage());
            }
        }
        LOGGER.log(Level.INFO, "Dish Name edited.");
        LOGGER.log(Level.INFO, "End of process.");
    }

    /**
     * Checks if given string can be converted into a number.
     * @param numberString string to be checked
     * @return true if string can be converted into a double, false otherwise
     *
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
     * Checks if given string can be converted into an integer.
     * @param numberString string to be checked
     * @return true if string can be converted into an integer, false otherwise
     *
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

