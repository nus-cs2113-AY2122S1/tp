package gordon.command.basic;

import gordon.command.Command;
import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;
import gordon.kitchen.Recipe;

public class DeleteRecipeCommand extends Command {
    int index;

    public DeleteRecipeCommand(int index) {
        this.index = index;
    }

    /**
     * <h2> void execute(cookbook).</h2>
     *
     * <p> This method calls the removeRecipe function and prints the corresponding message</p>
     *
     * @param cookbook The recipe object to be referenced
     */
    @Override
    public void execute(Cookbook cookbook) {
        try {
            cookbook.removeRecipe(index);
            System.out.println("OK! The recipe has been deleted from your cookbook.");
        } catch (GordonException e) {
            System.out.println("GordonException: " + e.getMessage());
        }
    }
}
