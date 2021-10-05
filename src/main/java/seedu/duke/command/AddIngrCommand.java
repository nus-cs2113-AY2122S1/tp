package seedu.duke.command;

import seedu.duke.IngredientList;

import java.util.ArrayList;

public class AddIngrCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        //Maybe number followed by name (name can be multi words)
        System.out.println("____________________________________________");
        String ingredientWeight = parameters.get(0);
        double ingredientWeightValue = Double.parseDouble(ingredientWeight);
        IngredientList.add(parameters.get(1), ingredientWeightValue);
        System.out.println("____________________________________________");
    }

}
