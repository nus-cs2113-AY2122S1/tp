package expiryeliminator.commands;

import expiryeliminator.data.IngredientList;
import expiryeliminator.data.RecipeList;

/**
 * Lists all ingredient, together with its associated quantity and expiry date.
 */
public class ListCommand extends Command {

    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists out all the ingredients in the repository. ";

    public static final String MESSAGE_SHOW_WHOLE_LIST = "Here are the ingredients in your list:\n" + "\n%1$s\n"
            + "You have a total of %2$s ingredient(s)";

    public static final String MESSAGE_EMPTY_INGREDIENT_LIST = "The recipe list is currently empty!";



    @Override
    public String execute(IngredientList ingredientList, RecipeList recipes) {
        if(ingredientList.size() == 0) {
            return MESSAGE_EMPTY_INGREDIENT_LIST;
        } else {
            assert ingredientList.size() > 0;
            return String.format(MESSAGE_SHOW_WHOLE_LIST, ingredientList.printWholeList(), ingredientList.size());
        }
    }
}
