package expiryeliminator.commands;

import expiryeliminator.data.IngredientList;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.Recipe;

/**
 * Finds a specific recipe, together with its associated ingredients.
 */

public class ViewRecipeCommand extends Command {
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "view recipe";

    private static final String MESSAGE_SHOW_RECIPE = "Here is the recipe in your recipe list:\n" + "\n%1$s\n";

    private final String recipeDescription;

    public ViewRecipeCommand(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    @Override
    public String execute(IngredientList ingredientList, RecipeList recipeList) {
        Recipe recipe = recipeList.findRecipe(recipeDescription);
        if(recipe == null) {
            IncorrectCommand incorrectCommand = new IncorrectCommand("Sorry. No matching recipes found!");
            return incorrectCommand.execute(ingredientList, recipeList);
        } else {
            return String.format(MESSAGE_SHOW_RECIPE, recipe);
        }
    }
}
