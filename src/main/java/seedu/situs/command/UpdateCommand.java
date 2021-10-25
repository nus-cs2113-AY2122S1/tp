package seedu.situs.command;

import seedu.situs.exceptions.SitusException;
import seedu.situs.ingredients.Ingredient;
import seedu.situs.ingredients.IngredientList;

public class UpdateCommand extends Command {

    private static final String UPDATE_MESSAGE = "Got it. This ingredient has been updated:\n" + "\t";
    private static final String LIST_EMPTY_MESSAGE = "Your inventory is empty!";
    private static final String INVALID_NUMBER = "Ingredient number does not exist!";

    private Ingredient updatedIngredient;

    public UpdateCommand(Ingredient ingredient) {

        this.updatedIngredient = ingredient;
    }

    @Override
    public String run() throws SitusException {
        try {
            String resultMsg;
            if (IngredientList.getInstance().getSize() == 0) {
                resultMsg = LIST_EMPTY_MESSAGE;
                return resultMsg;
            }
            IngredientList.getInstance().update(this.updatedIngredient);
            resultMsg = UPDATE_MESSAGE + updatedIngredient.toString();
            return resultMsg;

        } catch (IndexOutOfBoundsException e) {
            throw new SitusException(INVALID_NUMBER);
        }
    }

}