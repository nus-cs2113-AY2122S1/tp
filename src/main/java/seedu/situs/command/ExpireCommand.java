package seedu.situs.command;

import seedu.situs.exceptions.DukeException;
import seedu.situs.ingredients.Ingredient;
import seedu.situs.ingredients.IngredientList;

import java.time.LocalDate;
import java.util.ArrayList;

public class ExpireCommand extends Command {

    private final LocalDate expireBeforeDate;
    private static final String LIST_NEWLINE_INDENT = "\n" + "\t";

    public ExpireCommand(LocalDate expireBeforeDate) {
        this.expireBeforeDate = expireBeforeDate;
    }

    @Override
    public String run() throws DukeException {
        int expiringCount = 0;
        String resultMsg = "";
        ArrayList<Ingredient> ingredientList = IngredientList.getInstance().getIngredientList();

        for (Ingredient ingredient : ingredientList) {
            if (getNumDaysBetween(ingredient.getExpiry(), expireBeforeDate) >= 0) {
                resultMsg += ingredient + LIST_NEWLINE_INDENT;
                expiringCount += 1;
            }
        }

        if (expiringCount == 0) {
            resultMsg = "No ingredients expiring by " + expireBeforeDate;
            return resultMsg;
        }

        return "There are " + expiringCount
                + " ingredients expiring by: " + expireBeforeDate + LIST_NEWLINE_INDENT + resultMsg;
    }
}
