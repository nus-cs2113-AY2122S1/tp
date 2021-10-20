package seedu.duke.command;

import seedu.duke.DishList;
import seedu.duke.IngredientList;
import seedu.duke.Ui;
import seedu.duke.exceptions.FoodoramaException;

import java.util.ArrayList;

public class ListCommand extends Command {
    private static final Ui ui = new Ui();

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        switch (parameters.get(0)) {
        case "dish":
            DishList.list();
            break;

        case "ingr":
            IngredientList.list();
            break;

        default:
            throw new FoodoramaException(ui.getListMissingParamMsg());
        }
    }
}
