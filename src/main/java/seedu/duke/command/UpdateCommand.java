package seedu.duke.command;

import seedu.duke.exceptions.DukeException;
import seedu.duke.ingredients.Ingredient;
import seedu.duke.ingredients.IngredientList;

public class UpdateCommand implements Command {

    private static final String UPDATE_MESSAGE = "Got it. This ingredient has been updated:\n" + "\t";
    private static final String LIST_EMPTY_MESSAGE = "Your inventory is empty!";

    private Ingredient updatedIngredient;

    public UpdateCommand(Ingredient ingredient) {

        this.updatedIngredient = ingredient;
    }

    @Override
    public String run() throws DukeException {
        String resultMsg = "";
        int i;

        if (IngredientList.getInstance().getInventoryStock() == 0) {
            resultMsg = LIST_EMPTY_MESSAGE;
            return resultMsg;
        }


        for (i = 0; i < IngredientList.getInstance().getInventoryStock(); i++) {
            if (this.updatedIngredient.getName().equals((IngredientList.getInstance()).get(i + 1).getName())) {
                IngredientList.getInstance().set(i, this.updatedIngredient);
                resultMsg = UPDATE_MESSAGE + this.updatedIngredient.toString();
            }
        }
        return resultMsg;
    }
}
