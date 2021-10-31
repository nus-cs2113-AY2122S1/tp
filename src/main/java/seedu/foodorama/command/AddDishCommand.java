package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;

public class AddDishCommand extends Command {
    private static final Ui UI = new Ui();

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        if (parameters.get(0).isBlank()) {
            throw new FoodoramaException(UI.getDishNameMissingMsg());
        }
        DishList.add(parameters.get(0));
    }

}
