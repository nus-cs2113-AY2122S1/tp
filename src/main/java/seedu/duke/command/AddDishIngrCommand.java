package seedu.duke.command;

import seedu.duke.DishList;

import java.util.ArrayList;

public class AddDishIngrCommand extends Command {

    @Override
    public void execute(ArrayList<String> parameters) {
        int dishIndex = DishList.find(parameters.get(0));
        if (dishIndex == -1) {
            System.out.println("Dish does not exist");
        } else {
            System.out.println("____________________________________________");
            DishList.dishList.get(dishIndex).addConstituent(parameters.get(1));
            System.out.println("____________________________________________");
        }
    }

    @Override
    public void executeList(ArrayList<String> parameters) {
        // blank as AddDishIngrCommand will not use executeList
    }
}
