package seedu.duke.command;


import seedu.duke.Dish;
import seedu.duke.DishList;
import seedu.duke.Ui;
import seedu.duke.logger.LoggerManager;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddDishWasteCommand extends Command {
    private static Logger logger = Logger.getLogger("AddDishWasteCommand.execute()");

    AddDishWasteCommand() {
        LoggerManager.setupLogger(logger);
    }

    @Override
    public void execute(ArrayList<String> parameters) {
        Ui ui = new Ui();
        logger.log(Level.INFO, "Start of process");
        String dish = String.join(" ", parameters);
        int dishIndex = DishList.find(dish);
        System.out.println(ui.getLineDivider());
        if (dishIndex == -1) {
            System.out.println(ui.getDishNotExistMsg());
            logger.log(Level.INFO, "Dish does not exist", dishIndex);
        } else {
            assert (dishIndex != -1) : "The dishIndex cannot be -1";
            try {
                System.out.println("Enter the wastage of " + dish + " in KG:");
                Scanner in = new Scanner(System.in);
                String dishWeight = in.nextLine();
                double dishWeightValue = Double.parseDouble(dishWeight);
                Dish currentDish = DishList.dishList.get(dishIndex);
                currentDish.addWaste(dishWeightValue);
                logger.log(Level.INFO, "Successfully recorded Dish waste "
                        + dish
                        + " "
                        + dishWeightValue);
            } catch (NumberFormatException e) {
                System.out.println(ui.getInvalidParamMsg());
                System.out.println(ui.getLineDivider());
            }
        }
        System.out.println(ui.getLineDivider());
        logger.log(Level.INFO, "End of process");
    }

}
