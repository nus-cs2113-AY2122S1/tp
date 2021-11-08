package expiryeliminator.commands;

//@@author JoshHDs

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.RecipeList;

/**
 * Lists ingredients that have expired, together with its associated quantity and expiry date.
 */
public class ListIngredientsExpiredCommand extends Command {
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "list expired";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists out the ingredients that have expired.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SHOW_WHOLE_LIST = "Here are the expired ingredients in your list:\n"
            + "\n%1$s\n";

    @Override
    public String execute(IngredientRepository ingredientRepository, RecipeList recipes) {
        return String.format(MESSAGE_SHOW_WHOLE_LIST, ingredientRepository.findExpiredIngredients());

    }
}
