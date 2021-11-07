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
 * Allows the user to add wastage to a particular ingredient in the ingredient list.
 * Format: add ingr waste [INGR_NAME] or add ingr waste [INGR_INDEX]
 *
 * @author renzocanare
 */
public class AddIngrWasteCommand extends Command {
    private static Logger LOGGER = Logger.getLogger("AddIngrWasteCommand.execute()");
    private static Ui UI = new Ui();

    AddIngrWasteCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    /**
     * User command to add wastage to an ingredient in the ingredient list.
     * The [INGR_NAME]/[INGR_INDEX] to add wastage to is stored in ArrayList<String> parameters.
     * The method checks if either [INGR_NAME] or [INGR_INDEX] is input by the user, and finds the corresponding
     * ingredient in the ingredient list. The method throws an exception if the ingredient does not exist or the
     * ingredient index is not within the boundaries of the ingredient list.
     *
     * If no exceptions are thrown, the user will be prompted to add wastage weight for [INGR_NAME] or [INGR_INDEX].
     *
     * @param parameters contains the [INGR_NAME] or [INGR_INDEX] to add wastage to
     * @throws FoodoramaException when the ingredient to add wastage weight to does not exist or
     * the ingredient index is not within boundaries of the ingredient list
     * @author renzocanare
     */
    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        LOGGER.log(Level.INFO, "Start of process");
        String ingredient = String.join(" ", parameters);
        int ingredientIndex;
        if (isNumber(ingredient)) {
            if (isInteger(ingredient)) {
                ingredientIndex = Integer.parseInt(ingredient) - 1;
            } else {
                throw new FoodoramaException(UI.getInvalidIndexMsg());
            }
        } else {
            ingredientIndex = IngredientList.find(ingredient);
        }
        if (ingredient.isBlank()) {
            throw new FoodoramaException(UI.getIngrNameMissingMsg());
        } else if (!isNumber(ingredient) & ingredientIndex == -1) {
            LOGGER.log(Level.INFO, "Ingredient does not exist");
            throw new FoodoramaException(UI.getIngrNotExistMsg());
        } else if (ingredientIndex < 0 || ingredientIndex >= IngredientList.ingredientList.size()) {
            LOGGER.log(Level.INFO, "Ingredient index is wrong");
            throw new FoodoramaException(UI.getIngrIndexExceedSizeMsg());
        } else {
            try {
                Ingredient currentIngredient = IngredientList.ingredientList.get(ingredientIndex);
                currentIngredient.addWaste();
                LOGGER.log(Level.INFO, "Successfully recorded Ingredient waste "
                        + ingredient
                        + " "
                        + currentIngredient.getWastage());
            } catch (FoodoramaException e) {
                throw new FoodoramaException(e.getMessage());
            }
        }
        LOGGER.log(Level.INFO, "End of process");
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
