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
    private static final String INVALID_DATE_FORMAT = "Please write the date in this format: dd-mm-yyyy\n"
            + "e.g 14/10/2021";

    private String ingredientName;
    private String ingredientExpiryDate;

    /**
     * Constructor method for <code>DeleteCommand</code>.
     *
     * @param ingredientName the ingredient name to remove from the list
     * @param ingredientExpiryDate the ingredient expiry date as String
     */
    public DeleteCommand(String ingredientName, String ingredientExpiryDate) {
        this.ingredientName = ingredientName.substring(0, 1).toUpperCase() + ingredientName.substring(1);
        this.ingredientExpiryDate = ingredientExpiryDate;
    }

    @Override
    public String run() throws SitusException {
        try {
            String resultMsg;
            LocalDate expiryDate = Ingredient.stringToDate(ingredientExpiryDate);
            Ingredient removedIngredient = IngredientList.getInstance()
                    .removeIngredientFromGroup(ingredientName, expiryDate);

            resultMsg = DELETE_MESSAGE + removedIngredient.getName() + " | " + removedIngredient.toString();
            return resultMsg;
        } catch (DateTimeParseException e) {
            throw new SitusException(INVALID_DATE_FORMAT);
        } catch (IOException e) {
            throw new SitusException(STORAGE_ERROR_MESSAGE);
        }
    }
}
