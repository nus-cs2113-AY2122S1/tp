package seedu.duke.command;

import seedu.duke.exceptions.DukeException;
import seedu.duke.ingredients.Ingredient;
import seedu.duke.ingredients.IngredientList;
import seedu.duke.localtime.CurrentDate;

import java.time.LocalDate;
import java.util.ArrayList;

public class ExpireAtStartupCommand extends Command {
    private static long expiryThreshold = 5;

    public static void setExpiryThreshold(long expiryThreshold) {
        ExpireAtStartupCommand.expiryThreshold = expiryThreshold;
    }

    @Override
    public String run() throws DukeException {
        LocalDate expiryDateThreshold = CurrentDate.getCurrentDate().plusDays(expiryThreshold);

        return new ExpireCommand(expiryDateThreshold).run();
    }
}
