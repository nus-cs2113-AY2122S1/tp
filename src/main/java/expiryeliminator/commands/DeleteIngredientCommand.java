package expiryeliminator.commands;

import expiryeliminator.data.Ingredient;
import expiryeliminator.data.IngredientList;
import expiryeliminator.data.RecipeList;
import expiryeliminator.data.exception.NotFoundException;

/**
 * Deletes an ingredient.
 */
public class DeleteIngredientCommand extends Command {
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "delete";

    private static final String MESSAGE_INGREDIENT_NOT_FOUND = "Sorry. No matching ingredients found!";
    private static final String MESSAGE_INGREDIENT_DELETED = "I've deleted this ingredient for you:\n" + "%1$s\n"
            + "Now you have %2$s ingredient(s)";

    private final String ingredientName;

    public DeleteIngredientCommand(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @Override
    public String execute(IngredientList ingredientList, RecipeList recipes) {
        final Ingredient ingredient;
        try {
            ingredient = ingredientList.remove(ingredientName);
        } catch (NotFoundException e) {
            return MESSAGE_INGREDIENT_NOT_FOUND;
        }
        return String.format(MESSAGE_INGREDIENT_DELETED, ingredient, ingredientList.size());
    }
}
