package seedu.foodorama.command;

import seedu.foodorama.*;
import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;

public class SetIngrLimitCommand extends Command {
    private static final Ui ui = new Ui();

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        String dish = String.join(" ", parameters);
        int ingredientIndex = IngredientList.find(dish);

        if (ingredientIndex == -1) {
            throw new FoodoramaException(ui.getDishNotExistMsg(parameters.get(0)));
        } else {
            try {
                Ingredient currentIngr = IngredientList.ingredientList.get(ingredientIndex);
                currentIngr.setLimitValue();
            } catch (FoodoramaException e) {
                throw new FoodoramaException(e.getMessage());
            }
        }
    }
}
