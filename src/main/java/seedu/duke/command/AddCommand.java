package seedu.duke.command;

import seedu.duke.ingredients.Ingredient;
import seedu.duke.ingredients.IngredientList;

public class AddCommand implements Command {

    private static final String ADDED_MESSAGE = "Got it. This ingredient has been added to the inventory:\n" + "\t";

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
        String resultMsg = ADDED_MESSAGE
                + ingredient.toString() + '\n'
                + "Current inventory has " + IngredientList.getInstance().getInventoryStock()
                + " items.";
        return resultMsg;
    }
}
