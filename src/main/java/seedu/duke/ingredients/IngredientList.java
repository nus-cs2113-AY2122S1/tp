package seedu.duke.ingredients;

import seedu.duke.exceptions.DukeException;

import java.util.ArrayList;

/**
 * Represents the list of ingredients.
 */
public class IngredientList {

    private static final String INVALID_NUMBER = "Ingredient number does not exist!";

    protected ArrayList<Ingredient> ingredientList;
    private static IngredientList instance = null;

    public IngredientList() {
        ingredientList = new ArrayList<Ingredient>(); //This is for v1.0
    }

    public static IngredientList getInstance() {
        if (instance == null) {
            instance = new IngredientList();
        }
        return instance;
    }

    /**
     * Gets the size of the current inventory, not including the amount of individual ingredient.
     * @return the size of current inventory
     */
    public int getInventoryStock() {
        return ingredientList.size();
    }

    /**
     * Gets the string representation of an ingredient in the list.
     * @param ingredientNumber ingredient number to get information
     * @return String representation of the ingredient
     * @throws DukeException if the ingredient number have not existed
     */
    public String getIngredientInfo(int ingredientNumber) throws DukeException {
        try {
            return ingredientList.get(ingredientNumber - 1).toString();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INVALID_NUMBER);
        }
    }

    /**
     * Removes an ingredient from the list.
     * @param ingredientNumber ingredient number to remove
     * @return The removed ingredient
     * @throws DukeException if the ingredient number has not existed
     */
    public Ingredient remove(int ingredientNumber) throws DukeException {
        try {
            Ingredient removedIngredient = ingredientList.remove(ingredientNumber - 1);
            return removedIngredient;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INVALID_NUMBER);
        }
    }

    /**
     * Adds the new ingredient to the ingredient list.
     * @param ingredient The ingredient object to be added
     */
    public void add(Ingredient ingredient) {
        ingredientList.add(ingredient);
    }

    /**
     * Sets the update ingredient to an indexed ingredient in the ingredient list.
     * @param ingredientNumber The index of the ingredient to be updated
     * @param ingredient The update ingredient object
     */
    public void set(int ingredientNumber, Ingredient ingredient) {
        ingredientList.set(ingredientNumber, ingredient);
    }

    /**
     * Gets the ingredient from a specific index in the ingredient list.
     * @param ingredientNumber The index of the ingredient to be located
     * @returns The indexed ingredient object
     * @throws DukeException The ingredient is out of bounds
     */
    public Ingredient get(int ingredientNumber) throws DukeException {
        try {
            return ingredientList.get(ingredientNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INVALID_NUMBER);
        }
    }

}
