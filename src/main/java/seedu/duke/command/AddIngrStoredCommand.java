package seedu.duke.command;

import seedu.duke.Ingredient;
import seedu.duke.IngredientList;
import seedu.duke.Ui;
import seedu.duke.logger.LoggerManager;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddIngrStoredCommand extends Command {
    private static Logger logger = Logger.getLogger("AddIngrStoredCommand.execute()");

    AddIngrStoredCommand() {
        LoggerManager.setupLogger(logger);
    }

    @Override
    public void execute(ArrayList<String> parameters) {
        Ui ui = new Ui();
        logger.log(Level.INFO, "Start of process");
        String ingredient = String.join(" ", parameters);
        int ingredientIndex = IngredientList.find(ingredient);
        System.out.println(ui.getLineDivider());
        if (ingredientIndex == -1) {
            System.out.println(ui.getDishNotExistMsg());
            logger.log(Level.INFO, "Ingredient does not exist", ingredientIndex);
        } else {
            try {
                System.out.println("Enter the weight of " + ingredient + " in KG:");
                Scanner in = new Scanner(System.in);
                String ingredientWeight = in.nextLine();
                double ingredientWeightValue = Double.parseDouble(ingredientWeight);
                Ingredient currentIngredient = IngredientList.ingredientList.get(ingredientIndex);
                currentIngredient.updateIngredientWeight(ingredientWeightValue);
                logger.log(Level.INFO, "Successfully stored Ingredient");
            } catch (NumberFormatException e) {
                System.out.println(ui.getInvalidParamMsg());
                System.out.println(ui.getLineDivider());
            }
        }
        System.out.println(ui.getLineDivider());
        logger.log(Level.INFO, "End of process");
    }
}
