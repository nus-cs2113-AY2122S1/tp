package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allows the user to link an existing dish with an existing ingredient.
 * Format: link [DISH_NAME] / [INGR_NAME]
 *
 * @author Dniv-ra
 */
public class LinkCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("AddingDishIngrCommand.execute()");
    private static final Ui UI = new Ui();
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;


    LinkCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    /**
     * User command to link an ingredient and a dish object.
     * Checks if the input parameters of the link command are valid before
     * calling the appropriate function in the DishList class
     *
     * <p>If no exceptions are thrown, [INGR_NAME] is linked to [DISH_NAME].</p>
     * @param parameters parameters for the link command
     * @throws FoodoramaException if the dish input doesn't exist in the list of dishes
     *
     * @author Dniv-ra
     */
    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        LOGGER.log(Level.INFO, "Start of process");
        int dishIndex = DishList.find(parameters.get(INDEX_ZERO));

        // If dishIndex cannot be found
        if (dishIndex == -1) {
            LOGGER.log(Level.INFO, "Dish does not exist", dishIndex);
            throw new FoodoramaException(UI.getDishNotExistMsg(parameters.get(INDEX_ZERO)));
        } else {
            DishList.dishList.get(dishIndex).addPart(parameters.get(INDEX_ONE));
            LOGGER.log(Level.INFO, "Successfully added dish ingredient");
        }
        LOGGER.log(Level.INFO, "End of process");
    }
}
