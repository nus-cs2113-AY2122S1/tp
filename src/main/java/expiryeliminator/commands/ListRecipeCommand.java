package expiryeliminator.commands;


import expiryeliminator.data.IngredientList;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.DuplicateDataException;

/**
 * Lists all recipes, together with its associated ingredients.
 */
public class ListRecipeCommand extends Command {

    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "list recipes";

    private static final String MESSAGE_SHOW_WHOLE_RECIPE_LIST = "Here are the recipes in your recipe list:\n" + "\n%1$s\n"
            + "You have a total of %2$s recipe(s)";



    @Override
    public String execute(IngredientList ingredientList, RecipeList recipeList) {
        return String.format(MESSAGE_SHOW_WHOLE_RECIPE_LIST, recipeList.printWholeRecipeList(), recipeList.size());
    }
}
