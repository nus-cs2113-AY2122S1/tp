package expiryeliminator.commands;

//@@author bernardboey-reused
// Reused from https://github.com/bernardboey/ip/blob/master/src/main/java/duke/commands/Command.java
// with minor modifications

import expiryeliminator.data.IngredientRepository;
import expiryeliminator.data.RecipeList;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Executes the appropriate actions.
     *
     * @param ingredients Ingredient repository.
     * @param recipes Recipe List.
     * @return Feedback about what was executed.
     */
    public abstract String execute(IngredientRepository ingredients, RecipeList recipes);

    /**
     * Indicates whether the command is an exit command.
     *
     * @return {@code true} if and only if the command is an exit command; {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
//@@author
