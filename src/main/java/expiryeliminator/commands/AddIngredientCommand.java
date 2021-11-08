package expiryeliminator.commands;

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.IngredientStorage;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.DuplicateDataException;

/**
 * Adds an ingredient, together with its associated quantity and expiry date.
 */
public class AddIngredientCommand extends Command {
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an ingredient to the ingredient repository.\n"
            + "Ingredient names are case insensitive and must be unique.\n"
            + "Parameters: i/INGREDIENT [u/UNIT]\n"
            + "Example: " + COMMAND_WORD + " i/Chicken u/grams";

    private static final String MESSAGE_INGREDIENT_ADDED = "I've added this ingredient:\n" + "\n%1$s\n"
            + "Now you have %2$s ingredient(s)";
    private static final String MESSAGE_INGREDIENT_ALREADY_EXISTS = "Unable to add ingredient: %1$s\n"
            + "You already have it in your list\n"
            + "If you are trying to add quantities for an ingredient, please use the `increment` command instead";

    private final String name;
    private final String unit;


    /**
     * Instantiates ingredient with the specified name and unit.
     *
     * @param name Name of ingredient to be added.
     * @param unit Unit for ingredient to be added. Can be null.
     */
    public AddIngredientCommand(String name, String unit) {
        assert name != null && !name.isBlank() : "Ingredient name cannot be null and cannot be blank";
        this.name = name;
        this.unit = unit;
    }

    @Override
    public String execute(IngredientRepository ingredients, RecipeList recipes) {
        assert ingredients != null : "Ingredient repository cannot be null";
        final IngredientStorage ingredientStorage;
        try {
            ingredientStorage = ingredients.add(name, unit);
        } catch (DuplicateDataException e) {
            return String.format(MESSAGE_INGREDIENT_ALREADY_EXISTS, name);
        }
        return String.format(MESSAGE_INGREDIENT_ADDED, ingredientStorage, ingredients.size());
    }
}
