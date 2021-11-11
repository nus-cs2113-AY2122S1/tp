package seedu.foodorama.command;

import seedu.foodorama.Dish;
import seedu.foodorama.DishList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;

/**
 * Allows the user to set a limit to the amount of weight
 * of a dish being wasted.
 * Format: set dish limit [DISH_NAME]
 *
 * @author Dniv-ra
 */
public class SetDishLimitCommand extends Command {
    private static final Ui UI = new Ui();
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_OFFSET = 1;

    /**
     * User command to set limit to the wasted weight of the dish
     * selected by the index of the DIsh in the dishList or
     * the DISH_NAME in the dishList.
     *
     * @param parameters contains either the index of the Dish
     *                   the user intends to set wastage weight limit for or
     *                   the name of the Dish the user intends to set wastage weight limit for.
     * @throws FoodoramaException when invalid dish index is being input, user enters an empty dish index
     *                            or a dish index that is not a number, or when the dish index is not
     *                            found in the dish list.
     * @author Dniv-ra
     */
    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        String dish = parameters.get(INDEX_ZERO);
        int dishIndex;
        if (isNumber(dish)) {
            if (isInteger(dish)) {
                dishIndex = Integer.parseInt(dish) - INDEX_OFFSET;
            } else {
                throw new FoodoramaException(UI.getInvalidIndexMsg());
            }
        } else if (!isNumber(dish) && dish.isEmpty()) {
            throw new FoodoramaException(UI.getDishNameMissingMsg());
        } else {
            dishIndex = DishList.find(dish);
        }
        // If user input is not a number and index cannot be found
        if (dishIndex == -1 && !isNumber(dish)) {
            throw new FoodoramaException(UI.getDishNotExistMsg(parameters.get(INDEX_ZERO)));
        // If dish index is out of bounds
        } else if (dishIndex < 0 || dishIndex >= DishList.dishList.size()) {
            throw new FoodoramaException(UI.getDishIndexExceedSizeMsg());
        } else {
            assert (dishIndex != -1) : "The dishIndex cannot be -1";
            try {
                Dish currentDish = DishList.dishList.get(dishIndex);
                currentDish.setLimitValue();
            } catch (FoodoramaException e) {
                throw new FoodoramaException(e.getMessage());
            }
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
