package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;

/**
 * Allows the user to see a graphical representation of the dishes or ingredients present.
 * Format: graph dish | graph ingr
 *
 * @author Dniv-ra
 */
public class GraphCommand extends Command {
    private static final String DISH = "dish";
    private static final String INGR = "ingr";
    private static final Ui UI = new Ui();
    private static final int INDEX_ZERO = 0;

    /**
     * User command to display a graph of ingredients or dishes from their respective lists.
     * Checks if the input parameters of the graph command are valid before calling the appropriate function
     *
     * <p>If no exceptions are thrown, the user is shown a graph of either dishes or ingredients based on the type.</p>
     * @param parameters parameters for the graph command
     * @throws FoodoramaException if graph type isn't dish or ingredient
     *
     * @author Dniv-ra
     */
    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        switch (parameters.get(INDEX_ZERO)) {
        case DISH:
            if (DishList.dishList.isEmpty()) {
                throw new FoodoramaException(UI.getLineDivider() + System.lineSeparator()
                        + "List is empty, nothing to show" + System.lineSeparator()
                        + UI.getLineDivider());
            }
            DishList.graph();
            break;

        case INGR:
            if (IngredientList.ingredientList.isEmpty()) {
                throw new FoodoramaException(UI.getLineDivider() + System.lineSeparator()
                        + "List is empty, nothing to show" + System.lineSeparator()
                        + UI.getLineDivider());
            }
            IngredientList.graph();
            break;

        default:
            throw new FoodoramaException(UI.getGraphInvalidParamMsg());

        }
    }
}