package seedu.situs.command;

import seedu.situs.exceptions.DukeException;
import seedu.situs.ingredients.Ingredient;
import seedu.situs.ingredients.IngredientGroup;
import seedu.situs.ingredients.IngredientList;

import java.util.ArrayList;

public class AlertLowStockCommand extends Command {

    private static double lowStockThreshold = 1;
    private static final String LIST_NEWLINE_INDENT = "\n" + "\t";

    public static void setLowStockThreshold(double lowStockThreshold) {
        AlertLowStockCommand.lowStockThreshold = lowStockThreshold;
    }

    @Override
    public String run() throws DukeException {
        int lowStockCount = 0;
        String resultMsg = "";
        ArrayList<IngredientGroup> ingredientList = IngredientList.getInstance().getIngredientList();

        for (IngredientGroup ingredientGroup : ingredientList) {
            if (ingredientGroup.getTotalAmount() <= lowStockThreshold) {
                resultMsg += ingredientGroup + LIST_NEWLINE_INDENT;
                lowStockCount += 1;
            }
        }

        if (lowStockCount == 0) {
            resultMsg = "No ingredients with stock less than " + lowStockThreshold + " kg";
            return resultMsg;
        }

        return "There are " + lowStockCount
                + " ingredients with stock less than " + lowStockThreshold + " kg" + LIST_NEWLINE_INDENT + resultMsg;
    }
}

