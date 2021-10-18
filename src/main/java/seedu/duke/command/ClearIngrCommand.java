package seedu.duke.command;

import seedu.duke.IngredientList;
import seedu.duke.Ui;

import java.util.ArrayList;

public class ClearIngrCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        Ui ui = new Ui();
        IngredientList.clearList();
        System.out.println(ui.getIngrListCleared());
    }
}
