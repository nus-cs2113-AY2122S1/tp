package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;

public class EditDishCommand extends Command {
    private static final Ui ui = new Ui();

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        int dishIndex;
        if (isNumber(parameters.get(0))) {
            dishIndex = Integer.parseInt(parameters.get(0)) - 1;
        } else {
            String dishName = String.join(" ", parameters);
            if (dishName.isBlank()) {
                throw new FoodoramaException(ui.getDishIndexMissingMsg());
            } else {
                dishIndex = DishList.find(dishName);
            }
        }
        DishList.editName(dishIndex);
    }


    public boolean isNumber(String number) {
        try {
            int dishIndex = Integer.parseInt(number) - 1;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

