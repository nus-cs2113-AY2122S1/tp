package seedu.duke.command;

import seedu.situs.command.Command;
import seedu.situs.exceptions.DukeException;

public class DeleteCommand extends Command {

    private static final String DELETE_MESSAGE = "Got it. This ingredient has been removed:\n" + "\t";

    private int ingredientNumber;

    /**
     * Constructor method for <code>DeleteCommand</code>.
     *
     * @param ingredientNumber the ingredient number to remove from the list
     */
    public DeleteCommand(int ingredientNumber) {
        this.ingredientNumber = ingredientNumber;
    }

    @Override
    public String run() throws DukeException {
        /*Ingredient removedIngredient = IngredientGroup.remove(this.ingredientNumber);
        String resultMsg = DELETE_MESSAGE + removedIngredient.toString();
        return resultMsg;*/
        return "";
    }

}
