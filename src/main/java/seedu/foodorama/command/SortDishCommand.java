package seedu.foodorama.command;

import seedu.foodorama.Dish;
import seedu.foodorama.DishList;
import seedu.foodorama.Ui;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Allows the user to Sort the dishList from
 * most wasted Dish in terms of weight to the
 * least wasted Dish.
 * Format: sort dish
 *
 * @author jhsee5
 */
public class SortDishCommand extends Command {
    private static final Ui UI = new Ui();
    Comparator<Dish> dishComparator;

    /**
     * User command to Sort the dishList from
     * most wasted Dish in terms of weight to the
     * least wasted Dish.
     *
     * @param parameters Contains dishComparator used to sort Dishes
     *                   form the most to the least wasted by weight.
     * @author jhsee5
     */
    @Override
    public void execute(ArrayList<String> parameters) {
        DishList.dishList.sort(dishComparator);
        UI.printSortDishMsg();
        DishList.list();
    }
}
