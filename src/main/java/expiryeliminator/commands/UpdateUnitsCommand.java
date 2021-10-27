package expiryeliminator.commands;

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.IngredientStorage;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.NotFoundException;
import expiryeliminator.storage.SaveData;

/**
 * Updates the units of ingredients both in the ingredient repository and in recipes.
 */
public class UpdateUnitsCommand extends Command {

    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "update units";
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Updates the unit of a specified ingredient.\n"
                    + "Parameters: i/INGREDIENT u/UNITS\n"
                    + "Example: " + COMMAND_WORD + " i/Chicken u/kilograms";

    private static final String MESSAGE_INGREDIENT_NOT_FOUND = "Sorry. No matching ingredients found!";
    private static final String MESSAGE_INGREDIENT_UNIT_UPDATED = "The units for this "
            + "ingredient has been updated to %1$s.\n";
    private static final String MESSAGE_INGREDIENT_UNIT_DELETED = "The units for this "
            + "ingredient has been deleted";

    private final String ingredientName;
    private final String newUnit;

    /**
     * Initialises command and stores relevant parameters.
     *
     * @param ingredientName Name of ingredient to be incremented.
     * @param newUnit New unit to modify to.
     */
    public UpdateUnitsCommand(String ingredientName, String newUnit) {
        assert ingredientName != null && !ingredientName.isBlank()
                : "Ingredient name cannot be null and cannot be blank";
        this.ingredientName = ingredientName;
        this.newUnit = newUnit;
    }

    @Override
    public String execute(IngredientRepository ingredients, RecipeList recipes) {
        assert ingredients != null : "Ingredient repository cannot be null";
        final IngredientStorage ingredientStorage;
        //find ingredient in ingredient repo
        try {
            ingredientStorage = ingredients.find(ingredientName);
        } catch (NotFoundException e) {
            return MESSAGE_INGREDIENT_NOT_FOUND;
        }
        //update units of ingredient in ingredient repo
        ingredientStorage.updateUnits(newUnit);
        //update units of ingredient in recipe list
        recipes.updateUnits(ingredientName, newUnit);
        
        SaveData.saveIngredientRepoToFile(ingredients);

        if (newUnit == null) {
            return MESSAGE_INGREDIENT_UNIT_DELETED;
        } else {
            return String.format(MESSAGE_INGREDIENT_UNIT_UPDATED, newUnit);
        }


    }
}
