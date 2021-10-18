package seedu.duke.command;

import seedu.duke.DishList;
import seedu.duke.Ui;
import seedu.duke.exceptions.FoodoramaException;

import java.util.ArrayList;

public class AddDishCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        Ui ui = new Ui();
        //System.out.println(ui.getLineDivider());
        if(parameters.get(0).isBlank()) {
            throw new FoodoramaException("Dish name cannot be blank");
        }
        DishList.add(parameters.get(0));
        //System.out.println(ui.getLineDivider());
    }

}
