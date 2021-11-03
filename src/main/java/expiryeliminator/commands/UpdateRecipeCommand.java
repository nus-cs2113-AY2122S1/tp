package expiryeliminator.commands;

import java.util.ArrayList;

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.Recipe;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.DuplicateDataException;
import expiryeliminator.data.exception.IllegalValueException;
import expiryeliminator.data.exception.NotFoundException;

public class UpdateRecipeCommand extends Command {
    /**
     * Unique word associated with the command.
     */
    public static final String COMMAND_WORD = "update recipe";

    public static final String MESSAGE_RECIPE_UPDATED = "I've updated this recipe:\n" + "%1$s";
    public static final String RECIPE_UPDATE_FAIL = "Unable to update this recipe:\n" + "%1$s"
            + "No matching recipes or ingredients found, please check your input again\n";
    public static final String MESSAGE_DUPLICATE_INGREDIENT = "Unable to update recipe: %1$s\n"
            + "There is a duplicate ingredient: %2$s.";
    public static final String MESSAGE_ILLEGAL_VALUE_ERROR = "Quantity of ingredients for recipe cannot be zero or "
            + "negative.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the quantity of ingredients"
            + " in the recipe list.\n"
            + "Parameters: r/RECIPE NAME i/INGREDIENT... q/QUANTITY...\n"
            + "Example: " + COMMAND_WORD
            + " r/Chicken Soup i/Chicken q/1 i/Salt q/20 i/Ginger q/2";


    private final String name;
    private final ArrayList<String> ingredientNames;
    private final ArrayList<Integer> quantities;

    public UpdateRecipeCommand(String name, ArrayList<String> ingredientNames, ArrayList<Integer> quantities) {
        assert name != null : "Recipe name cannot be null";
        assert ingredientNames != null : "Ingredient list cannot be null";
        assert quantities != null : "Quantity list cannot be null";
        assert ingredientNames.size() != 0 : "Ingredient list cannot be empty";
        assert ingredientNames.size() == quantities.size() : "Quantity list cannot be empty";
        this.name = name;
        this.ingredientNames = ingredientNames;
        this.quantities = quantities;
    }

    @Override
    public String execute(IngredientRepository ingredients, RecipeList recipes) {
        assert recipes != null : "Recipe list cannot be null";

        final Recipe recipe = new Recipe(name);

        for (int i = 0; i < ingredientNames.size(); i++) {
            try {
                recipe.add(ingredientNames.get(i), quantities.get(i), ingredients);
            } catch (DuplicateDataException e) {
                return String.format(MESSAGE_DUPLICATE_INGREDIENT, name, ingredientNames.get(i));
            } catch (IllegalValueException e) {
                return MESSAGE_ILLEGAL_VALUE_ERROR;
            }
        }

        try {
            recipes = recipes.updateRecipe(recipes, recipe, ingredients);

            if (recipes != null) {
                return String.format(MESSAGE_RECIPE_UPDATED, recipe);
            } else {
                return String.format(RECIPE_UPDATE_FAIL, recipe);
            }
        } catch (IllegalValueException | DuplicateDataException | NotFoundException e) {
            return String.format(RECIPE_UPDATE_FAIL, recipe);
        }

    }
}
