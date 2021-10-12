package seedu.duke.command;

import seedu.duke.exceptions.DukeException;
import seedu.duke.ingredients.Ingredient;
import seedu.duke.ingredients.IngredientList;

public class UpdateCommand implements Command {

    private static final String UPDATE_MESSAGE = "Got it. This ingredient has been updated:\n" + "\t";

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
    public String run() throws DukeException {
        IngredientList.getInstance().set(ingredientNumber, updatedIngredient);
        String resultMsg = UPDATE_MESSAGE + updatedIngredient.toString();
        return resultMsg;
    }
}
