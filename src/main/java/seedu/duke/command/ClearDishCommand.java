package seedu.duke.command;

import seedu.duke.DishList;
import seedu.duke.Ui;

import java.util.ArrayList;

public class ClearDishCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        Ui ui = new Ui();
        DishList.clearList();
        System.out.println(ui.getDishListCleared());
    }
}
