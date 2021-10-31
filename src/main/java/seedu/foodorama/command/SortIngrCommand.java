package seedu.foodorama.command;

import seedu.foodorama.Ingredient;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;

import java.util.ArrayList;
import java.util.Comparator;

public class SortIngrCommand extends Command {
    private static final Ui UI = new Ui();
    Comparator<Ingredient> ingrComparator;

    @Override
    public void execute(ArrayList<String> parameters) {
        IngredientList.ingredientList.sort(ingrComparator);
        UI.printSortIngrMsg();
        IngredientList.list();
    }
}
