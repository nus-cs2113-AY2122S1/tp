package seedu.duke.command;

import seedu.duke.DishList;

import java.util.ArrayList;

public class AddDishWasteCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        Ui ui = new Ui();
        //Num followed by name
        int dishIndex = DishList.find(parameters.get(1));
        if (dishIndex == -1) {
            System.out.println(ui.getDishNotExistMsg());
        } else {
            try {
                DishList.dishList.get(dishIndex).addWaste(Double.parseDouble(parameters.get(0)));
            } catch (NumberFormatException e) {
                System.out.println("____________________________________________");
                System.out.println("Incorrect parameters - Invalid number entered");
                System.out.println("____________________________________________");
            }
        }
    }

}
