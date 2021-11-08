package seedu.foodorama.command;

import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allows the user to add ingredients to the ingredient list.
 * Format: add ingr [INGR_NAME]
 *
 * @author jhsee5
 */
public class AddIngrCommand extends Command {
    public static final String NUMBER_REGEX = "^[-\\d\\s.]+$";
    private static Logger LOGGER = Logger.getLogger("AddIngrCommand.execute()");
    private static final Ui UI = new Ui();
    private static final int INDEX_ZERO = 0;

    AddIngrCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    /**
     * User command to add an ingredient to the ingredient list.
     * The INGR_NAME to be added is stored in parameters. The method checks if the input
     * is empty, if the ingredient already exists in the list or if the input is an number only, and throws
     * an exception.
     *
     * <p>If no exceptions are thrown, INGR_NAME in parameters is added to the ingredient list.</p>
     *
     * @param parameters contains the user input for INGR_NAME
     * @throws FoodoramaException when the INGR_NAME is missing/blank, already exists in the list or is a number
     * @author jhsee5
     */
    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        LOGGER.log(Level.INFO, "Start of process");
        String ingredient = String.join(" ", parameters);
        if (ingredient.isBlank()) {
            LOGGER.log(Level.INFO, "Ingredient Name is Empty");
            throw new FoodoramaException(UI.getIngrNameMissingMsg());
        // If can find ingredient (value is not negative), ingredient already exists
        } else if (IngredientList.find(ingredient) >= 0) {
            LOGGER.log(Level.INFO, "Ingredient already exists", ingredient);
            throw new FoodoramaException(UI.getIngrExistsMsg(parameters.get(INDEX_ZERO)));
        } else if (isNumber(ingredient)) {
            LOGGER.log(Level.INFO, "Parameter is Integer ", ingredient);
            throw new FoodoramaException(UI.getInvalidIngredientName());
        } else {
            try {
                IngredientList.add(ingredient);
                LOGGER.log(Level.INFO, "Successfully added Ingredient");
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
        if (numberString.matches(NUMBER_REGEX)) {
            return true;
        } else {
            return false;
        }
    }

}
