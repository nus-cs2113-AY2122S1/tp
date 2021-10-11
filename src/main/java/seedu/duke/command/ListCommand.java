package seedu.duke.command;

import seedu.duke.exceptions.DukeException;
import seedu.duke.ingredients.IngredientList;

/**
 * Represents a list command.
 */
public class ListCommand implements Command {

    @Override
    public String run() throws DukeException {
        String resultMsg = "";
        int i;

        if (IngredientList.getInstance().getInventoryStock() == 0) {
            resultMsg = "Inventory is empty!";
            return resultMsg;
        }

        assert (IngredientList.getInstance().getInventoryStock() > 0);

        for (i = 0; i < IngredientList.getInstance().getInventoryStock() - 1; i++) {
            resultMsg += (i + 1) + "."
                    + IngredientList.getInstance().getIngredientInfo(i + 1)
                    + '\n';
        }
        resultMsg = resultMsg + IngredientList.getInstance().getInventoryStock() + "."
                + IngredientList.getInstance().getIngredientInfo(i + 1);

        return resultMsg;
    }
}
