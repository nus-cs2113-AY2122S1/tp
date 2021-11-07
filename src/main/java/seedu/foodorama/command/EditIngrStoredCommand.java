package seedu.foodorama.command;

import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditIngrStoredCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("EditIngrCommand");
    private static final Ui UI = new Ui();

    EditIngrStoredCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    /**
     * Checks if the input parameters of the edit ingredient stored command are valid
     * before calling the appropriate function in the IngredientList class
     * @param parameters parameters for the edit ingredient stored command
     * @throws FoodoramaException if the input parameters are invalid
     *
     * @author renzocanare
     */
    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        LOGGER.log(Level.INFO, "Start of process");
        int ingredientIndex;
        if (isNumber(parameters.get(0))) {
            if (isInteger(parameters.get(0))) {
                ingredientIndex = Integer.parseInt(parameters.get(0)) - 1;
                LOGGER.log(Level.INFO, "Parameter is Integer " + ingredientIndex);
            } else {
                throw new FoodoramaException(UI.getInvalidIndexMsg());
            }
        } else {
            String ingredientName = String.join(" ", parameters);
            if (ingredientName.isBlank()) {
                LOGGER.log(Level.INFO, "Parameter is Empty");
                throw new FoodoramaException(UI.getIngrIndexMissingMsg());
            } else {
                ingredientIndex = IngredientList.find(ingredientName);
                LOGGER.log(Level.INFO, "Parameter is String '" + ingredientName + "'");
            }
        }
        IngredientList.editStorage(ingredientIndex);
        LOGGER.log(Level.INFO, "Ingredient Name edited.");
        LOGGER.log(Level.INFO, "End of process.");
    }

    /**
     * Checks if given string can be converted into a number
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
     * Checks if given string can be converted into an integer
     * @param numberString string to be checked
     * @return true if string can be converted into an integer, false otherwise
     *
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
