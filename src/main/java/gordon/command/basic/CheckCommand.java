package gordon.command.basic;

import gordon.command.Command;
import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;

public class CheckCommand extends Command {
    String recipeName;

    public CheckCommand(String recipeName) {
        this.recipeName = recipeName;
    }

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
