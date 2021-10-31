package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;

public class GraphCommand extends Command {
    private static final String DISH = "dish";
    private static final String INGR = "ingr";
    private static final Ui UI = new Ui();

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        switch (parameters.get(0)) {
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