package seedu.duke.command;

import seedu.duke.DishList;
import seedu.duke.IngredientList;
import seedu.duke.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {
    Ui ui = new Ui();

    // For JUnit Testing
    public boolean isDish = false;
    public boolean isIngr = false;
    public boolean isOther = false;

    @Override
    public void execute(ArrayList<String> parameters) {
        switch (parameters.get(0)) {
        case "dish":
            System.out.println(ui.getLineDivider());
            DishList.list();
            System.out.println(ui.getLineDivider());

            isDish = true;
            break;

        case "ingr":
            System.out.println(ui.getLineDivider());
            IngredientList.list();
            System.out.println(ui.getLineDivider());
            
            isIngr = true;
            break;

        default:
            System.out.println(ui.getListMissingParamMsg());
            isOther = true;
            break;
        }
    }
}
