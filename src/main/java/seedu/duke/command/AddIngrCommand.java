package seedu.duke.command;

import seedu.duke.IngredientList;
import seedu.duke.Ui;
import seedu.duke.logger.LoggerManager;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddIngrCommand extends Command {
    private static Logger logger = Logger.getLogger("AddIngrCommand.execute()");

    AddIngrCommand() {
        LoggerManager.setupLogger(logger);
    }

    @Override
    public void execute(ArrayList<String> parameters) {
        Ui ui = new Ui();
        logger.log(Level.INFO, "Start of process");
        String ingredient = String.join(" ", parameters);
        if (IngredientList.find(ingredient) >= 0) {
            ui.printIngrExistsMsg();
            logger.log(Level.INFO, "Ingredient does not exist", ingredient);
        // TODO shift to Ingredient class
        } else {
            System.out.println(ui.getLineDivider());
            System.out.println("Enter the weight of " + ingredient + " in KG:");
            Scanner in = new Scanner(System.in);
            String ingredientWeight = in.nextLine();

            double ingredientWeightValue = Double.parseDouble(ingredientWeight);
            IngredientList.add(ingredient, ingredientWeightValue);
            logger.log(Level.INFO, "Successfully added Ingredient");
        }
        System.out.println(ui.getLineDivider());
        logger.log(Level.INFO, "End of process");
    }

}
