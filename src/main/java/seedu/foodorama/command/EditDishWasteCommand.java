package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allows the user to edit the wastage of a dish.
 * Format: edit dish waste [DISH_NAME]/[INDEX]
 *
 * @author renzocanare
 */
public class EditDishWasteCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("EditDishCommand");
    private static final Ui UI = new Ui();
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_OFFSET = 1;

    EditDishWasteCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    /**
     * User command to edit the wastage of a dish in the dish list.
     * Parameters can either accept the [DISH_NAME] of the dish to be edited
     * or the [INDEX] of the dish in the dish list.
     * The method checks if the [DISH_INDEX] is an integer and is out of bounds of the size of the dish list
     * or if the [DISH_NAME] doesn't exist in the list and throws an exception.
     *
     * <p>If no exceptions are thrown, the user is prompted to provide the new wastage for the dish.</p>
     *
     * @param parameters contains the [DISH_NAME] or [DISH_INDEX] of the ingredient to edit wastage of
     * @throws FoodoramaException if [DISH_NAME] doesn't exist in the dish list or if [INDEX] is not an integer,
     *      [INDEX] is an integer that's out of bounds
     * @author renzocanare
     */
    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        LOGGER.log(Level.INFO, "Start of process");
        int dishIndex;
        if (isNumber(parameters.get(INDEX_ZERO))) {
            if (isInteger(parameters.get(INDEX_ZERO))) {
                dishIndex = Integer.parseInt(parameters.get(INDEX_ZERO)) - INDEX_OFFSET;
                LOGGER.log(Level.INFO, "Parameter is Integer " + dishIndex);
            } else {
                throw new FoodoramaException(UI.getInvalidIndexMsg());
            }
        } else {
            String dishName = String.join(" ", parameters);
            if (dishName.isBlank()) {
                LOGGER.log(Level.INFO, "Parameter is Empty");
                throw new FoodoramaException(UI.getDishIndexMissingMsg());
            } else {
                dishIndex = DishList.find(dishName);
                LOGGER.log(Level.INFO, "Parameter is String '" + dishName + "'");
            }
        }
        DishList.editWastage(dishIndex);
        LOGGER.log(Level.INFO, "Dish Name edited.");
        LOGGER.log(Level.INFO, "End of process.");
    }

    /**
     * Checks if given string can be converted into a number.
     * @param numberString string to be checked
     * @return true if string can be converted into a double, false otherwise
     *
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
     * Checks if given string can be converted into an integer.
     * @param numberString string to be checked
     * @return true if string can be converted into an integer, false otherwise
     *
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

