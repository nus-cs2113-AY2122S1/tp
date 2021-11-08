package seedu.situs.command;

import seedu.situs.exceptions.SitusException;
import seedu.situs.ingredients.IngredientList;

//@@author datn02

/**
 * Represents a list command.
 */
public class ListCommand extends Command {

    private static final String LIST_EMPTY_MESSAGE = "Your inventory is empty!";
    private static final String LIST_MESSAGE = "Here is the list of the ingredients currently in inventory:\n" + "\t";
    private static final String LIST_NEWLINE_INDENT = "\n" + "\n" + "\t";

    @Override
    public String run() throws SitusException {
        String resultMsg = "";

        if (IngredientList.getInstance().getSize() == 0) {
            resultMsg = LIST_EMPTY_MESSAGE;
            return resultMsg;
        }

        assert (IngredientList.getInstance().getSize() > 0);
        assert (IngredientList.getInstance() != null);

        int currentStock = IngredientList.getInstance().getSize();
        resultMsg = LIST_MESSAGE;

        for (int i = 1; i <= currentStock; i++) {
            resultMsg += i + ". "
                    + IngredientList.getInstance().getIngredientGroup(i).toString();
            if (i < currentStock) {
                resultMsg += LIST_NEWLINE_INDENT;
            }
        }
        return resultMsg;
    }
}
