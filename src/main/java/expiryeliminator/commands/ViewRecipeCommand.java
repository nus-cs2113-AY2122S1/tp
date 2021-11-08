package expiryeliminator.commands;

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.Recipe;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.NotFoundException;

//@@author KairuiHu

/**
 * Finds a specific recipe, together with its associated ingredients.
 */

public class ViewRecipeCommand extends Command {
    /**
     * Unique word associated with the command.
     */
    public static final String COMMAND_WORD = "view recipe";

    public static final String MESSAGE_SHOW_RECIPE = "Here is the recipe in your recipe list:\n" + "\n%1$s\n";
    public static final String MESSAGE_RECIPE_NOT_FOUND = "Sorry. No matching recipes found!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View a recipe in the recipe list.\n"
            + "Only full name of recipe will be matched.\n"
            + "Parameters: r/RECIPE NAME\n"
            + "Example: " + COMMAND_WORD + " r/Chicken Soup";


    private final String recipeDescription;

    public ViewRecipeCommand(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    @Override
    public String execute(IngredientRepository ingredientRepository, RecipeList recipeList) {
        try {
            final Recipe recipe = recipeList.findRecipe(recipeDescription);
            return String.format(MESSAGE_SHOW_RECIPE, recipe);
        } catch (NotFoundException e) {
            return MESSAGE_RECIPE_NOT_FOUND;
        }
    }
}
