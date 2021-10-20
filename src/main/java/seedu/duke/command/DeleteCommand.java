package seedu.duke.command;

import seedu.duke.exceptions.DukeException;
import seedu.duke.ingredients.Ingredient;
import seedu.duke.ingredients.IngredientList;

import java.io.IOException;

public class DeleteCommand extends Command {

    private static final String DELETE_MESSAGE = "Got it. This ingredient has been removed:\n" + "\t";
    private static final String INVALID_NUMBER = "Ingredient number does not exist!";

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
        try {
            Ingredient removedIngredient = IngredientList.getInstance().remove(this.ingredientNumber);
            String resultMsg = DELETE_MESSAGE + removedIngredient.toString();
            return resultMsg;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INVALID_NUMBER);
        } catch (IOException e) {
            throw new DukeException("Cannot remove ingredients from memory!");
        }
    }
}
