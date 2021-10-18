package seedu.duke.command;

import seedu.duke.IngredientList;
import seedu.duke.Ui;

import java.util.ArrayList;

public class DeleteIngrCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        Ui ui = new Ui();

        System.out.println(ui.getLineDivider());
        IngredientList.delete(parameters.get(0));
        System.out.println(ui.getLineDivider());
    }
}