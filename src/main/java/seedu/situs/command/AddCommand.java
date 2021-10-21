package seedu.situs.command;

import seedu.situs.exceptions.DukeException;
import seedu.situs.ingredients.Ingredient;
import seedu.situs.ingredients.IngredientList;

import java.io.IOException;

<<<<<<< HEAD:src/main/java/seedu/duke/command/AddCommand.java
//public class AddCommand implements Command {
public class AddCommand implements Command {
=======
public class AddCommand extends Command {
>>>>>>> 9ac62d704a800cb53eec8dfc24ebe0cd5e1c3d83:src/main/java/seedu/situs/command/AddCommand.java

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
                    + ingredient.getName() + " | " + ingredient.toString() + '\n'
                    + "Current inventory has " + IngredientList.getInstance().getSize()
                    + " items." + '\n' + "This ingredient will expire in "
                    + Ingredient.daysFromCurrentDate(ingredient.getExpiry()) + " days.";
            return resultMsg;
        } catch (IOException e) {
            throw new DukeException("Cannot write ingredient to memory!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e.getMessage());
        }
    }

}
