package seedu.situs.command;

import seedu.situs.exceptions.DukeException;
import seedu.situs.ingredients.Ingredient;
import seedu.situs.ingredients.IngredientList;

import java.io.IOException;

public class UpdateCommand extends Command {

    private static final String UPDATE_MESSAGE = "Got it. This ingredient has been updated:\n" + "\t";
    private static final String LIST_EMPTY_MESSAGE = "Your inventory is empty!";
<<<<<<< HEAD:src/main/java/seedu/duke/command/UpdateCommand.java
    private static final String INCORRECT_UPDATE = "Your inventory is empty!";
=======
    private static final String INVALID_NUMBER = "Ingredient number does not exist!";
>>>>>>> 9ac62d704a800cb53eec8dfc24ebe0cd5e1c3d83:src/main/java/seedu/situs/command/UpdateCommand.java

    private Ingredient updatedIngredient;

    public UpdateCommand(Ingredient ingredient) {

        this.updatedIngredient = ingredient;
    }

    @Override
    public String run() throws DukeException {
        /*try {
            String resultMsg = "";
            int i;

            if (IngredientGroup.getIngredientGroupSize() == 0) {
                resultMsg = LIST_EMPTY_MESSAGE;
                return resultMsg;
            }


<<<<<<< HEAD:src/main/java/seedu/duke/command/UpdateCommand.java
            for (i = 0; i < IngredientList.getInstance().getInventoryStock(); i++) {
                if (this.updatedIngredient.getName().equals((IngredientList.getInstance()).get(i + 1).getName())) {
                        IngredientList.getInstance().set(i, this.updatedIngredient);
                        resultMsg = UPDATE_MESSAGE + this.updatedIngredient.toString();
                    }

=======
            for (i = 0; i < IngredientGroup.getIngredientGroupSize(); i++) {
                if (this.updatedIngredient.getName().equals((IngredientGroup.getInstance()).get(i + 1).getName())) {
                    IngredientGroup.getInstance().set(i, this.updatedIngredient);
                    resultMsg = UPDATE_MESSAGE + this.updatedIngredient.toString();
>>>>>>> 9ac62d704a800cb53eec8dfc24ebe0cd5e1c3d83:src/main/java/seedu/situs/command/UpdateCommand.java
                }
            return resultMsg;
            } catch (IOException e) {
            throw new DukeException("Cannot write ingredient to memory!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INVALID_NUMBER);
        }*/
        return "";
    }

}
