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

    public static final String MESSAGE_RECIPE_UPDATED = "I've updated this recipe:\n\n" + "%1$s\n\n"
            + "You may want to use the 'list recipes' command to check the whole recipe list.";
    public static final String RECIPE_UPDATE_FAIL = "Unable to update this recipe:\n\n" + "%1$s\n\n"
            + "No matching recipes or ingredients found, please check your input again.\n";
    public static final String MESSAGE_ILLEGAL_VALUE_ERROR = "Unable to update this recipe:\n\n" + "%1$s\n\n"
            + "Quantity of ingredients for recipe cannot be negative.";
    public static final String MESSAGE_NO_INGREDIENT_ERROR = "Unable to update this recipe:\n\n" + "%1$s\n\n"
            + "Why update fails:\n"
            + "There should be at least one ingredient in the recipe.\n"
            + "But your command may result in a recipe without any ingredients.\n"
            + "Therefore, please add another ingredient first if you still want to delete this ingredient.";
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

        final Recipe recipe;
        try {
            recipe = recipes.findRecipe(name);
        } catch (NotFoundException e) {
            return String.format(RECIPE_UPDATE_FAIL, name);
        }

        for (int i = 0; i < ingredientNames.size(); i++) {
            int quantity = quantities.get(i);
            String ingredientName = ingredientNames.get(i);
            if(quantity == 0) {
                try {
                    recipe.delete(ingredientName);
                } catch (IllegalValueException e) {
                    return String.format(MESSAGE_NO_INGREDIENT_ERROR, name);
                } catch (NotFoundException e) {
                    return String.format(RECIPE_UPDATE_FAIL, name);
                }
            } else {
                try {
                    recipe.update(ingredientName, quantity);
                } catch (IllegalValueException e) {
                    return String.format(RECIPE_UPDATE_FAIL, name) + "\n\n" + MESSAGE_ILLEGAL_VALUE_ERROR;
                } catch (NotFoundException e) {
                    try {
                        recipe.add(ingredientName, quantity, ingredients);
                    } catch (DuplicateDataException | IllegalValueException ex) {
                        return String.format(RECIPE_UPDATE_FAIL, name);
                    }
                }
            }
        }
        return String.format(MESSAGE_RECIPE_UPDATED, recipe);
    }
}
