package seedu.duke.command;


import seedu.duke.Dish;
import seedu.duke.DishList;
import seedu.duke.Ui;

import java.util.ArrayList;
import java.util.Scanner;

public class AddDishWasteCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        Ui ui = new Ui();
        String dish = String.join(" ", parameters);
        int dishIndex = DishList.find(dish);
        System.out.println(ui.getLineDivider());
        if (dishIndex == -1) {
            System.out.println(ui.getDishNotExistMsg());
        } else {
            try {
                System.out.println("Enter the wastage of " + dish + " in KG:");
                Scanner in = new Scanner(System.in);
                String dishWeight = in.nextLine();
                double ingredientWeightValue = Double.parseDouble(dishWeight);
                Dish currentDish = DishList.dishList.get(dishIndex);
                currentDish.addWaste(ingredientWeightValue);
            } catch (NumberFormatException e) {
                System.out.println(ui.getInvalidParamMsg());
                System.out.println(ui.getLineDivider());
            }
        }
        System.out.println(ui.getLineDivider());
    }

}
