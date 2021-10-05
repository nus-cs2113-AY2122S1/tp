package seedu.duke.command;

import seedu.duke.DishList;
import seedu.duke.IngredientList;

import java.util.ArrayList;

public class ListCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        switch (parameters.get(0)) {
        case "dish":
            DishList.list();
            break;

        case "ingr":
            IngredientList.list();
            break;

        default:
            System.out.println("____________________________________________");
            System.out.println("Invalid input");
            System.out.println("____________________________________________");
            break;
        }
    }

}
