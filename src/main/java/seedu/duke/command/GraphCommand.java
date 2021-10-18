package seedu.duke.command;

import seedu.duke.DishList;
import seedu.duke.IngredientList;
import seedu.duke.Ui;

import java.util.ArrayList;

public class GraphCommand extends Command {
    Ui ui = new Ui();

    @Override
    public void execute(ArrayList<String> parameters) {
        switch (parameters.get(0)) {
        case "dish":
            DishList.graph();
            break;

        case "ingr":
            IngredientList.graph();
            break;

        default:
            ui.printListMissingParamMsg();
            break;
        }
    }
}