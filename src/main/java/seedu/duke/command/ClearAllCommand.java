package seedu.duke.command;

import seedu.duke.DishList;
import seedu.duke.IngredientList;
import seedu.duke.Ui;

import java.util.ArrayList;

public class ClearAllCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        Ui ui = new Ui();
        DishList.clearList();
        IngredientList.clearList();
        System.out.println(ui.getAllCleared());
    }
}
