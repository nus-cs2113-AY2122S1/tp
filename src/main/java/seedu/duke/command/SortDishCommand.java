package seedu.duke.command;

import seedu.duke.Dish;
import seedu.duke.DishList;
import seedu.duke.Ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortDishCommand extends Command {
    private static final Ui ui =  new Ui();
    Comparator<Dish> dishComparator;

    @Override
    public void execute(ArrayList<String> parameters) {
        DishList.dishList.sort(dishComparator);
        ui.printSortDishMsg();
        DishList.list();
    }
}
