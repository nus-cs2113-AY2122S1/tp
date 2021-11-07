package seedu.foodorama.command;

import seedu.foodorama.Ingredient;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Allows the user to Sort the ingredientList from
 * most wasted Ingredient in terms of weight to the
 * least wasted Ingredient.
 * Format: sort ingr
 *
 * @author jhsee5
 */
public class SortIngrCommand extends Command {
    private static final Ui UI = new Ui();
    Comparator<Ingredient> ingrComparator;

    /**
     * User command to Sort the ingredeintList from
     * most wasted Ingredient in terms of weight to the
     * least wasted Ingredient.
     *
     * @param parameters Contains ingrComparator used to sort Ingredients
     *                   from most to least wasted weight.
     * @author jhsee5
     */
    @Override
    public void execute(ArrayList<String> parameters) {
        IngredientList.ingredientList.sort(ingrComparator);
        UI.printSortIngrMsg();
        IngredientList.list();
    }
}
