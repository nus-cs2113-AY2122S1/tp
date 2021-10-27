package seedu.situs.command;

import seedu.situs.exceptions.SitusException;
import seedu.situs.ingredients.Ingredient;
import seedu.situs.ingredients.IngredientList;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeleteCommand extends Command {

    private static final String DELETE_MESSAGE = "Got it. This ingredient has been removed:\n" + "\t";
    private static final String STORAGE_ERROR_MESSAGE = "Cannot remove ingredient from memory file!";

    private int groupNumber;
    private int ingredientNumber;

    /**
     * Constructor method for <code>DeleteCommand</code>.
     *
     * @param groupNumber the group number of the ingredient to remove from the list
     * @param ingredientNumber the ingredient number to remove
     */
    public DeleteCommand(int groupNumber, int ingredientNumber) {
        this.groupNumber = groupNumber;
        this.ingredientNumber = ingredientNumber;
    }

    @Override
    public String run() throws SitusException {
        try {
            String resultMsg;
            Ingredient removedIngredient = IngredientList.getInstance()
                    .removeIngredientFromGroup(groupNumber, ingredientNumber);

            resultMsg = DELETE_MESSAGE + removedIngredient.getName() + " | " + removedIngredient;
            return resultMsg;
        } catch (IOException e) {
            throw new SitusException(STORAGE_ERROR_MESSAGE);
        }
    }
}
