package seedu.foodorama.command;

import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddIngrCommand extends Command {
    private static Logger logger = Logger.getLogger("AddIngrCommand.execute()");
    private static final Ui UI = new Ui();

    AddIngrCommand() {
        LoggerManager.setupLogger(logger);
    }

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        logger.log(Level.INFO, "Start of process");
        String ingredient = String.join(" ", parameters);
        if (ingredient.isBlank()) {
            throw new FoodoramaException(UI.getIngrNameMissingMsg());
        }
        if (IngredientList.find(ingredient) >= 0) {
            logger.log(Level.INFO, "Ingredient already exists", ingredient);
            throw new FoodoramaException(UI.getIngrExistsMsg(parameters.get(0)));
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
