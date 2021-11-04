package seedu.foodorama.command;

import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditIngrWasteCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("EditIngrCommand");
    private static final Ui UI = new Ui();

    EditIngrWasteCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        LOGGER.log(Level.INFO, "Start of process");
        String ingr = parameters.get(0);
        int ingrIndex;
        if (isNumber(ingr)) {
            if (isInteger(ingr)) {
                ingrIndex = Integer.parseInt(parameters.get(0)) - 1;
                LOGGER.log(Level.INFO, "Parameter is Integer " + ingrIndex);
            } else {
                throw new FoodoramaException(UI.getInvalidIndexMsg());
            }
        } else if (!isNumber(ingr) & ingr.isEmpty()) {
            LOGGER.log(Level.INFO, "Parameter is Empty");
            throw new FoodoramaException(UI.getIngrIndexMissingMsg());
        } else {
            LOGGER.log(Level.INFO, "Parameter is String '" + ingr + "'");
            ingrIndex = IngredientList.find(ingr);
        }
        if (ingrIndex == -1) {
            LOGGER.log(Level.INFO, "Ingredient does not exist");
            throw new FoodoramaException(UI.getIngrNotExistMsg());
        } else if (ingrIndex < 0 || ingrIndex >= IngredientList.ingredientList.size()) {
            LOGGER.log(Level.INFO, "Ingredient Index is not within Ingredient List Range");
            throw new FoodoramaException(UI.getIngrIndexExceedSizeMsg());
        } else {
            assert (ingrIndex != -1) : "The dishIndex cannot be -1";
            try {
                IngredientList.editWastage(ingrIndex);
            } catch (FoodoramaException e) {
                throw new FoodoramaException(e.getMessage());
            }
        }
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
        if (isNumber(numberString)) {
            double number = Double.parseDouble(numberString);
            return Math.rint(number) - number == 0;
        } else {
            return false;
        }
    }
}
