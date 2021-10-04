package expiryeliminator.commands;

import expiryeliminator.data.Ingredient;
import expiryeliminator.data.IngredientList;
import expiryeliminator.data.exception.DuplicateDataException;

/**
 * Adds an ingredient, together with its associated quantity and expiry date.
 */
public class AddIngredientCommand extends Command {
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "add";

    private static final String MESSAGE_INGREDIENT_ADDED = "I've added this ingredient:\n" + "%1$s\n"
            + "Now you have %2$s ingredient(s)";
    private static final String MESSAGE_INGREDIENT_ALREADY_EXISTS = "Unable to add ingredient: %1$s\n"
            + "You already have it in your list";

    private final Ingredient ingredient;

    public AddIngredientCommand(String name, int quantity, String expiryDate) {
        ingredient = new Ingredient(name, quantity, expiryDate);
    }

    @Override
    public String execute(IngredientList ingredients) {
        try {
            ingredients.add(ingredient);
        } catch (DuplicateDataException e) {
            return String.format(MESSAGE_INGREDIENT_ALREADY_EXISTS, ingredient.getName());
        }
        return String.format(MESSAGE_INGREDIENT_ADDED, ingredient, ingredients.size());
    }
}
