package seedu.duke.command;

import seedu.duke.DishList;
import seedu.duke.Ingredient;
import seedu.duke.IngredientList;

import java.util.ArrayList;

public class ListCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        // blank as ListCommand will not use execute
    }

    @Override
    public void executeList(ArrayList<String> parameters) {
        switch (parameters.get(0)) {
            case "dish":
                DishList.list();
                break;

            case "ingr":
                IngredientList.list();

            default:
                System.out.println("____________________________________________");
                System.out.println("Invalid input");
                System.out.println("____________________________________________");
                break;
        }
    }
}
