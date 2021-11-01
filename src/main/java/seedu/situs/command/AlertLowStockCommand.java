package seedu.situs.command;

import seedu.situs.exceptions.SitusException;
import seedu.situs.ingredients.IngredientGroup;
import seedu.situs.ingredients.IngredientList;
import seedu.situs.storage.Storage;

import java.io.IOException;
import java.util.ArrayList;
//@@author mudkip8

public class AlertLowStockCommand extends Command {

    private static double lowStockThreshold = 1.0;
    private static final String LIST_NEWLINE_INDENT = "\n" + "\t";

    public AlertLowStockCommand() throws SitusException {
        try {
            lowStockThreshold = new Storage().loadStockThreshold();
        } catch (IOException | SitusException e) {
            throw new SitusException(e.getMessage());
        }
    }

    public static double getLowStockThreshold() {
        return lowStockThreshold;
    }

    public static void setLowStockThreshold(double lowStockThreshold) throws IOException {
        AlertLowStockCommand.lowStockThreshold = lowStockThreshold;
        new Storage().writeThresholdData();
    }

    @Override
    public String run() throws SitusException {
        int lowStockCount = 0;
        String resultMsg = "";
        ArrayList<IngredientGroup> ingredientList = IngredientList.getInstance().getIngredientList();

        for (IngredientGroup ingredientGroup : ingredientList) {
            if (ingredientGroup.getTotalAmount() > lowStockThreshold) {
                continue;
            }
            String groupName = ingredientGroup.getIngredientGroupName();
            String totalAmountMessage = " | Total Amount: "
                    + String.format("%.3f", ingredientGroup.getTotalAmount()) + " kg";
            resultMsg += groupName + totalAmountMessage + LIST_NEWLINE_INDENT;
            lowStockCount += 1;

        }

        if (lowStockCount == 0) {
            resultMsg = "No ingredients with stock less than " + lowStockThreshold + " kg";
            return resultMsg;
        }

        return "There are " + lowStockCount
                + " ingredients with stock less than " + lowStockThreshold + " kg"
                + LIST_NEWLINE_INDENT + resultMsg.trim();
    }
}

