package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteDishCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("EditDishCommand");
    private static final Ui UI = new Ui();

    DeleteDishCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        LOGGER.log(Level.INFO, "Start of process");
        String dish = parameters.get(0);
        int dishIndex;
        if (isNumber(dish)) {
            if(isInteger(dish)) {
                dishIndex = Integer.parseInt(parameters.get(0)) - 1;
                LOGGER.log(Level.INFO, "Parameter is Integer " + dishIndex);
            } else {
                throw new FoodoramaException(UI.getInvalidIndexMsg());
            }
        } else if (!isNumber(dish) & dish.isEmpty()) {
            LOGGER.log(Level.INFO, "Parameter is Empty");
            throw new FoodoramaException(UI.getDishIndexMissingMsg());
        } else {
            dishIndex = DishList.find(dish);
        }
        if (!isNumber(dish) & dishIndex == -1) {
            LOGGER.log(Level.INFO, "Dish does not exist");
            throw new FoodoramaException(UI.getDishNotExistMsg(dish));
        } else if (dishIndex < 0 || dishIndex >= DishList.dishList.size()) {
            LOGGER.log(Level.INFO, "Dish Index is not within Dish List Range");
            throw new FoodoramaException(UI.getDishIndexExceedSizeMsg());
        } else {
            assert (dishIndex != -1) : "The dishIndex cannot be -1";
            DishList.delete(dishIndex);
        }
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
