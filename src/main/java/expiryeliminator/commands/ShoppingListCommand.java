package expiryeliminator.commands;

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.RecipeList;

public class ShoppingListCommand extends Command{

    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "shopping list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists out ingredients needed to buy to make recipe.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SHOW_SHOPPING_LIST = "Here is your shopping list!\n";

    public static final String MESSAGE_EMPTY_SHOPPING_LIST = "Your shopping list is empty!";

    private final String recipeDescription;

    public ShoppingListCommand(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    @Override
    public String execute(IngredientRepository ingredients, RecipeList recipes) {


        return null;
    }
}
