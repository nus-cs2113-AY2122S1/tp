package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.Ingredient;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddIngrWasteCommand extends Command {
    private static Logger LOGGER = Logger.getLogger("AddIngrWasteCommand.execute()");
    private static Ui UI = new Ui();

    AddIngrWasteCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

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

    public boolean isNumber(String numberString) {
        try {
            double number = Double.parseDouble(numberString);
            return true;
        } catch (NumberFormatException e) {
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
