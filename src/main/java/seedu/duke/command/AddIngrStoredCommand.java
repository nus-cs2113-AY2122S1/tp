package seedu.duke.command;

import seedu.duke.Ingredient;
import seedu.duke.IngredientList;

import java.util.ArrayList;

public class AddIngrStoredCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        //Todo change implementation to renzos dual scan in
        int ingredientIndex = IngredientList.find(parameters.get(1));
        if (ingredientIndex == -1) {
            System.out.println("Ingredient does not exist");
        } else {
            try {
                Ingredient currentIngredient = IngredientList.ingredientList.get(ingredientIndex);
                currentIngredient.updateIngredientWeight(Double.parseDouble(parameters.get(0)));
            } catch (NumberFormatException e) {
                System.out.println("____________________________________________");
                System.out.println("Incorrect parameters - Invalid number entered");
                System.out.println("____________________________________________");
            }
        }
    }
}
