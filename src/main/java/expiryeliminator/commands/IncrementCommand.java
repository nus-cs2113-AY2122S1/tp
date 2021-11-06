package expiryeliminator.commands;

import java.time.LocalDate;

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.IngredientStorage;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.IllegalValueException;
import expiryeliminator.data.exception.NotFoundException;

/**
 * Increment ingredient by a specified quantity.
 */
public class IncrementCommand extends Command {
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "increment";
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Increases the quantity of a specified ingredient by a specified amount.\n"
                    + "Parameters: i/INGREDIENT q/QUANTITY e/EXPIRY_DATE\n"
                    + "Example: " + COMMAND_WORD + " i/Red Apple q/3 e/2021-12-25";

    private static final String MESSAGE_QUANTITY_ZERO = "Cannot increment by a quantity of zero.";
    private static final String MESSAGE_INGREDIENT_NOT_FOUND = "Sorry. No matching ingredients found!";
    private static final String MESSAGE_INGREDIENT_INCREMENTED = "I've incremented this ingredient by %1$s:\n"
            + "\n%2$s";

    private final String ingredientName;
    private final int quantity;
    private final LocalDate expiryDate;

    /**
     * Initialises command and stores relevant parameters.
     *
     * @param ingredientName Name of ingredient to be incremented.
     * @param quantity Quantity to increment by.
     */
    public IncrementCommand(String ingredientName, int quantity, LocalDate expiryDate) {
        assert ingredientName != null && !ingredientName.isBlank()
                : "Ingredient name cannot be null and cannot be blank";
        assert quantity >= 0 : "Quantity cannot be negative";
        assert expiryDate != null : "Expiry date cannot be null";
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
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
            ingredientStorage.add(quantity, expiryDate);
        } catch (IllegalValueException e) {
            return MESSAGE_QUANTITY_ZERO;
        }
        return String.format(MESSAGE_INGREDIENT_INCREMENTED, quantity, ingredientStorage);
    }
}
