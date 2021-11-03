package gordon.command.find;

import gordon.command.Command;
import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;
import gordon.kitchen.Recipe;

import java.util.ArrayList;
import java.util.Collections;

public class FindIngredientsCommand extends Command {
    ArrayList<String> ingredients;

    public FindIngredientsCommand(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public void execute(Cookbook cookbook) {
        System.out.println("Searching by ingredient...");
        ArrayList<Recipe> result = cookbook.filterByIngredients(ingredients);
        ArrayList<String> resultNames = new ArrayList<String>();
        for (int i = 0; i < result.size(); i++) {
            resultNames.add(result.get(i).getName());
        }

        // Sort
        Collections.sort(resultNames, String.CASE_INSENSITIVE_ORDER);

        if (result.size() == 0) {
            System.out.println("GordonException: " + GordonException.NO_RESULT_FOUND);
        } else {
            for (int i = 0; i < result.size(); i++) {
                System.out.println((i + 1) + ". " + resultNames.get(i));
            }
        }
    }
}
