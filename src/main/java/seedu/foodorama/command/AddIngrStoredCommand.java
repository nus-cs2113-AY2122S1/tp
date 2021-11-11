package seedu.foodorama.command;

import seedu.foodorama.Ingredient;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allows the user to add storage to a particular ingredient in the ingredient list.
 * Format: add ingr stored [INGR_NAME] or add ingr stored [INGR_INDEX]
 *
 * @author renzocanare
 */
public class AddIngrStoredCommand extends Command {
    private static final Logger logger = Logger.getLogger("AddIngrStoredCommand.execute()");
    private static final Ui UI = new Ui();
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_OFFSET = 1;

    AddIngrStoredCommand() {
        LoggerManager.setupLogger(logger);
    }

    /**
     * User command to add storage to an ingredient in the ingredient list.
     * The [INGR_NAME]/[INGR_INDEX] to add storage to is stored in parameters.
     * The method checks if either [INGR_NAME] or [INGR_INDEX] is input by the user, and finds the corresponding
     * ingredient in the ingredient list. The method throws an exception if the ingredient does not exist or the
     * ingredient index is not within the boundaries of the ingredient list.
     *
     * <p>If no exceptions are thrown, the user will be prompted to add storage weight for [INGR_NAME] or [INGR_INDEX].
     * </p>
     *
     * @param parameters contains the [INGR_NAME] or [INGR_INDEX] to add storage to
     * @throws FoodoramaException when the ingredient to add storage weight to does not exist or
     *      the ingredient index is not within boundaries of the ingredient list
     * @author renzocanare
     */
    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        logger.log(Level.INFO, "Start of process");
        String ingredient = parameters.get(INDEX_ZERO);
        int ingredientIndex;
        if (isNumber(ingredient)) {
            if (isInteger(ingredient)) {
                ingredientIndex = Integer.parseInt(ingredient) - INDEX_OFFSET;
            } else {
                throw new FoodoramaException(UI.getInvalidIndexMsg());
            }
        } else {
            ingredientIndex = IngredientList.find(ingredient);
        }
        if (ingredient.isBlank()) {
            throw new FoodoramaException(UI.getIngrNameMissingMsg());
        // If ingredient cannot be found and is not a number
        } else if (!isNumber(ingredient) && ingredientIndex == -1) {
            logger.log(Level.INFO, "Ingredient does not exist");
            throw new FoodoramaException(UI.getIngrNotExistMsg());
        // If ingredient index is out of bounds
        } else if (ingredientIndex < 0 || ingredientIndex >= IngredientList.ingredientList.size()) {
            logger.log(Level.INFO, "Ingredient index is wrong");
            throw new FoodoramaException(UI.getIngrIndexExceedSizeMsg());
        } else {
            try {
                Ingredient currentIngredient = IngredientList.ingredientList.get(ingredientIndex);
                currentIngredient.updateIngredientWeight();
                logger.log(Level.INFO, "Successfully stored Ingredient");
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
