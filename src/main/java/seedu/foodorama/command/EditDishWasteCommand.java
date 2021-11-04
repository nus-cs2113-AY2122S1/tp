package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditDishWasteCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("EditDishCommand");
    private static final Ui UI = new Ui();

    EditDishWasteCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        LOGGER.log(Level.INFO, "Start of process");
        int dishIndex;
        if (isNumber(parameters.get(0))) {
            if(isInteger(parameters.get(0))) {
                dishIndex = Integer.parseInt(parameters.get(0)) - 1;
                LOGGER.log(Level.INFO, "Parameter is Integer " + dishIndex);
            } else {
                throw new FoodoramaException(UI.getInvalidIndexMsg());
            }
        } else {
            String dishName = String.join(" ", parameters);
            if (dishName.isBlank()) {
                LOGGER.log(Level.INFO, "Parameter is Empty");
                throw new FoodoramaException(UI.getDishIndexMissingMsg());
            } else {
                dishIndex = DishList.find(dishName);
                LOGGER.log(Level.INFO, "Parameter is String '" + dishName + "'");
            }
        }
        DishList.editWastage(dishIndex);
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

    public boolean isInteger(String numberString) {
        if(isNumber(numberString)) {
            double number = Double.parseDouble(numberString);
            return Math.rint(number) - number == 0;
        } else {
            return false;
        }
    }
}

