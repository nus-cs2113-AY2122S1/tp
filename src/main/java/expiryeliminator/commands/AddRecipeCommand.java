package expiryeliminator.commands;

import expiryeliminator.data.IngredientList;
import expiryeliminator.data.Recipe;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.DuplicateDataException;
import expiryeliminator.data.exception.EmptyIngredientsException;

public class AddRecipeCommand extends Command{
    public static final String COMMAND_WORD = "add recipe";

    private static final String MESSAGE_RECIPE_ADDED = "I've added this recipe:\n" + "%1$s\n"
            + "Now you have %2$s recipe(s)";
    private static final String MESSAGE_RECIPE_ALREADY_EXISTS = "Unable to add recipe: %1$s\n"
            + "You already have it in your list";
    private static final String MESSAGE_NO_INGREDIENTS_FOR_RECIPE = "There's no ingredients for this recipe";

    private final Recipe recipe;

    public AddRecipeCommand(String name, IngredientList ingredients) {
        recipe = new Recipe(name,ingredients);
    }

    @Override
    public String execute(IngredientList ingredients, RecipeList recipes) {
        try {
            recipes.add(recipe);
        } catch (DuplicateDataException e) {
            return String.format(MESSAGE_RECIPE_ALREADY_EXISTS, recipe.getName());
        } catch (EmptyIngredientsException e) {
            return MESSAGE_NO_INGREDIENTS_FOR_RECIPE;
        }
        return String.format(MESSAGE_RECIPE_ADDED, recipe, recipes.size());
    }
}
