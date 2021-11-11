package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;

/**
 * Allows the user to view a list of existing Ingredients being
 * tracked in Food-O-Rama.
 * Format: list ingr
 *
 * <p> Allows the user to view a list of existing Dishes being
 * tracked in Food-O-Rama.
 * Format: list dish </p>
 *
 * @author renzocanare
 */
public class ListCommand extends Command {
    private static final String DISH = "dish";
    private static final String INGR = "ingr";
    private static final Ui UI = new Ui();
    private static final int INDEX_ZERO = 0;

    /**
     * User command to view the list of existing ingredients or dishes
     * based on the input that comes after 'list'.
     * Input after 'list' is either 'ingr' or 'dish'
     *
     * @param parameters contains the user input for LIST
     * @throws FoodoramaException when the parameter is missing/blank
     * @author renzocanare
     */
    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        switch (parameters.get(INDEX_ZERO)) {
        case DISH:
            DishList.list();
            break;

        case INGR:
            IngredientList.list();
            break;

        default:
            throw new FoodoramaException(UI.getListMissingParamMsg());
        }
    }
}
