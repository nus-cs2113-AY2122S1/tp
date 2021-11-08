package expiryeliminator.commands;

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.IngredientStorage;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.IllegalValueException;
import expiryeliminator.data.exception.NotFoundException;

/**
 * Decrement ingredient by a specified quantity.
 */
public class DecrementCommand extends Command {
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "decrement";
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Decreases the quantity of a specified ingredient by a specified amount.\n"
                    + "Parameters: i/INGREDIENT q/QUANTITY e/EXPIRY_DATE\n"
                    + "Example: " + COMMAND_WORD + " i/Red Apple q/5 e/2021-12-25";

    private static final String MESSAGE_QUANTITY_ZERO = "Cannot decrement by a quantity of zero.";
    private static final String MESSAGE_INGREDIENT_NOT_FOUND = "Sorry. No matching ingredients found!";
    private static final String MESSAGE_QUANTITY_TOO_MUCH =
            "Sorry, you currently only have %1$s%2$s of this ingredient.\n"
                    + "You cannot decrease it by %3$s%2$s.";
    private static final String MESSAGE_INGREDIENT_DECREMENTED = "I've decremented this ingredient by %1$s%2$s:\n"
            + "\n%3$s";

    private final String ingredientName;
    private final int quantity;

    /**
     * Initialises command and stores relevant parameters.
     *
     * @param ingredientName Name of ingredient to be incremented.
     * @param quantity Quantity to increment by.
     */
    public DecrementCommand(String ingredientName, int quantity) {
        assert ingredientName != null && !ingredientName.isBlank()
                : "Ingredient name cannot be null and cannot be blank";
        assert quantity >= 0 : "Quantity cannot be negative";
        this.ingredientName = ingredientName;
        this.quantity = quantity;
    }

    @Override
    public String execute(IngredientRepository ingredients, RecipeList recipes) {
        assert ingredients != null : "Ingredient repository cannot be null";
        final IngredientStorage ingredientStorage;
        try {
            ingredientStorage = ingredients.find(ingredientName);
        } catch (NotFoundException e) {
            return MESSAGE_INGREDIENT_NOT_FOUND;
        }
        try {
            ingredientStorage.remove(quantity);
        } catch (IllegalValueException e) {
            if (quantity == 0) {
                return MESSAGE_QUANTITY_ZERO;
            } else {
                return String.format(MESSAGE_QUANTITY_TOO_MUCH, ingredientStorage.getQuantity(),
                        ingredientStorage.getFormattedUnit(), quantity);
            }
        }
        return String.format(MESSAGE_INGREDIENT_DECREMENTED, quantity, ingredientStorage.getFormattedUnit(),
                ingredientStorage);
    }
}
