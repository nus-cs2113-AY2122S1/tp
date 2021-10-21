package seedu.duke.ingredients;

import seedu.duke.exceptions.DukeException;

import java.util.ArrayList;

/*
 * Represents a list of multiple ingredients (multiple ingredient groups).
 *
 * E.g. (2 ingredient groups)
 * 1. Carrot | Total Amount: 3.5 kg
 *      Amount Left: 1.0 kg | Expiry Date: 04/11/2021
 *      Amount Left: 2.5 kg | Expiry Date: 10/12/2021
 *
 * 2. Radish | Total Amount: 1.0 kg
 *      Amount Left: 1.0 kg | Expiry Date: 18/02/2022
 */

public class IngredientList {

    private static final String INVALID_NUMBER = "Ingredient number does not exist!";
    protected ArrayList<IngredientGroup> ingredientList;
    private static IngredientList instance = null;

    public IngredientList() {
        ingredientList = new ArrayList<>();
    }

    /**
     * Gets instance of ingredient list.
     *
     * @return ingredient list
     */
    public static IngredientList getInstance() {
        if (instance == null) {
            instance = new IngredientList();
        }
        return instance;
    }

    /**
     * Gets size of ingredient list.
     *
     * @return size
     */
    public int getSize() {
        return ingredientList.size();
    }

    /**
     * Uses ingredient name as key to search if ingredient currently exists in ingredient list.
     *
     * @param ingredientName ingredient to be searched
     * @return true if ingredient already exists, false if otherwise
     */
    public boolean searchIngredientInList(String ingredientName) {
        for (int i = 0; i < getSize(); i++) {
            if (ingredientList.get(i).getIngredientGroupName().equals(ingredientName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Uses ingredient name as key to find the index of the corresponding ingredient.
     * in the ingredient list.
     *
     * @param ingredientName name of ingredient
     * @return index of ingredient, -1 if not found
     */
    public int findIngredientIndexInList(String ingredientName) {
        for (int i = 0; i < getSize(); i++) {
            IngredientGroup currentIngredient = ingredientList.get(i);
            if (ingredientName.equals(currentIngredient.getIngredientGroupName())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds ingredient to ingredient list.
     *
     * @param ingredient ingredient to be added
     */
    public void add(Ingredient ingredient) {
        String ingredientName = ingredient.getName();
        boolean repeatedName = searchIngredientInList(ingredientName);

        if (repeatedName) { //ingredient already exists, add to current ingredient group
            int ingredientIndex = findIngredientIndexInList(ingredientName);
            ingredientList.get(ingredientIndex).add(ingredient);

        } else { //create new ingredient group
            IngredientGroup newGroup = new IngredientGroup();
            ingredientList.add(newGroup);
            newGroup.setIngredientGroupName(ingredientName);
            newGroup.add(ingredient);
        }
    }

    /**
     * Get ingredient group based on ingredient number (i.e. all duplicates of the same ingredient).
     *
     * @param ingredientNumber ingredient number
     * @return ingredient group
     * @throws DukeException index out of bounds, cannot access
     */
    public IngredientGroup getIngredientGroup(int ingredientNumber) throws DukeException {
        try {
            return ingredientList.get(ingredientNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INVALID_NUMBER);
        }
    }
}
