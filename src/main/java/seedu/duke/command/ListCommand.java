package seedu.duke.command;

import seedu.duke.DishList;
import seedu.duke.IngredientList;
import seedu.duke.Ui;
import seedu.duke.exceptions.FoodoramaException;

import java.util.ArrayList;

public class ListCommand extends Command {
    private static final Ui ui = new Ui();

    // For JUnit Testing
    public boolean isDish = false;
    public boolean isIngr = false;
    public boolean isOther = false;

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        switch (parameters.get(0)) {
        case "dish":
            DishList.list();
            isDish = true;
            break;

        case "ingr":
            IngredientList.list();
            isIngr = true;
            break;

        default:
            isOther = true;
            throw new FoodoramaException(ui.getListMissingParamMsg());
        }
    }
}
