package seedu.duke.command;

import seedu.duke.command.Command;
import seedu.duke.exceptions.DukeException;
import seedu.duke.ingredients.Ingredient;
import seedu.duke.ingredients.IngredientList;

public class AddCommand implements Command {
    private Ingredient ingredient;

    /**
     * Constructor method for <code>AddCommand</code>.
     * @param i the ingredient to add
     */
    public AddCommand(Ingredient i) {
        this.ingredient = i;
    }

    @Override
    public String run() {
        IngredientList.getInstance().add(this.ingredient);
        String resultMsg = "Got it. This ingredient has been added to the inventory:\n"
                + "\t" + ingredient.toString() + '\n'
                + "Currently inventory has " + IngredientList.getInstance().getInventoryStock()
                + " items.";
        return resultMsg;
    }
}
