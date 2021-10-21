package expiryeliminator.commands;

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.Recipe;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.IllegalValueException;
import expiryeliminator.data.exception.NotFoundException;

import java.util.ArrayList;

public class ShoppingListCommand extends Command{

    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "shopping list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists out ingredients needed to buy to make recipe.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SHOW_SHOPPING_LIST = "Here is your shopping list!\n";
    public static final String MESSAGE_EMPTY_SHOPPING_LIST = "Your shopping list is empty!";
    public static final String MESSAGE_RECIPE_NOT_FOUND = "Sorry. No matching recipes found!";

    private final ArrayList<String> recipeDescriptions;

    public ShoppingListCommand(ArrayList<String> recipeDescriptions) {
        this.recipeDescriptions = recipeDescriptions;
    }

    @Override
    public String execute(IngredientRepository ingredients, RecipeList recipes) {
        try {
            ArrayList<Recipe> recipeList = new ArrayList<>();
            for (String recipeDescription : recipeDescriptions) {
                Recipe recipe = recipes.findRecipe(recipeDescription);
                recipeList.add(recipe);
            }
            String shoppingList = ingredients.generateShoppingList(recipeList);
            return shoppingList;
        } catch (NotFoundException | IllegalValueException e) {
            return MESSAGE_RECIPE_NOT_FOUND;
        }
    }
}
