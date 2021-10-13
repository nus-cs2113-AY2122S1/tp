package expiryeliminator.commands;

import expiryeliminator.data.IngredientList;
import expiryeliminator.data.RecipeList;

public class HelpCommand extends Command {
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public String execute(IngredientList ingredientList, RecipeList recipes) {
        String commandResult = AddIngredientCommand.MESSAGE_USAGE
                        + "\n\n" + AddRecipeCommand.MESSAGE_USAGE
                        + "\n\n" + DeleteIngredientCommand.MESSAGE_USAGE
                        + "\n\n" + DeleteRecipeCommand.MESSAGE_USAGE
                        + "\n\n" + DecrementCommand.MESSAGE_USAGE
                        + "\n\n" + IncrementCommand.MESSAGE_USAGE
                        + "\n\n" + ListRecipeCommand.MESSAGE_USAGE
                        + "\n\n" + ViewRecipeCommand.MESSAGE_USAGE;
        return commandResult;
    }
}
