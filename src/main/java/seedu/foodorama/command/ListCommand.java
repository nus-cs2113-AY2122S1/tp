package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;

public class ListCommand extends Command {
    private static final String DISH = "dish";
    private static final String INGR = "ingr";
    private static final Ui UI = new Ui();

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        switch (parameters.get(0)) {
        case DISH:
            DishList.list();
            break;

        case INGR:
            IngredientList.list();
            break;

        default:
            throw new FoodoramaException(UI.getListMissingParamMsg());
        }
    }
}
