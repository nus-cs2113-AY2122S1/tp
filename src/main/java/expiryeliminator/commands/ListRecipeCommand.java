package expiryeliminator.commands;


import expiryeliminator.data.IngredientList;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.DuplicateDataException;

import java.util.Locale;

/**
 * Lists all recipes, together with its associated ingredients.
 */
public class ListRecipeCommand extends Command {

    /**
     * Unique word associated with the command.
     */
    public static final String COMMAND_WORD = "list recipes";

    public static final String MESSAGE_RECIPE_LIST = "Here are the recipes in your recipe list:\n" + "\n%1$s\n"
            + "You have a total of %2$s recipe(s)";

    private static final String MESSAGE_EMPTY_RECIPE_LIST = "The recipe list is currently empty!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all recipes in the recipe list.\n"
            + "Example: " + COMMAND_WORD;



    @Override
    public String execute(IngredientList ingredientList, RecipeList recipeList) {
        if (recipeList.size() == 0) {
            return MESSAGE_EMPTY_RECIPE_LIST;
        } else {
            return String.format(MESSAGE_RECIPE_LIST, recipeList.getWholeRecipeList(), recipeList.size());
        }
    }
}
