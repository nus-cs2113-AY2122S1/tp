package expiryeliminator.commands;

import java.util.ArrayList;

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.Recipe;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.DuplicateDataException;
import expiryeliminator.data.exception.IllegalValueException;
import expiryeliminator.data.exception.NotFoundException;

public class DeleteIngredientInRecipeCommand extends Command {
    /**
     * Unique word associated with the command.
     */
    public static final String COMMAND_WORD = "delete ingredient in recipe";

    public static final String MESSAGE_DELETION_SUCCESSFUL = "I've deleted ingredients in this recipe:\n\n" + "%1$s";
    public static final String RECIPE_DELETION_FAIL = "Unable to deleted ingredients in this recipe:\n" + "%1$s"
            + "No matching recipes or ingredients found, please check your input again\n";
    public static final String MESSAGE_DUPLICATE_INGREDIENT = "Unable to update recipe: %1$s\n"
            + "There is a duplicate ingredient: %2$s.";
    public static final String MESSAGE_ILLEGAL_VALUE_ERROR = "Quantity of ingredients for recipe cannot be zero or "
            + "negative.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes ingredients in a recipe \n"
            + "Parameters: r/RECIPE NAME i/INGREDIENT...\n"
            + "Example: " + COMMAND_WORD
            + " r/Chicken Soup i/Chicken i/Salt";


    private final String name;
    private final ArrayList<String> ingredientNames;

    public DeleteIngredientInRecipeCommand(String name, ArrayList<String> ingredientNames) {
        assert name != null : "Recipe name cannot be null";
        assert ingredientNames != null : "Ingredient list cannot be null";
        assert ingredientNames.size() != 0 : "Ingredient list cannot be empty";
        this.name = name;
        this.ingredientNames = ingredientNames;
    }

    @Override
    public String execute(IngredientRepository ingredients, RecipeList recipes) {
        assert recipes != null : "Recipe list cannot be null";
        final Recipe recipe = new Recipe(name);

        for (int i = 0; i < ingredientNames.size(); i++) {
            try {
                int currentQuantity = 1;
                recipe.add(ingredientNames.get(i), currentQuantity, ingredients);
            } catch (DuplicateDataException e) {
                return String.format(MESSAGE_DUPLICATE_INGREDIENT, name, ingredientNames.get(i));
            } catch (IllegalValueException e) {
                return MESSAGE_ILLEGAL_VALUE_ERROR;
            }
        }

        try {
            recipes = recipes.deleteIngredientInRecipe(recipes, recipe, ingredients);

            if (recipes != null) {
                return String.format(MESSAGE_DELETION_SUCCESSFUL, recipe.getName());
            } else {
                return String.format(RECIPE_DELETION_FAIL, recipe);
            }
        } catch (IllegalValueException | DuplicateDataException | NotFoundException e) {
            return String.format(RECIPE_DELETION_FAIL, recipe);
        }

    }
}
