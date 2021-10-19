package seedu.duke.command;

import seedu.duke.Ingredient;
import seedu.duke.IngredientList;
import seedu.duke.Ui;
import seedu.duke.exceptions.FoodoramaException;
import seedu.duke.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddIngrStoredCommand extends Command {
    private static final Logger logger = Logger.getLogger("AddIngrStoredCommand.execute()");
    private static final Ui ui = new Ui();

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
            throw new FoodoramaException(ui.getIngrNotExistMsg());
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
