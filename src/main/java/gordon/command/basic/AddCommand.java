package gordon.command.basic;

import gordon.command.Command;
import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;
import gordon.kitchen.Recipe;

public class AddCommand extends Command {
    Recipe recipe;

    public AddCommand(Recipe recipe) {
        this.recipe = recipe;
    }

    /**
     * <h2> void execute(cookbook).</h2>
     *
     * <p> This method calls the addRecipe function and prints the corresponding message</p>
     *
     * @param cookbook The recipe object to be referenced
     */
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
