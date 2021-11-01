package expiryeliminator.commands;

//@@author JoshHDs

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.RecipeList;

/**
 * Lists all ingredient, together with its associated quantity and expiry date.
 */
public class ListCommand extends Command {

    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists out all the ingredients in the repository.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SHOW_WHOLE_LIST = "Here are the ingredients in your list:\n" + "%1$s\n"
            + "You have a total of %2$s ingredient(s)";

    public static final String MESSAGE_EMPTY_INGREDIENT_LIST = "The ingredient repository is currently empty!";


    @Override
    public String execute(IngredientRepository ingredientRepository, RecipeList recipes) {
        if (ingredientRepository.size() == 0) {
            return MESSAGE_EMPTY_INGREDIENT_LIST;
        } else {
            assert ingredientRepository.size() > 0;
            return String.format(MESSAGE_SHOW_WHOLE_LIST, ingredientRepository.printWholeList(),
                    ingredientRepository.size());
        }
    }
}
