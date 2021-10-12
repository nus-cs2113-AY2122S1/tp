package gordon.command;

import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;
import gordon.kitchen.Recipe;

import java.util.ArrayList;

public class FindIngredientsCommand extends Command {
    ArrayList<String> ingredients;

    public FindIngredientsCommand(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public void execute(Cookbook cookbook) {
        ArrayList<Recipe> result = cookbook.filterByIngredients(ingredients);
        if (result.size() == 0) {
            System.out.println("GordonException: " + GordonException.NO_RESULT_FOUND);
        } else {
            System.out.println("Searching by ingredient...");
            for (int i = 0; i < result.size(); i++) {
                System.out.println((i + 1) + ". " + result.get(i).getName());
            }
        }
    }
}
