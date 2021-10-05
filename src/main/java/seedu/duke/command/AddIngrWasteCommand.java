package seedu.duke.command;

import seedu.duke.Ingredient;
import seedu.duke.IngredientList;

import java.util.ArrayList;

public class AddIngrWasteCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        //Same num first then ingr (for now)
        int ingredientIndex = IngredientList.find(parameters.get(1));
        if (ingredientIndex == -1) {
            System.out.println("Ingredient does not exist");
        } else {
            Ingredient currentIngredient =  IngredientList.ingredientList.get(ingredientIndex);
            currentIngredient.addWaste(Double.parseDouble(parameters.get(0)));
        }
    }

}
