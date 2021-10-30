package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditDishCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("EditDishCommand");
    private static final Ui ui = new Ui();

    EditDishCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        LOGGER.log(Level.INFO, "Start of process");
        int dishIndex;
        if (isNumber(parameters.get(0))) {
            dishIndex = Integer.parseInt(parameters.get(0)) - 1;
            LOGGER.log(Level.INFO, "Parameter is Integer " + dishIndex);
        } else {
            String dishName = String.join(" ", parameters);
            if (dishName.isBlank()) {
                LOGGER.log(Level.INFO, "Parameter is Empty");
                throw new FoodoramaException(ui.getDishIndexMissingMsg());
            } else {
                dishIndex = DishList.find(dishName);
                LOGGER.log(Level.INFO, "Parameter is String '" + dishName + "'");
            }
        }
        DishList.editName(dishIndex);
        LOGGER.log(Level.INFO, "Dish Name edited.");
        LOGGER.log(Level.INFO, "End of process.");
    }


    public boolean isNumber(String number) {
        try {
            int dishIndex = Integer.parseInt(number) - 1;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

