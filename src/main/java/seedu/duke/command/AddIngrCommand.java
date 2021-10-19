package seedu.duke.command;

import seedu.duke.IngredientList;
import seedu.duke.exceptions.FoodoramaException;
import seedu.duke.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddIngrCommand extends Command {
    private static Logger logger = Logger.getLogger("AddIngrCommand.execute()");

    AddIngrCommand() {
        LoggerManager.setupLogger(logger);
    }

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        logger.log(Level.INFO, "Start of process");
        String ingredient = String.join(" ", parameters);
        if (ingredient.isBlank()) {
            throw new FoodoramaException("Ingredient name cannot be blank");
        }
        if (IngredientList.find(ingredient) >= 0) {
            logger.log(Level.INFO, "Ingredient already exists", ingredient);
            throw new FoodoramaException("The ingredient " + parameters.get(0) + " already exists");
        } else {
            try {
                IngredientList.add(ingredient);
                logger.log(Level.INFO, "Successfully added Ingredient");
            } catch (FoodoramaException e) {
                throw new FoodoramaException(e.getMessage());
            }
        }
        logger.log(Level.INFO, "End of process");
    }

}
