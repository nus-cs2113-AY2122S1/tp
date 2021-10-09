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

    private static final String MESSAGE_RECIPE_LIST = "Here are the recipes in your recipe list:\n" + "\n%1$s\n"
            + "You have a total of %2$s recipe(s)";



    @Override
    public String execute(IngredientList ingredientList, RecipeList recipeList) {
        if(recipeList.size() == 0) {
            IncorrectCommand incorrectCommand = new IncorrectCommand("The recipe list is currently empty!");
            return incorrectCommand.execute(ingredientList, null);
        } else {
            return String.format(MESSAGE_RECIPE_LIST, recipeList.getWholeRecipeList(), recipeList.size());
        }
    }
}
