package seedu.duke.command;

import seedu.duke.exceptions.DukeException;
import seedu.duke.ingredients.Ingredient;
import seedu.duke.ingredients.IngredientList;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ExpireCommand implements Command {

    private final LocalDate expireBeforeDate;
    private static final String EXPIRE_MESSAGE = "The following items expire by ";
    private static final String LIST_NEWLINE_INDENT = "\n" + "\t";

    public ExpireCommand(LocalDate expireBeforeDate) {
        this.expireBeforeDate = expireBeforeDate;
    }


    @Override
    public String run() throws DukeException {
        String resultMsg = EXPIRE_MESSAGE + Ingredient.dateToString(expireBeforeDate) + ":\n";

        int currentStock = IngredientList.getInstance().getInventoryStock();
        for (int i = 0; i < currentStock; i++) {
            if (ChronoUnit.DAYS.between(IngredientList.getInstance().getIngredientExpiry(i + 1),
                    expireBeforeDate) >= 0) {
                resultMsg += IngredientList.getInstance().getIngredientInfo(i + 1);
                if (i < currentStock - 1) {
                    resultMsg += LIST_NEWLINE_INDENT;
                }
            }
        }
        return resultMsg;
    }
}
