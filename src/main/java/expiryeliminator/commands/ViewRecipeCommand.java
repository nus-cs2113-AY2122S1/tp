package expiryeliminator.commands;

import expiryeliminator.data.Ingredient;
import expiryeliminator.data.IngredientList;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.Recipe;

/**
 * Finds a specific recipe, together with its associated ingredients.
 */

public class ViewRecipeCommand extends Command {
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "view recipe";

    private static final String MESSAGE_SHOW_Recipe = "Here is the recipe in your recipe list:\n" + "\n%1$s\n";

    private final String recipeDescription;

    public ViewRecipeCommand(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    @Override
    public String execute(IngredientList ingredientList, RecipeList recipeList) {
        Recipe recipe = recipeList.findRecipe(recipeDescription);
        return String.format(MESSAGE_SHOW_Recipe, recipe);
    }
}
