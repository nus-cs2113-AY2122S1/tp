package seedu.duke.command;

import seedu.duke.exceptions.DukeException;
import seedu.duke.ingredients.Ingredient;
import seedu.duke.ingredients.IngredientList;
import seedu.duke.localtime.CurrentDate;

import java.io.IOException;

public class AddCommand extends Command {

    private static final String ADDED_MESSAGE = "Got it. This ingredient has been added to the inventory:\n" + "\t";

    private Ingredient ingredient;

    /**
     * Constructor method for <code>AddCommand</code>.
     *
     * @param i the ingredient to add
     */
    public AddCommand(Ingredient i) {
        this.ingredient = i;
    }

    @Override
    public String run() throws DukeException {
        try {
            IngredientList.getInstance().add(this.ingredient);
            String resultMsg = ADDED_MESSAGE
                    + ingredient.toString() + '\n'
                    + "Current inventory has " + IngredientList.getInstance().getInventoryStock()
                    + " items." + '\n' + "This ingredient will expire in "
                    + Ingredient.daysFromCurrentDate(ingredient.getExpiry()) + " days.";
            return resultMsg;
        } catch (IOException e) {
            throw new DukeException("Cannot write ingredient to memory!");
        }
    }
}
