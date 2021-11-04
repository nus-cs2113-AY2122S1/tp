package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddDishCommand extends Command {
    private static Logger LOGGER = Logger.getLogger("AddDishCommand.execute()");
    private static final Ui UI = new Ui();

    AddDishCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        String dish = parameters.get(0);
        if (dish.isBlank()) {
            LOGGER.log(Level.INFO, "Dish Name is Empty");
            throw new FoodoramaException(UI.getDishNameMissingMsg());
        } else if (DishList.find(dish) >= 0) {
            LOGGER.log(Level.INFO, "Dish already exists ", dish);
            throw new FoodoramaException(UI.getDishExistsMsg(dish));
        } else if (isNumber(dish)) {
            LOGGER.log(Level.INFO, "Parameter is Integer ", dish);
            throw new FoodoramaException(UI.getInvalidDishName());
        } else {
            DishList.add(dish);
            LOGGER.log(Level.INFO, "Successfully added Dish");
        }
    }

    public boolean isNumber(String numberString) {
        try {
            double number = Double.parseDouble(numberString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
