package seedu.foodorama.command;

import seedu.foodorama.Dish;
import seedu.foodorama.DishList;
import seedu.foodorama.Ui;

import java.util.ArrayList;
import java.util.Comparator;

public class SortDishCommand extends Command {
    private static final Ui ui = new Ui();
    Comparator<Dish> dishComparator;

    @Override
    public void execute(ArrayList<String> parameters) {
        DishList.dishList.sort(dishComparator);
        ui.printSortDishMsg();
        DishList.list();
    }
}
