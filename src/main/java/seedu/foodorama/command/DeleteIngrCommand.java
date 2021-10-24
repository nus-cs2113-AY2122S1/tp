package seedu.foodorama.command;

import seedu.foodorama.IngredientList;
import java.util.ArrayList;

public class DeleteIngrCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        IngredientList.delete(parameters.get(0));
    }
}