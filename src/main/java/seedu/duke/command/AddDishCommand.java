package seedu.duke.command;

import seedu.duke.DishList;
import seedu.duke.Ui;

import java.util.ArrayList;

public class AddDishCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        Ui ui = new Ui();
        //System.out.println(ui.getLineDivider());
        DishList.add(parameters.get(0));
        //System.out.println(ui.getLineDivider());
    }

}
