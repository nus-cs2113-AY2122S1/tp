package seedu.duke.command;

import seedu.duke.exceptions.DukeException;
import seedu.duke.ingredients.IngredientList;

/**
 * Represents a list command.
 */
public class ListCommand implements Command {

    private static final String LIST_EMPTY_MESSAGE = "Your inventory is empty!";
    private static final String LIST_MESSAGE = "Here is the list of the ingredients currently in inventory:\n" + "\t";

    @Override
    public String run() throws DukeException {
        String resultMsg = "";
        int i;

        if (IngredientList.getInstance().getInventoryStock() == 0) {
            resultMsg = LIST_EMPTY_MESSAGE;
            return resultMsg;
        }

        assert (IngredientList.getInstance().getInventoryStock() > 0);

        for (i = 0; i < IngredientList.getInstance().getInventoryStock(); i++) {
            resultMsg += LIST_MESSAGE + (i + 1) + ". "
                    + IngredientList.getInstance().getIngredientInfo(i + 1);
        }

        return resultMsg;
    }
}
