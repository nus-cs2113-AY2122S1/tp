package seedu.foodorama.command;

import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;

public class EditIngrCommand extends Command {
    private static final Ui ui = new Ui();

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        try {
            int ingredientIndex = Integer.parseInt(parameters.get(0)) - 1;
            IngredientList.editName(ingredientIndex);
        } catch (NumberFormatException e) {
            throw new FoodoramaException(ui.getIngrIndexMissingMsg());
        }

    }
}
