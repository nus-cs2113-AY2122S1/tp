package gordon.command.set;

import gordon.command.Command;
import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;

import java.util.ArrayList;

public class SetIngredientsCommand extends Command {
    String recipeName;
    ArrayList<String> ingredients;

    public SetIngredientsCommand(String recipeName, ArrayList<String> ingredients) {
        this.recipeName = recipeName;
        this.ingredients = ingredients;
    }

    /**
     * <h2> void execute(cookbook).</h2>
     *
     * <p> This method calls the setIngredients function and prints the corresponding message</p>
     *
     * @param cookbook The recipe object to be referenced
     */
    @Override
    public void execute(Cookbook cookbook) {
        System.out.println("Setting ingredients...");
        try {
            cookbook.setIngredients(recipeName, ingredients);
            System.out.println("Ingredients set successfully.");
        } catch (GordonException e) {
            System.out.println("GordonException: " + e.getMessage());
        }
    }
}
