package seedu.duke.command;

import seedu.duke.DishList;
import seedu.duke.IngredientList;
import seedu.duke.Ui;
import seedu.duke.exceptions.FoodoramaException;

import java.util.ArrayList;

public class GraphCommand extends Command {
    Ui ui = new Ui();

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        switch (parameters.get(0)) {
        case "dish":
            System.out.println(ui.getLineDivider());
            DishList.graph();
            System.out.println(ui.getLineDivider());
            break;

        case "ingr":
            System.out.println(ui.getLineDivider());
            IngredientList.graph();
            System.out.println(ui.getLineDivider());
            break;

        default:
            throw new FoodoramaException("Sorry, please input: graph [TYPE]." + System.lineSeparator()
                    + "[TYPE]: dish to list dishes, ingr to list ingredients.");

        }
    }
}