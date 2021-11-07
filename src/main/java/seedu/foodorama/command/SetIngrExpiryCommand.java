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
        String ingr = parameters.get(0);
        int ingrIndex;
        if (isNumber(ingr)) {
            if (isInteger(ingr)) {
                ingrIndex = Integer.parseInt(ingr) - 1;
            } else {
                throw new FoodoramaException(UI.getInvalidIndexMsg());
            }
        } else if (!isNumber(ingr) & ingr.isEmpty()) {
            throw new FoodoramaException(UI.getIngrIndexMissingMsg());
        } else {
            ingrIndex = IngredientList.find(ingr);
        }
        if (ingrIndex == -1 && !isNumber(ingr)) {
            throw new FoodoramaException(UI.getIngrNotExistMsg());
        } else if (ingrIndex < 0 || ingrIndex >= IngredientList.ingredientList.size()) {
            throw new FoodoramaException(UI.getIngrIndexExceedSizeMsg());
        } else {
            assert (ingrIndex != -1) : "The ingrIndex cannot be -1";
            try {
                IngredientList.addExpiry(ingrIndex);
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
            return Math.rint(number) - number == 0;
        } else {
            return false;
        }
    }
}

