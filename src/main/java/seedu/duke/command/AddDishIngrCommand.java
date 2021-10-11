package seedu.duke.command;

import seedu.duke.DishList;

import java.util.ArrayList;

public class AddDishIngrCommand extends Command {

    @Override
    public void execute(ArrayList<String> parameters) {
        Ui ui = new Ui();
        int dishIndex = DishList.find(parameters.get(0));
        if (dishIndex == -1) {
            System.out.println(ui.getDishNotExistMsg());
        } else {
            System.out.println(ui.getLineDivider());
            DishList.dishList.get(dishIndex).addConstituent(parameters.get(1));
            System.out.println(ui.getLineDivider());
        }
    }
}
