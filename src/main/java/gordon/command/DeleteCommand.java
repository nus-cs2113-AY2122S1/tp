package gordon.command;

import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;
import gordon.kitchen.Recipe;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

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
