package seedu.duke.command;

import seedu.duke.exceptions.DukeException;
import seedu.duke.ingredients.Ingredient;
import seedu.duke.ingredients.IngredientList;

import java.io.IOException;

public class UpdateCommand extends Command {

    private static final String UPDATE_MESSAGE = "Got it. This ingredient has been updated:\n" + "\t";
    private static final String LIST_EMPTY_MESSAGE = "Your inventory is empty!";

    private Ingredient updatedIngredient;

    public UpdateCommand(Ingredient ingredient) {

        this.updatedIngredient = ingredient;
    }

    @Override
    public String run() throws DukeException {
        try {
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
        } catch (IOException e) {
            throw new DukeException("Cannot write ingredient to memory!");
        }
    }
}
