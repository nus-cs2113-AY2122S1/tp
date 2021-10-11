package seedu.duke.command;

import seedu.duke.IngredientList;

import java.util.ArrayList;

public class AddIngrCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        try {
            //Maybe number followed by name (name can be multi words)
            String ingredientWeight = parameters.get(0);
            double ingredientWeightValue = Double.parseDouble(ingredientWeight);
            System.out.println("____________________________________________");
            IngredientList.add(parameters.get(1), ingredientWeightValue);
            System.out.println("____________________________________________");
        } catch (NumberFormatException e) {
            System.out.println("____________________________________________");
            System.out.println("Incorrect parameters - Invalid number entered");
            System.out.println("____________________________________________");
        }
    }

}
