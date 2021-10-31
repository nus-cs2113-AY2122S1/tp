package expiryeliminator.commands;

//@@author JoshHDs

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.IngredientStorage;
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

    private final String ingredientName;

    /**
     * Initialises command and stores relevant parameters.
     *
     * @param ingredientName Name of ingredient to find.
     */
    public ViewIngredientCommand(String ingredientName) {
        assert ingredientName != null && !ingredientName.isBlank();
        this.ingredientName = ingredientName;
    }

    @Override
    public String execute(IngredientRepository ingredientRepository, RecipeList recipes) {
        try {
            final IngredientStorage ingredient = ingredientRepository.find(ingredientName);
            return String.format(MESSAGE_SHOW_INGREDIENT, ingredient);
        } catch (NotFoundException e) {
            return MESSAGE_INGREDIENT_NOT_FOUND;
        }
    }

}
