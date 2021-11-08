package expiryeliminator.commands;

//@@author bernardboey-reused
// Reused from
// https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/commands/IncorrectCommand.java
// with minor modifications

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.RecipeList;

/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */
public class IncorrectCommand extends Command {

    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public String execute(IngredientRepository ingredientRepository, RecipeList recipes) {
        return feedbackToUser;
    }

}
//@@author
