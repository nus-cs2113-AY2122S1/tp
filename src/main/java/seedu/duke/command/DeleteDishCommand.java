package seedu.duke.command;

import seedu.duke.DishList;

import java.util.ArrayList;

public class DeleteDishCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        Ui ui = new Ui();
        System.out.println(ui.getLineDivider());
        DishList.delete(parameters.get(0));
        System.out.println(ui.getLineDivider());
    }
}
