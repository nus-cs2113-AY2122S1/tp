package expiryeliminator.commands;

import expiryeliminator.data.Ingredient;
import expiryeliminator.data.IngredientList;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.NotFoundException;

/**
 * Finds a specific ingredient, together with its associated quantity and expiry date.
 */
public class ViewIngredientCommand extends Command {
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "view";

    private static final String MESSAGE_SHOW_INGREDIENT = "Here is the ingredient in your list:\n" + "\n%1$s\n";
    private static final String MESSAGE_INGREDIENT_NOT_FOUND = "Sorry. No matching ingredients found!";

    private final String ingredientDescription;

    public ViewIngredientCommand(String ingredientDescription) {
        this.ingredientDescription = ingredientDescription;
    }

    @Override
    public String execute(IngredientList ingredientList, RecipeList recipes) {
        try {
            final Ingredient ingredient = ingredientList.find(ingredientDescription);
            return String.format(MESSAGE_SHOW_INGREDIENT, ingredient);
        } catch (NotFoundException e) {
            return MESSAGE_INGREDIENT_NOT_FOUND;
        }
    }

}
