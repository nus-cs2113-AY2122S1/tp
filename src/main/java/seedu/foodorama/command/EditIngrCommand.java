package seedu.foodorama.command;

import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditIngrCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("EditIngrCommand");
    private static final Ui ui = new Ui();

    EditIngrCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        LOGGER.log(Level.INFO, "Start of process");
        int ingredientIndex;
        if (isNumber(parameters.get(0))) {
            ingredientIndex = Integer.parseInt(parameters.get(0)) - 1;
            LOGGER.log(Level.INFO, "Parameter is Integer " + ingredientIndex);
        } else {
            String ingredientName = String.join(" ", parameters);
            if (ingredientName.isBlank()) {
                LOGGER.log(Level.INFO, "Parameter is Empty");
                throw new FoodoramaException(ui.getIngrIndexMissingMsg());
            } else {
                ingredientIndex = IngredientList.find(ingredientName);
                LOGGER.log(Level.INFO, "Parameter is String '" + ingredientName + "'");
            }
        }
        IngredientList.editName(ingredientIndex);
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
}
