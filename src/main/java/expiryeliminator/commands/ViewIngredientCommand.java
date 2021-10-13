package expiryeliminator.commands;

import expiryeliminator.data.Ingredient;
import expiryeliminator.data.IngredientList;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.NotFoundException;

/**
 * Finds a specific ingredient, together with its associated quantity and expiry date.
 */
public class ViewIngredientCommand extends Command {
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds an ingredient to the ingredient repository.\n"
            + "Ingredient names are case insensitive and must be unique.\n"
            + "Parameters: i/INGREDIENT\n"
            + "Example: " + COMMAND_WORD + " i/Red Apple";

    public static final String MESSAGE_SHOW_INGREDIENT = "Here is the ingredient in your list:\n" + "\n%1$s\n";
    public static final String MESSAGE_INGREDIENT_NOT_FOUND = "Sorry. No matching ingredients found!";

    private final String ingredientDescription;

    /**
     * Initialises command and stores relevant parameters.
     *
     * @param ingredientDescription Name of ingredient to find.
     */
    public ViewIngredientCommand(String ingredientDescription) {
        assert ingredientDescription != null && !ingredientDescription.isBlank();
        this.ingredientDescription = ingredientDescription;
    }

    @Override
    public String execute(IngredientList ingredientList, RecipeList recipes) {
        try {
            final Ingredient ingredient = ingredientList.find(ingredientDescription);
            return String.format(MESSAGE_SHOW_INGREDIENT, ingredient);
        } catch (NotFoundException e) {
            return MESSAGE_INGREDIENT_NOT_FOUND;
        }
    }

}
