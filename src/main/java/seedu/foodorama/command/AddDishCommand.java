package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allows the user to add dishes to the dish list.
 * Format: add dish [DISH_NAME]
 *
 * @author Dniv-ra
 */
public class AddDishCommand extends Command {
    private static final String NUMBER_REGEX = "^[-\\d\\s.]+$";
    private static Logger LOGGER = Logger.getLogger("AddDishCommand.execute()");
    private static final Ui UI = new Ui();
    private static final int INDEX_ZERO = 0;

    AddDishCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    /**
     * User command to add a dish to the dish list.
     * The DISH_NAME to be added is stored in parameters. The method checks if the input
     * is empty, if the dish already exists in the list or if the input is an number only, and throws an exception.
     *
     * <p>If no exceptions are thrown, DISH_NAME in parameters is added to the dish list.</p>
     *
     * @param parameters contains the user input for DISH_NAME
     * @throws FoodoramaException when the DISH_NAME is missing/blank, already exists in the list or is a number
     * @author Dniv-ra
     */
    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        String dish = parameters.get(INDEX_ZERO);
        if (dish.isBlank()) {
            LOGGER.log(Level.INFO, "Dish Name is Empty");
            throw new FoodoramaException(UI.getDishNameMissingMsg());
        // If can find dish (value is not negative), dish already exists
        } else if (DishList.find(dish) >= 0) {
            LOGGER.log(Level.INFO, "Dish already exists ", dish);
            throw new FoodoramaException(UI.getDishExistsMsg(dish));
        } else if (isNumber(dish)) {
            LOGGER.log(Level.INFO, "Parameter is Integer ", dish);
            throw new FoodoramaException(UI.getInvalidDishName());
        } else {
            DishList.add(dish);
            LOGGER.log(Level.INFO, "Successfully added Dish");
        }
    }

    /**
     * Checks if the parameter numberString is a number.
     *
     * @param numberString the String to check if it is a number
     * @return true is the String is a number, false if it is not a number
     * @author Dniv-ra
     */
    public boolean isNumber(String numberString) {
        if (numberString.matches(NUMBER_REGEX)) {
            return true;
        } else {
            return false;
        }
    }

}
