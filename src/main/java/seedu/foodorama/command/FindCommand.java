package seedu.foodorama.command;

import seedu.foodorama.Dish;
import seedu.foodorama.DishList;
import seedu.foodorama.Ingredient;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;


import java.util.ArrayList;

public class FindCommand extends Command {
    private static final String DISH = "dish";
    private static final String INGR = "ingr";
    private static final Ui UI = new Ui();

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        String commandToExecute = parameters.get(0);
        parameters.remove(0);
        String itemToFind = String.join(" ", parameters);

        switch (commandToExecute) {
        case DISH:
            if (itemToFind.isBlank()) {
                throw new FoodoramaException(UI.getFindMissingParamMsg());
            }
            ArrayList<Dish> matchedDishList = new ArrayList<>();
            for (Dish matchingDishes : DishList.dishList) {
                if (matchingDishes.getDishName().contains(itemToFind)) {
                    matchedDishList.add(matchingDishes);
                }
            }
            UI.printMatchedDishes(matchedDishList);
            break;

        case INGR:
            if (itemToFind.isBlank()) {
                throw new FoodoramaException(UI.getFindMissingParamMsg());
            }
            ArrayList<Ingredient> matchedIngrList = new ArrayList<>();
            for (Ingredient matchingIngr : IngredientList.ingredientList) {
                if (matchingIngr.getIngredientName().contains(itemToFind)) {
                    matchedIngrList.add(matchingIngr);
                }
            }
            UI.printMatchedIngredients(matchedIngrList);
            break;

        default:
            throw new FoodoramaException(UI.getFindInvalidParamMsg());
        }
    }
}

