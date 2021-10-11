package seedu.duke.command;

import seedu.duke.Ingredient;
import seedu.duke.IngredientList;

import java.util.ArrayList;

public class AddIngrWasteCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        Ui ui = new Ui();
        //Same num first then ingr (for now)
        int ingredientIndex = IngredientList.find(parameters.get(1));
        if (ingredientIndex == -1) {
            System.out.println(ui.getIngrNotExistMsg());
        } else {
            try {
                Ingredient currentIngredient = IngredientList.ingredientList.get(ingredientIndex);
                currentIngredient.addWaste(Double.parseDouble(parameters.get(0)));
            } catch (NumberFormatException e) {
                System.out.println("____________________________________________");
                System.out.println("Incorrect parameters - Invalid number entered");
                System.out.println("____________________________________________");
            }
        }
    }

}
