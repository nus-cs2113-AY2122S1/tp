package seedu.situs.command;

import seedu.situs.exceptions.SitusException;
import seedu.situs.ingredients.IngredientList;

import java.io.IOException;

//@@author AayushMathur7
/**
 * Represents a command to subtract given amount from an ingredient.
 */
public class SubtractCommand extends Command {

    private static final String STORAGE_ERROR_MESSAGE = "Cannot remove ingredient from memory file!";
    private static final String INVALID_NUMBER = "Ingredient number does not exist!";

    private int groupNumber;
    private Double subtractAmount;

    /**
     * Constructor method for <code>SubtractCommand</code>.
     * @param groupNumber the ingredient name to subtract amount from
     * @param subtractAmount the amount to be subtracted
     */
    public SubtractCommand(int groupNumber, Double subtractAmount) {
        this.groupNumber = groupNumber;
        this.subtractAmount = subtractAmount;
    }

    @Override
    public String run() throws SitusException {
        try {
            String resultMsg;
            resultMsg = "Got it. " + subtractAmount
                    + " kg has been subtracted from "
                    + IngredientList.getInstance().getIngredientGroup(groupNumber).getIngredientGroupName();
            IngredientList.getInstance()
                    .subtractIngredientFromGroup(groupNumber, subtractAmount);
            return resultMsg;
        } catch (IndexOutOfBoundsException e) {
            throw new SitusException(INVALID_NUMBER);
        } catch (IOException e) {
            throw new SitusException(STORAGE_ERROR_MESSAGE);
        }
    }
}