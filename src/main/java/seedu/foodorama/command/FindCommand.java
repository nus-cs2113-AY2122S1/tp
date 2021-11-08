package seedu.foodorama.command;

import seedu.foodorama.Dish;
import seedu.foodorama.DishList;
import seedu.foodorama.Ingredient;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;


import java.util.ArrayList;

/**
 * Allows the user to find a dish or an ingredient with a keyword.
 * Format: find dish [KEYWORD] | find ingr [KEYWORD]
 *
 * @author renzocanare
 */
public class FindCommand extends Command {
    private static final String DISH = "dish";
    private static final String INGR = "ingr";
    private static final Ui UI = new Ui();
    private static final int INDEX_ZERO = 0;


    /**
     * User command to find ingredients or dishes from their respective lists using a keyword.
     * Checks if the input parameters of the find command are valid before calling the appropriate function
     *
     * <p>If no exceptions are thrown, the user is shown a list of objects that match the keyword.</p>
     * @param parameters parameters for the find command
     * @throws FoodoramaException if no keyword to search for or find type isn't dish or ingredient
     *
     * @author renzocanare
     */
    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        String commandToExecute = parameters.get(INDEX_ZERO);
        parameters.remove(INDEX_ZERO);
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

