package seedu.duke.command;

import seedu.duke.IngredientList;

import java.util.ArrayList;

public class DeleteIngrCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        System.out.println("____________________________________________");
        IngredientList.delete(parameters.get(0));
        System.out.println("____________________________________________");
    }
}