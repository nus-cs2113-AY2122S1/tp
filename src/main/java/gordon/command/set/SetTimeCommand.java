package gordon.command.set;

import gordon.command.Command;
import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;

public class SetTimeCommand extends Command {
    String recipeName;
    int prepTime;
    int cookTime;

    public SetTimeCommand(String recipeName, int prepTime, int cookTime) {
        this.recipeName = recipeName;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
    }

    /**
     * <h2> void execute(cookbook).</h2>
     *
     * <p> This method calls the setTimes function and prints the corresponding message</p>
     *
     * @param cookbook The recipe object to be referenced
     */
    @Override
    public void execute(Cookbook cookbook) {
        System.out.println("Setting times...");
        try {
            cookbook.setTimes(recipeName, prepTime, cookTime);
            System.out.println("Times set successfully.");
        } catch (GordonException e) {
            System.out.println("GordonException: " + e.getMessage());
        }
    }
}
