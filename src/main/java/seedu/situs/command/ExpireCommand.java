package seedu.situs.command;

import seedu.situs.exceptions.SitusException;
import seedu.situs.ingredients.Ingredient;
import seedu.situs.ingredients.IngredientGroup;
import seedu.situs.ingredients.IngredientList;

import java.time.LocalDate;
import java.util.ArrayList;
//@@author mudkip8

public class ExpireCommand extends Command {

    private final LocalDate expireBeforeDate;
    private static final String LIST_NEWLINE_INDENT = "\n" + "\t";

    public ExpireCommand(LocalDate expireBeforeDate) {
        this.expireBeforeDate = expireBeforeDate;
    }

    @Override
    public String run() throws SitusException {
        int expiringCount = 0;
        String resultMsg = "";
        ArrayList<IngredientGroup> ingredientList = IngredientList.getInstance().getIngredientList();

        for (IngredientGroup ingredientGroup : ingredientList) {
            int entryCount = ingredientGroup.getIngredientGroupSize();
            for (int i = 0; i < entryCount; i++) {
                if (getNumDaysBetween(ingredientGroup.getIngredientExpiry(i + 1),
                        expireBeforeDate) >= 0) {
                    resultMsg += ingredientGroup.get(i + 1).getName() + " | "
                            + ingredientGroup.getIngredientInfo(i + 1) + LIST_NEWLINE_INDENT;
                    expiringCount += 1;
                }
            }
        }

        if (expiringCount == 0) {
            resultMsg = "No ingredients expiring by " + Ingredient.dateToString(expireBeforeDate);
            return resultMsg;
        }

        return "There are " + expiringCount
                + " ingredients expiring by: " + Ingredient.dateToString(expireBeforeDate)
                + LIST_NEWLINE_INDENT + resultMsg.trim();
    }
}
