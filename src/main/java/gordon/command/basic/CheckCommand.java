package gordon.command.basic;

import gordon.command.Command;
import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;

public class CheckCommand extends Command {
    String recipeName;

    public CheckCommand(String recipeName) {
        this.recipeName = recipeName;
    }

    /**
     * <h2> void execute(cookbook).</h2>
     *
     * <p> This method calls the checkRecipe function and prints the recipe</p>
     *
     * @param cookbook The recipe object to be referenced
     */
    @Override
    public void execute(Cookbook cookbook) {
        try {
            if (recipeName.isEmpty()) {
                throw new GordonException(GordonException.EMPTY_RECIPE_NAME);
            }
            cookbook.checkRecipe(recipeName);
        } catch (GordonException e) {
            System.out.println("GordonException: " + e.getMessage());
        }
    }
}
