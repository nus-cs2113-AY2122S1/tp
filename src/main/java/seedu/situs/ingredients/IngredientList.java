package seedu.situs.ingredients;

import seedu.situs.exceptions.DukeException;
import seedu.situs.storage.Storage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents the list of ingredients.
 */
public class IngredientList {

    private static final String INVALID_NUMBER = "Ingredient number does not exist!";

    protected ArrayList<Ingredient> ingredientList;
    private Storage storage;
    private static IngredientList instance = null;

    public IngredientList() throws DukeException {
        try {
            storage = new Storage();
            ingredientList = storage.loadIngredientsFromMemory();
        } catch (IOException e) {
            //failsafe
            ingredientList = new ArrayList<>();
            throw new DukeException("Cannot read ingredients from memory!");
        }
    }

    public static IngredientList getInstance() throws DukeException {
        if (instance == null) {
            instance = new IngredientList();
        }
        return instance;
    }

    /**
     * Gets the current list of ingredients.
     * @return the list of ingredients.
     */
    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
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

    public LocalDate getIngredientExpiry(int ingredientNumber) {
        return ingredientList.get(ingredientNumber - 1).getExpiry();
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
    public void add(Ingredient ingredient) throws IOException {
        ingredientList.add(ingredient);
        storage.writeIngredientsToMemory(ingredientList);
    }

    /**
     * Sets the update ingredient to an indexed ingredient in the ingredient list.
     * @param ingredientNumber The index of the ingredient to be updated
     * @param ingredient The update ingredient object
     */
    public void set(int ingredientNumber, Ingredient ingredient) throws IOException {
        ingredientList.set(ingredientNumber, ingredient);
        storage.writeIngredientsToMemory(ingredientList);
    }

    /**
     * Gets the ingredient from a specific index in the ingredient list.
     * @param ingredientNumber The index of the ingredient to be located
     * @return The indexed ingredient object
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
