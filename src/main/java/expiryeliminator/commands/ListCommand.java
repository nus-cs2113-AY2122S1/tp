package expiryeliminator.commands;

import expiryeliminator.data.IngredientList;
import expiryeliminator.data.RecipeList;

/**
 * Lists all ingredient, together with its associated quantity and expiry date.
 */
public class ListCommand extends Command {

    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "list";

    private static final String MESSAGE_SHOW_WHOLE_LIST = "Here are the ingredients in your list:\n" + "\n%1$s\n"
            + "You have a total of %2$s ingredient(s)";




    @Override
    public String execute(IngredientList ingredientList, RecipeList recipes) {
        return String.format(MESSAGE_SHOW_WHOLE_LIST, ingredientList.printWholeList(), ingredientList.size());
    }
}
