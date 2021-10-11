package seedu.duke.command;

import seedu.duke.Ingredient;
import seedu.duke.IngredientList;
import seedu.duke.Ui;

import java.util.ArrayList;
import java.util.Scanner;

public class AddIngrWasteCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        Ui ui = new Ui();
        String ingredient = String.join(" ", parameters);
        int ingredientIndex = IngredientList.find(ingredient);
        System.out.println(ui.getLineDivider());
        if (ingredientIndex == -1) {
            System.out.println(ui.getIngrNotExistMsg());
        } else {
            try {
                System.out.println("Enter the wastage of " + ingredient + " in KG:");
                Scanner in = new Scanner(System.in);
                String ingredientWeight = in.nextLine();
                double ingredientWeightValue = Double.parseDouble(ingredientWeight);
                Ingredient currentIngredient = IngredientList.ingredientList.get(ingredientIndex);
                currentIngredient.addWaste(ingredientWeightValue);
            } catch (NumberFormatException e) {
                System.out.println(ui.getInvalidParamMsg());
                System.out.println(ui.getLineDivider());
            }
        }
        System.out.println(ui.getLineDivider());
    }

}
