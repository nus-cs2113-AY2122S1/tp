package seedu.foodorama.command;

import seedu.foodorama.Dish;
import seedu.foodorama.DishList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;

public class SetDishLimitCommand extends Command {
    private static final Ui ui = new Ui();

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        String dish = String.join(" ", parameters);
        int dishIndex = DishList.find(dish);

        if (dishIndex == -1) {
            throw new FoodoramaException(ui.getDishNotExistMsg(parameters.get(0)));
        } else {
            try {
                Dish currentDish = DishList.dishList.get(dishIndex);
                currentDish.setLimitValue();
            } catch (FoodoramaException e) {
                throw new FoodoramaException(e.getMessage());
            }
        }
    }
}
