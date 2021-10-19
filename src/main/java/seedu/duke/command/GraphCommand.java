package seedu.duke.command;

import seedu.duke.DishList;
import seedu.duke.IngredientList;
import seedu.duke.Ui;
import seedu.duke.exceptions.FoodoramaException;

import java.util.ArrayList;

public class GraphCommand extends Command {
    private static final Ui ui = new Ui();

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        switch (parameters.get(0)) {
        case "dish":
            DishList.graph();
            break;

        case "ingr":
            IngredientList.graph();
            break;

        default:
            throw new FoodoramaException(ui.getGraphInvalidParamMsg());

        }
    }
}