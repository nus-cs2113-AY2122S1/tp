package seedu.foodorama.command;

import seedu.foodorama.Dish;
import seedu.foodorama.DishList;
import seedu.foodorama.Ui;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Represents the Command to Sort the ingredientList from
 * most wasted Ingredient in terms of weight to the
 * least wasted Ingredient
 */

public class SortDishCommand extends Command {
    private static final Ui UI = new Ui();
    Comparator<Dish> dishComparator;


    /**
     * @param parameters dish comparator used to sort Dishes
     *                   by largest to smallest weight
     */
    @Override
    public void execute(ArrayList<String> parameters) {
        DishList.dishList.sort(dishComparator);
        UI.printSortDishMsg();
        DishList.list();
    }
}
