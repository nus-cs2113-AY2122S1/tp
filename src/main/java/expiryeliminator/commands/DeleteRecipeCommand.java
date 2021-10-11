package expiryeliminator.commands;

import expiryeliminator.data.IngredientList;
import expiryeliminator.data.Recipe;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.NotFoundException;

/**
 * Deletes a recipe from the recipe list.
 */
public class DeleteRecipeCommand extends Command {

    public static final String COMMAND_WORD = "delete recipe";

    private final String recipeName;
    public static final String MESSAGE_RECIPE_NOT_FOUND = "You don't have this recipe in your list :(";
    public static final String MESSAGE_RECIPE_DELETED = "I've deleted this recipe for you:\n" + "%1$s"
            + "Now you have %2$s recipe(s)";

    public DeleteRecipeCommand(String recipeName) {
        this.recipeName = recipeName;
    }

    public String execute(IngredientList ingredients, RecipeList recipes) {
        try {
            Recipe deletedRecipe = recipes.remove(recipeName);
            return String.format(MESSAGE_RECIPE_DELETED, deletedRecipe, recipes.size());
        } catch (NotFoundException e) {
            return MESSAGE_RECIPE_NOT_FOUND;
        }
    }
}
