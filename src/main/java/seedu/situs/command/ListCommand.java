package seedu.situs.command;

import seedu.situs.exceptions.DukeException;
import seedu.situs.ingredients.IngredientList;

/**
 * Represents a list command.
 */
public class ListCommand extends Command {

    private static final String LIST_EMPTY_MESSAGE = "Your inventory is empty!";
    private static final String LIST_MESSAGE = "Here is the list of the ingredients currently in inventory:\n" + "\t";
    private static final String LIST_NEWLINE_INDENT = "\n" + "\t";

    @Override
    public String run() throws DukeException {
        String resultMsg = "";

        if (IngredientList.getInstance().getInventoryStock() == 0) {
            resultMsg = LIST_EMPTY_MESSAGE;
            return resultMsg;
        }

        assert (IngredientList.getInstance().getInventoryStock() > 0);
        assert (IngredientList.getInstance() != null);

        int currentStock = IngredientList.getInstance().getInventoryStock();
        resultMsg = LIST_MESSAGE;
        for (int i = 0; i < currentStock; i++) {
            resultMsg +=  (i + 1) + ". "
                    + IngredientList.getInstance().getIngredientInfo(i + 1);
            if (i < currentStock - 1) {
                resultMsg += LIST_NEWLINE_INDENT;
            }
        }

        return resultMsg;
    }
}
