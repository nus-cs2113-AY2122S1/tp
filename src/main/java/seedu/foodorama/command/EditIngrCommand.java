package seedu.foodorama.command;

import seedu.foodorama.IngredientList;
import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;

public class EditIngrCommand extends Command{
    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        int ingredientIndex = Integer.parseInt(parameters.get(0)) - 1;
        IngredientList.editName(ingredientIndex);
    }
}
