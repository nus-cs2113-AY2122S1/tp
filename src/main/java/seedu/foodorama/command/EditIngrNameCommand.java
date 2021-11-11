package seedu.foodorama.command;

import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allows the user to edit the name of an ingredient.
 * Format: edit ingr name [INGR_NAME]/[INDEX]
 *
 * @author Rakesh12000
 */
public class EditIngrNameCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("EditIngrCommand");
    private static final Ui UI = new Ui();
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_OFFSET = 1;

    EditIngrNameCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    /**
     * User command to edit the name of an ingredient in the ingredient list.
     * Parameters can either accept the [INGR_NAME] of the ingredient to be edited
     * or the [INDEX] of the ingredient in the ingredient list.
     * The method checks if the [INGR_INDEX] is an integer and is out of bounds of the size of the ingredient list
     * or if the [INGR_NAME] doesn't exist in the list and throws an exception.
     *
     * <p>If no exceptions are thrown, the user is prompted to provide the new name for the ingredient.</p>
     *
     * @param parameters contains the [INGR_NAME] or [INGR_INDEX] of the ingredient to edit name of
     * @throws FoodoramaException if [INGR_NAME] doesn't exist in the ingredient list or if [INDEX] is not an integer,
     *      [INDEX] is an integer that's out of bounds
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
        } else if (!isNumber(ingr) & ingr.isEmpty()) {
            LOGGER.log(Level.INFO, "Parameter is Empty");
            throw new FoodoramaException(UI.getIngrIndexMissingMsg());
        } else {
            ingrIndex = IngredientList.find(ingr);
        }
        // If user input is not a number and index cannot be found
        if (!isNumber(ingr) & ingrIndex == -1) {
            LOGGER.log(Level.INFO, "Ingredient does not exist");
            throw new FoodoramaException(UI.getIngrNotExistMsg());
        // If ingr index is out of bounds
        } else if (ingrIndex < 0 || ingrIndex >= IngredientList.ingredientList.size()) {
            LOGGER.log(Level.INFO, "Ingredient Index is not within Ingredient List Range");
            throw new FoodoramaException(UI.getIngrIndexExceedSizeMsg());
        } else {
            assert (ingrIndex != -1) : "The ingrIndex cannot be -1";
            try {
                IngredientList.editName(ingrIndex);
            } catch (FoodoramaException e) {
                throw new FoodoramaException(e.getMessage());
            }
        }
        LOGGER.log(Level.INFO, "Ingredient Name edited.");
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
            // Check if integer when rounded number - number == 0
            if (Math.rint(number) - number == 0) {
                return (number < Integer.MAX_VALUE && number > Integer.MIN_VALUE);
            }
        }
        return false;
    }
}
