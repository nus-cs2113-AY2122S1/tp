package seedu.duke.command;

import seedu.duke.DishList;

import java.util.ArrayList;

public class AddDishWasteCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        //Num followed by name
        int dishIndex = DishList.find(parameters.get(1));
        if (dishIndex == -1) {
            System.out.println("Dish does not exist");
        } else {
            DishList.dishList.get(dishIndex).addWaste(Double.parseDouble(parameters.get(0)));
        }
    }

}
