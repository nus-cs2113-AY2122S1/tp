package seedu.situs.command;

import seedu.situs.exceptions.SitusException;
import seedu.situs.ingredients.Ingredient;
import seedu.situs.ingredients.IngredientGroup;
import seedu.situs.ingredients.IngredientList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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
        ArrayList<Ingredient> expiringIngredients = new ArrayList<>();
        String resultMsg = "";
        ArrayList<IngredientGroup> ingredientList = IngredientList.getInstance().getIngredientList();

        for (IngredientGroup ingredientGroup : ingredientList) {
            int entryCount = ingredientGroup.getIngredientGroupSize();
            for (int i = 0; i < entryCount; i++) {
                if (getNumDaysBetween(ingredientGroup.getIngredientExpiry(i + 1),
                        expireBeforeDate) < 0) {
                    continue;
                }
                expiringIngredients.add(ingredientGroup.get(i + 1));
                expiringCount += 1;

            }
        }

        if (expiringCount == 0) {
            resultMsg = "No ingredients expiring by " + Ingredient.dateToString(expireBeforeDate);
            return resultMsg;
        }
        expiringIngredients.sort(Comparator.comparing(Ingredient::getExpiry));
        for (Ingredient ingredient : expiringIngredients) {
            resultMsg += ingredient.getName() + " | " + ingredient + LIST_NEWLINE_INDENT;
        }
        return "There are " + expiringCount
                + " ingredients expiring by: " + Ingredient.dateToString(expireBeforeDate)
                + LIST_NEWLINE_INDENT + resultMsg.trim();
    }
}
