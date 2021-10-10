package expiryeliminator.commands;

import expiryeliminator.data.IngredientList;
import expiryeliminator.data.Recipe;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.NotFoundException;

/**
 * Finds a specific recipe, together with its associated ingredients.
 */
public class ViewRecipeCommand extends Command {
    /**
     * Unique word associated with the command.
     */
    public static final String COMMAND_WORD = "view recipe";

    private static final String MESSAGE_SHOW_RECIPE = "Here is the recipe in your recipe list:\n" + "\n%1$s\n";
    private static final String MESSAGE_RECIPE_NOT_FOUND = "Sorry. No matching recipes found!";

    private final String recipeDescription;

    public ViewRecipeCommand(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    @Override
    public String execute(IngredientList ingredientList, RecipeList recipeList) {
        try {
            final Recipe recipe = recipeList.findRecipe(recipeDescription);
            return String.format(MESSAGE_SHOW_RECIPE, recipe);
        } catch (NotFoundException e) {
            return MESSAGE_RECIPE_NOT_FOUND;
        }
    }
}
