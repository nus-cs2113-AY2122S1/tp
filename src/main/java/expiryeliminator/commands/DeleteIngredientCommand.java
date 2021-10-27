package expiryeliminator.commands;

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.IngredientStorage;
import expiryeliminator.data.Recipe;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.NotFoundException;

/**
 * Deletes an ingredient.
 */
public class DeleteIngredientCommand extends Command {
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Deletes an ingredient from the ingredient repository.\n"
                    + "Parameters: i/INGREDIENT\n"
                    + "Example: " + COMMAND_WORD + " i/Red Apple";

    private static final String MESSAGE_INGREDIENT_NOT_FOUND = "Sorry. No matching ingredients found!";
    private static final String MESSAGE_INGREDIENT_DELETED = "I've deleted this ingredient for you:\n" + "%1$s\n"
            + "Now you have %2$s ingredient(s)";
    private static final String MESSAGE_INGREDIENT_USED_IN_RECIPE = "Sorry. You cannot delete an ingredient that is "
            + "being used in a recipe.\n"
            + "Please delete the recipe(s) that contain the ingredient first:\n%1$s";

    private final String ingredientName;

    /**
     * Initialises command and stores relevant parameters.
     *
     * @param ingredientName Name of ingredient to be deleted.
     */
    public DeleteIngredientCommand(String ingredientName) {
        assert ingredientName != null && !ingredientName.isBlank()
                : "Ingredient name cannot be null and cannot be blank";
        this.ingredientName = ingredientName;
    }

    @Override
    public String execute(IngredientRepository ingredients, RecipeList recipes) {
        assert ingredients != null : "Ingredient repository cannot be null";
        assert recipes != null : "Recipe list cannot be null";

        StringBuilder recipesContainingIngredient = new StringBuilder();
        for (Recipe recipe : recipes.getRecipes().values()) {
            if (recipe.contains(ingredientName)) {
                recipesContainingIngredient.append("\n").append(recipe.getName());
            }
        }
        if (recipesContainingIngredient.length() > 0) {
            return String.format(MESSAGE_INGREDIENT_USED_IN_RECIPE, recipesContainingIngredient);
        }

        final IngredientStorage ingredient;
        try {
            ingredient = ingredients.remove(ingredientName);
        } catch (NotFoundException e) {
            return MESSAGE_INGREDIENT_NOT_FOUND;
        }
        return String.format(MESSAGE_INGREDIENT_DELETED, ingredient, ingredients.size());
    }
}
