package seedu.foodorama.command;

import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;

/**
 * Allows the user to set an expiry date
 * for a specific Ingredient being stored.
 * Format: set ingr expiry [INGR_NAME]
 *
 * @author renzocanare
 */
public class SetIngrExpiryCommand extends Command {
    private static final Ui UI = new Ui();
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_OFFSET = 1;

    /**
     * User command to set expiry date to the Ingredient
     * selected by the index of the Ingredient in the ingredientList or
     * the INGR_NAME in the ingredientList.
     *
     * @param parameters Contains ingrIndex or INGR_NAME of the selected Ingredient
     *                   to set expiry date.
     * @throws FoodoramaException when the INGR_NAME or ingrIndex is not found in the ingredientList
     * @author renzocanare
     */
    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        String ingr = parameters.get(INDEX_ZERO);
        int ingrIndex;
        if (isNumber(ingr)) {
            if (isInteger(ingr)) {
                ingrIndex = Integer.parseInt(ingr) - INDEX_OFFSET;
            } else {
                throw new FoodoramaException(UI.getInvalidIndexMsg());
            }
        } else if (!isNumber(ingr) & ingr.isEmpty()) {
            throw new FoodoramaException(UI.getIngrIndexMissingMsg());
        } else {
            ingrIndex = IngredientList.find(ingr);
        }
        // If user input is not a number and index cannot be found
        if (ingrIndex == -1 && !isNumber(ingr)) {
            throw new FoodoramaException(UI.getIngrNotExistMsg());
        // If ingr index is out of bounds
        } else if (ingrIndex < 0 || ingrIndex >= IngredientList.ingredientList.size()) {
            throw new FoodoramaException(UI.getIngrIndexExceedSizeMsg());
        } else {
            assert (ingrIndex != -1) : "The ingrIndex cannot be -1";
            IngredientList.addExpiry(ingrIndex);
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

