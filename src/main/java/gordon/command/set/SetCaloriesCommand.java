package gordon.command.set;

import gordon.command.Command;
import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;

public class SetCaloriesCommand extends Command {
    String recipeName;
    int newCalories;

    public SetCaloriesCommand(String recipeName, int newCalories) {
        this.recipeName = recipeName;
        this.newCalories = newCalories;
    }

    /**
     * <h2> void execute(cookbook).</h2>
     *
     * <p> This method calls the setCalories function and prints the corresponding message</p>
     *
     * @param cookbook The recipe object to be referenced
     */
    @Override
    public void execute(Cookbook cookbook) {
        System.out.println("Setting calories...");
        try {
            cookbook.setCalories(recipeName, newCalories);
            System.out.println("Calories set successfully.");
        } catch (GordonException e) {
            System.out.println("GordonException: " + e.getMessage());
        }
    }
}
