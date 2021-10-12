package seedu.duke.command;

import seedu.duke.DishList;
import seedu.duke.Ui;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddDishIngrCommand extends Command {
    private static Logger logger = Logger.getLogger("AddingDishIngrCommand.execute()");

    @Override
    public void execute(ArrayList<String> parameters) {
        Ui ui = new Ui();
        logger.log(Level.INFO, "Start of process");
        int dishIndex = DishList.find(parameters.get(0));
        if (dishIndex == -1) {
            System.out.println(ui.getDishNotExistMsg());
            logger.log(Level.INFO, "Dish does not exist", dishIndex);
        } else {
            System.out.println(ui.getLineDivider());
            DishList.dishList.get(dishIndex).addConstituent(parameters.get(1));
            System.out.println(ui.getLineDivider());
            logger.log(Level.INFO, "Successfully added dish ingredient");
        }
        logger.log(Level.INFO, "End of process");
    }
}
