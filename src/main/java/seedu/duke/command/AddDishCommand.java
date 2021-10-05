package seedu.duke.command;

import seedu.duke.DishList;

import java.util.ArrayList;

public class AddDishCommand extends Command{
    @Override
    public void execute(ArrayList<String> parameters) {
        System.out.println("____________________________________________");
        DishList.add(parameters.get(0));
        System.out.println("____________________________________________");
    }

    @Override
    public void executeList(ArrayList<String> parameters) {
        // blank as AddDishCommand will not use executeList
    }
}
