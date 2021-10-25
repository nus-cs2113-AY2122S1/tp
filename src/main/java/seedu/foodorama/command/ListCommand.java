package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;

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
