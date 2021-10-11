package seedu.duke.command;

import seedu.duke.command.Command;
import seedu.duke.exceptions.DukeException;
import seedu.duke.ingredients.Ingredient;
import seedu.duke.ingredients.IngredientList;
import java.util.ArrayList;

public class UpdateCommand implements Command {
    private int ingredientNumber;
    private Ingredient updatedIngredient;

    public UpdateCommand(int ingredientNumber, String ingredientName, Double ingredientAmount, String ingredientUnit, String ingredientExpiry) {
        this.ingredientNumber = ingredientNumber;
        this.updatedIngredient.setName(ingredientName);
        this.updatedIngredient.setAmount(ingredientAmount);
        this.updatedIngredient.setUnits(ingredientUnit);
        this.updatedIngredient.setExpiry(ingredientExpiry);
    }

    @Override
    public String run() {
        IngredientList.getInstance().set(ingredientNumber, updatedIngredient);
        String resultMsg = "Noted. This has been updated:\n"
                + "t" + updatedIngredient.toString();
        return resultMsg;
    }
}
