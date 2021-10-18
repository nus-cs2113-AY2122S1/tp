package seedu.duke.command;

import seedu.duke.IngredientList;
import seedu.duke.Ui;
import seedu.duke.exceptions.FoodoramaException;
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
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        Ui ui = new Ui();
        logger.log(Level.INFO, "Start of process");
        String ingredient = String.join(" ", parameters);
        if(ingredient.isBlank()) {
            throw new FoodoramaException("Ingredient name cannot be blank");
        }
        if (IngredientList.find(ingredient) >= 0) {
            System.out.println(ui.getIngrExistsMsg());
            logger.log(Level.INFO, "Ingredient already exists", ingredient);
        } else {
            System.out.println(ui.getLineDivider());
            //String ingredientWeight = parameters.get(0);
            System.out.println("Enter the weight of " + ingredient + " in KG:");
            Scanner in = new Scanner(System.in);
            String ingredientWeight = in.nextLine();
            try {
                double ingredientWeightValue = Double.parseDouble(ingredientWeight);
                IngredientList.add(ingredient, ingredientWeightValue);
                logger.log(Level.INFO, "Successfully added Ingredient");
            } catch (NumberFormatException e) {
                throw new FoodoramaException("Sorry, please input a valid number.");
            }
        }
        System.out.println(ui.getLineDivider());
        logger.log(Level.INFO, "End of process");
    }

}
