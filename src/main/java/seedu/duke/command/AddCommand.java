package seedu.duke.command;

import seedu.duke.DishList;

import java.util.ArrayList;

public class AddCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        switch (parameters.get(0)) {
        case "dish":
            DishList.add(parameters.get(1));
        }
    }
}
