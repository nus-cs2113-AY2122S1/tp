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

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        LOGGER.log(Level.INFO, "Start of process");
        int ingredientIndex;
        if (isNumber(parameters.get(0))) {
            if(isInteger(parameters.get(0))) {
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


    public boolean isNumber(String number) {
        try {
            int ingredientIndex = Integer.parseInt(number) - 1;
            return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

    public boolean isInteger(String numberString) {
        if(isNumber(numberString)) {
            double number = Double.parseDouble(numberString);
            return Math.rint(number) - number == 0;
        } else {
            return false;
        }
    }
}
