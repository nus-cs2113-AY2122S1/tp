package seedu.foodorama.command;

import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allows the user to remove a particular ingredient.
 * Format: del ingr [INGR_NAME]/[INDEX]
 *
 * @author Rakesh12000
 */
public class DeleteIngrCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("EditIngrCommand");
    private static final Ui UI = new Ui();
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_OFFSET = 1;

    DeleteIngrCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    /**
     * User command to remove a particular ingredient.
     * This method calls .delete(ingrIndex) method in IngredientList, deleting the corresponding ingredient.
     *
     * @author Rakesh12000
     */
    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        LOGGER.log(Level.INFO, "Start of process");
        String ingr = parameters.get(INDEX_ZERO);
        int ingrIndex;
        if (isNumber(ingr)) {
            if (isInteger(ingr)) {
                ingrIndex = Integer.parseInt(parameters.get(INDEX_ZERO)) - INDEX_OFFSET;
                LOGGER.log(Level.INFO, "Parameter is Integer " + ingrIndex);
            } else {
                throw new FoodoramaException(UI.getInvalidIndexMsg());
            }
        } else if (!isNumber(ingr) && ingr.isEmpty()) {
            LOGGER.log(Level.INFO, "Parameter is Empty");
            throw new FoodoramaException(UI.getIngrIndexMissingMsg());
        } else {
            ingrIndex = IngredientList.find(ingr);
        }
        // If user input is not a number and index cannot be found
        if (!isNumber(ingr) && ingrIndex == -1) {
            LOGGER.log(Level.INFO, "Ingredient does not exist");
            throw new FoodoramaException(UI.getIngrNotExistMsg());
        // If ingr index is out of bounds
        } else if (ingrIndex < 0 || ingrIndex >= IngredientList.ingredientList.size()) {
            LOGGER.log(Level.INFO, "Ingredient Index is not within Ingredient List Range");
            throw new FoodoramaException(UI.getIngrIndexExceedSizeMsg());
        } else {
            assert (ingrIndex != -1) : "The ingrIndex cannot be -1";
            IngredientList.delete(ingrIndex);
        }
        LOGGER.log(Level.INFO, "Ingredient Name edited.");
        LOGGER.log(Level.INFO, "End of process.");
    }

    /**
     * Checks if the parameter numberString is a number.
     *
     * @param numberString the String to check if it is a number
     * @return true if the String is a number, false if it is not a number
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
     * @return true if the String is an integer, false if it is not an integer
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