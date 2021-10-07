package expiryeliminator.commands;

import expiryeliminator.data.Ingredient;
import expiryeliminator.data.IngredientList;

public class ViewIngredientCommand extends Command{
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "view";

    private static final String MESSAGE_SHOW_Ingredient = "Here is the ingredient in your list:\n" + "\n%1$s\n";

    private final String ingredientDescription;

    public ViewIngredientCommand( String ingredientDescription) {
        this.ingredientDescription = ingredientDescription;
    }

    @Override
    public String execute(IngredientList ingredientList) {
        Ingredient ingredient = ingredientList.findIngredient(ingredientDescription);
        return String.format(MESSAGE_SHOW_Ingredient, ingredient);
    }
}
