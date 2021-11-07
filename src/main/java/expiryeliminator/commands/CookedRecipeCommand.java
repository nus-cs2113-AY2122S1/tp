package expiryeliminator.commands;

import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import expiryeliminator.common.LogsCenter;
import expiryeliminator.data.IngredientQuantity;
import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.IngredientStorage;
import expiryeliminator.data.Recipe;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.IllegalValueException;
import expiryeliminator.data.exception.NotFoundException;

/**
 * Updates the ingredient amounts in the repository based on the recipe that is cooked.
 */
public class CookedRecipeCommand extends Command {
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "cooked";

    public static final String MESSAGE_RECIPE_COOKED = "Now you have these quantities left for your ingredients:\n"
            + "\n%1$s\n";
    public static final String MESSAGE_INSUFFICIENT_QUANTITY = "You don't have enough ingredients!\n"
            + "Generate a shopping list to see what ingredients you're missing.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes the ingredients' quantities based on"
            + "the recipe cooked.\n"
            + "Parameters: r/RECIPE NAME\n"
            + "Example: " + COMMAND_WORD
            + " r/Chicken Soup";

    private final String name;
    private static final Logger logger = LogsCenter.getLogger(CookedRecipeCommand.class);

    public CookedRecipeCommand(String name) {
        assert name != null : "The recipe name cannot be null";
        assert !name.isEmpty() : "The recipe name cannot be empty";
        this.name = name;
    }

    @Override
    public String execute(IngredientRepository ingredients, RecipeList recipes) {
        String ingredientsLeft = "";
        try {
            Recipe recipe = recipes.findRecipe(name);
            TreeMap<String, IngredientQuantity> ingredientsInRecipe = recipe.getIngredientQuantities();
            if (!recipe.allIngredientsAreSufficient(ingredients)) {
                return MESSAGE_INSUFFICIENT_QUANTITY;
            }
            for (String s : ingredientsInRecipe.keySet()) {
                IngredientStorage ingredient = ingredients.findWithNullReturn(s);
                assert ingredient != null : "Ingredient should be added in when recipe is added.";
                int quantity = ingredientsInRecipe.get(s).getQuantity();
                logger.log(Level.INFO,"Removing " + quantity + " amount of " + ingredient.getIngredient());
                ingredient.remove(quantity);
                ingredientsLeft += ingredient + "\n";
            }
        } catch (NotFoundException e) {
            return ViewRecipeCommand.MESSAGE_RECIPE_NOT_FOUND;
        } catch (IllegalValueException e) {
            assert false : "The ingredients should have sufficient quantity";
        }
        return String.format(MESSAGE_RECIPE_COOKED, ingredientsLeft);
    }
}
