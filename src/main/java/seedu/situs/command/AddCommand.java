package seedu.situs.command;

import seedu.situs.exceptions.SitusException;
import seedu.situs.ingredients.Ingredient;
import seedu.situs.ingredients.IngredientList;

import java.io.IOException;
//@@author ngoivanessa

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
    public String run() throws SitusException {
        try {
            IngredientList.getInstance().add(this.ingredient);
            String resultMsg = ADDED_MESSAGE
                    + ingredient.getName() + " | " + ingredient.toString() + '\n'
                    + "Current inventory has " + IngredientList.getInstance().getSize()
                    + " items." + '\n' + "This ingredient will expire in "
                    + Ingredient.daysFromCurrentDate(ingredient.getExpiry()) + " days.";
            return resultMsg;
        } catch (IOException e) {
            throw new SitusException("Cannot write ingredient to memory!");
        } catch (IndexOutOfBoundsException e) {
            throw new SitusException(e.getMessage());
        }
    }

}
