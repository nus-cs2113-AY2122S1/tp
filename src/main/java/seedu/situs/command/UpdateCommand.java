package seedu.situs.command;

import seedu.situs.exceptions.SitusException;
import seedu.situs.ingredients.Ingredient;
import seedu.situs.ingredients.IngredientList;

import java.io.IOException;

//@@author AayushMathur7
/**
 * Represents a command to update ingredient amount for different expiry dates.
 */
public class UpdateCommand extends Command {

    private static final String UPDATE_MESSAGE = "Got it. This ingredient has been updated:\n" + "\t";
    private static final String LIST_EMPTY_MESSAGE = "Your inventory is empty!";
    private static final String INVALID_NUMBER = "Ingredient number does not exist!";
    private static final String STORAGE_ERROR = "Cannot update ingredient to memory!";

    private final int groupNumber;
    private final int ingredientNumber;
    private final double newAmount;

    /**
     * Constructor method for <code>UpdateCommand</code>.
     *
     * @param groupNumber the group number of the ingredient to be updated
     * @param ingredientNumber the number of the ingredient to be updated
     * @param newAmount the new amount of the ingredient to be updated
     */
    public UpdateCommand(int groupNumber, int ingredientNumber, double newAmount) {

        this.groupNumber = groupNumber;
        this.ingredientNumber = ingredientNumber;
        this.newAmount = newAmount;
    }

    @Override
    public String run() throws SitusException {
        try {
            String resultMsg;
            if (IngredientList.getInstance().getSize() == 0) {
                resultMsg = LIST_EMPTY_MESSAGE;
                return resultMsg;
            }
            Ingredient updatedIngredient = IngredientList.getInstance()
                    .update(groupNumber, ingredientNumber, newAmount);
            resultMsg = UPDATE_MESSAGE + updatedIngredient.getName() + " | " + updatedIngredient;
            return resultMsg;

        } catch (IndexOutOfBoundsException e) {
            throw new SitusException(INVALID_NUMBER);
        } catch (IOException e) {
            throw new SitusException(STORAGE_ERROR);
        }
    }

}