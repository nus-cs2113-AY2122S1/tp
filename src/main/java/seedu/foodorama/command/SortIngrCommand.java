package seedu.foodorama.command;

import seedu.foodorama.Ingredient;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Represents the Command to Sort the ingredientList from
 * most wasted Ingredient in terms of weight to the
 * least wasted Ingredient
 */
public class SortIngrCommand extends Command {
    private static final Ui UI = new Ui();
    Comparator<Ingredient> ingrComparator;


    /**
     * @param parameters ingredient comparator used to sort Ingredients
     *                   by largest to smallest weight
     */
    @Override
    public void execute(ArrayList<String> parameters) {
        IngredientList.ingredientList.sort(ingrComparator);
        UI.printSortIngrMsg();
        IngredientList.list();
    }
}
