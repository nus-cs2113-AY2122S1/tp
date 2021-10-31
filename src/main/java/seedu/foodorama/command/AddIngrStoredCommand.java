package seedu.foodorama.command;

import seedu.foodorama.Ingredient;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddIngrStoredCommand extends Command {
    private static final Logger logger = Logger.getLogger("AddIngrStoredCommand.execute()");
    private static final Ui UI = new Ui();

    AddIngrStoredCommand() {
        LoggerManager.setupLogger(logger);
    }

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {

        logger.log(Level.INFO, "Start of process");
        String ingredient = String.join(" ", parameters);
        int ingredientIndex = IngredientList.find(ingredient);
        if (ingredientIndex == -1) {
            logger.log(Level.INFO, "Ingredient does not exist", ingredientIndex);
            throw new FoodoramaException(UI.getIngrNotExistMsg());
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
}
