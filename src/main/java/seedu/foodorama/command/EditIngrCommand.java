package seedu.foodorama.command;

import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;

public class EditIngrCommand extends Command {
    private static final Ui ui = new Ui();

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        int ingredientIndex;
        if (isNumber(parameters.get(0))) {
            ingredientIndex = Integer.parseInt(parameters.get(0)) - 1;
        }
        else {
            String ingredientName = String.join(" ", parameters);
            if (ingredientName.isBlank()) {
                throw new FoodoramaException(ui.getIngrIndexMissingMsg());
            } else {
                ingredientIndex = IngredientList.find(ingredientName);
            }
        }
        IngredientList.editName(ingredientIndex);
    }



    public boolean isNumber (String number) {
        try {
            int ingredientIndex = Integer.parseInt(number) - 1;
            return true;
        } catch (NumberFormatException ignored) {

        }
        return false;
    }
}
