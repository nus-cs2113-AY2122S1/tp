package seedu.duke.command;

import seedu.duke.Dish;
import seedu.duke.DishList;
import seedu.duke.Ingredient;
import seedu.duke.IngredientList;
import seedu.duke.Ui;


import java.util.ArrayList;

public class FindCommand extends Command  {
    @Override
    public void execute(ArrayList<String> parameters) {
        Ui ui = new Ui();
        String commandToExecute = parameters.get(0);
        parameters.remove(0);
        String itemToFind = String.join(" ", parameters);
        // TODO exception here
        if (itemToFind.isBlank()) {
            System.out.println(ui.FIND_MISSING_PARAM);
        } else {
            switch (commandToExecute) {
                case "dish":
                    ArrayList<Dish> matchedDishList = new ArrayList<>();
                    for (Dish matchingDishes : DishList.dishList) {
                        if (matchingDishes.getDishName().contains(itemToFind)) {
                            matchedDishList.add(matchingDishes);
                        }
                    }
                    ui.printMatchedDishes(matchedDishList);
                    break;

                case "ingr":
                    ArrayList<Ingredient> matchedIngrList = new ArrayList<>();
                    for (Ingredient matchingIngr : IngredientList.ingredientList) {
                        if (matchingIngr.getIngredientName().contains(itemToFind)) {
                            matchedIngrList.add(matchingIngr);
                        }
                    }
                    ui.printMatchedIngredients(matchedIngrList);
                    break;

                default:
                    System.out.println("THATS THE WRONG NUMBER");
                    break;
            }
        }
    }
}
