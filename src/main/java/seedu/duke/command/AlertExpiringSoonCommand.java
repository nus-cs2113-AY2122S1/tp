package seedu.duke.command;

import seedu.duke.exceptions.DukeException;
import seedu.duke.ingredients.Ingredient;
import seedu.duke.ingredients.IngredientList;
import seedu.duke.localtime.CurrentDate;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class AlertExpiringSoonCommand implements Command {
    private static final long THRESHOLD_EXPIRY = 5;

    @Override
    public String run() throws DukeException {
        int i;
        String resultMsg;
        ArrayList<Ingredient> ingredientList = IngredientList.getInstance().getIngredientList();
        ArrayList<Ingredient> expiringIngredients = new ArrayList<>();

        for (i = 0; i < ingredientList.size(); i++) {
            if (ChronoUnit.DAYS.between(CurrentDate.getCurrentDate(), ingredientList.get(i).getExpiry())
                    <= THRESHOLD_EXPIRY) {
                expiringIngredients.add(ingredientList.get(i));
            }
        }

        if (expiringIngredients.size() == 0) {
            resultMsg = "No expiring ingredients in " + THRESHOLD_EXPIRY + " days time";
            return resultMsg;
        }

        resultMsg = "There are expiring ingredients in your inventory:" + '\n' + '\t';
        for (i = 0; i < expiringIngredients.size() - 1; i++) {
            resultMsg += (i + 1) + ". " + expiringIngredients.get(i).toString() + '\n' + '\t';
        }
        resultMsg = resultMsg + expiringIngredients.size() + ". " + expiringIngredients.get(i).toString();

        return resultMsg;
    }
}
