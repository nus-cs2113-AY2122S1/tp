package gordon.command;

import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;
import gordon.kitchen.Recipe;

public class AddCommand extends Command {
    Recipe recipe;

    public AddCommand(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public void execute(Cookbook cookbook) {
        try {
            cookbook.addRecipe(recipe);
            System.out.println("Added " + recipe.getName() + " recipe! Yum!");
            System.out.print(recipe);
        } catch (GordonException e) {
            System.out.println("GordonException: " + e.getMessage());
        }
    }
}
