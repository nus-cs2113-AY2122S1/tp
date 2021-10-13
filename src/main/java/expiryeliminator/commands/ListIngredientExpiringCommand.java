package expiryeliminator.commands;

import expiryeliminator.data.IngredientList;
import expiryeliminator.data.RecipeList;


/**
 * Lists ingredients expiring within the week, together with its associated quantity and expiry date.
 */
public class ListIngredientExpiringCommand extends Command {

    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "list expiring";

    public static final String MESSAGE_SHOW_WHOLE_LIST = "Here are the expiring ingredients in your list:\n"
            + "\n%1$s\n";

    @Override
    public String execute(IngredientList ingredientList, RecipeList recipes) {
        return String.format(MESSAGE_SHOW_WHOLE_LIST, ingredientList.findExpiringIngredients());

    }
}
