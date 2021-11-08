package expiryeliminator.commands;

//@@author JoshHDs

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.Recipe;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.IllegalValueException;
import expiryeliminator.data.exception.NotFoundException;
import java.util.ArrayList;

/**
 * Lists ingredients required to be bought depending on recipe/recipes user chooses to make.
 */
public class ShoppingListCommand extends Command {

    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "shopping list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists out ingredients needed to buy to make recipe.\n"
            + "Parameters: r/RECIPE...\n"
            + "Example: " + COMMAND_WORD + " r/Chicken Soup r/Pork Soup";

    public static final String MESSAGE_SHOW_SHOPPING_LIST = "Here is your shopping list!\n";
    public static final String MESSAGE_RECIPE_NOT_FOUND = "Sorry. One or more of your recipes are not found!";

    private final ArrayList<String> recipeDescriptions;

    /**
     * Initialises command and stores relevant parameter.
     *
     * @param recipeDescriptions Name of recipe/recipes user wants to generate shopping list for.
     */
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
            return (MESSAGE_SHOW_SHOPPING_LIST + "\n" + shoppingList);
        } catch (NotFoundException | IllegalValueException e) {
            return MESSAGE_RECIPE_NOT_FOUND;
        }
    }
}
