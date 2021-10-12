package seedu.duke.command;

import seedu.duke.IngredientList;
import seedu.duke.Ui;

import java.util.ArrayList;
import java.util.Scanner;

public class AddIngrCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        Ui ui = new Ui();
        String ingredient = String.join(" ", parameters);
        if (IngredientList.find(ingredient) >= 0) {
            System.out.println(ui.getIngrExistsMsg());
        } else {
            System.out.println(ui.getLineDivider());
            //String ingredientWeight = parameters.get(0);
            System.out.println("Enter the weight of " + ingredient + " in KG:");
            Scanner in = new Scanner(System.in);
            String ingredientWeight = in.nextLine();

            double ingredientWeightValue = Double.parseDouble(ingredientWeight);
            IngredientList.add(ingredient, ingredientWeightValue);
        }
        System.out.println(ui.getLineDivider());
    }

}
