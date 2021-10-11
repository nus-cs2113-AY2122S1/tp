package seedu.duke.ingredients;

import seedu.duke.exceptions.DukeException;

import java.util.ArrayList;

/**
 * Represents the list of ingredients.
 */
public class IngredientList {

    protected ArrayList<Ingredient> ingredientList;
    private static IngredientList instance = null;

    private static final String ADDED_MESSAGE = "That task has been added:\n" + "\t";
    private static final String LIST_EMPTY_MESSAGE = "No ingredients currently in the list.";
    private static final String LIST_MESSAGE = "Here is the list of the ingredients currently in inventory:\n";
    private static final String DELETE_MESSAGE = "Got it. This ingredient has been removed:\n" + "\t";

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
     * Adds the new ingredient to the ingredient list.
     * @param ingredient The ingredient object to be added
     * @return Successful Addition Message
     */
    public String addNewIngredient(Ingredient ingredient) {
        // TODO: Append new ingredient to the list
        // We allow duplicate names for v1.0
        ingredientList.add(ingredient);
        return ADDED_MESSAGE + ingredient.toString();
    }

    /**
     * Lists all the ingredients stored in the ingredients list.
     * @return the list of ingredients
     */
    public String listAllIngredients() {
        String resultMsg;
        int i;

        if (ingredientList.size() == 0) {
            resultMsg = LIST_EMPTY_MESSAGE;
            return resultMsg;
        }

        resultMsg = LIST_MESSAGE;

        for (i = 0; i < ingredientList.size() - 1; i++) {
            resultMsg += (i + 1) + "."
                    + ingredientList.get(i).toString() + '\n' + '\t';
        }
        resultMsg = resultMsg + ingredientList.size() + "." + ingredientList.get(i).toString();

        return resultMsg;
    }

    /**
     * Deletes an ingredient at the specified index in the list.
     * @param ingredientNumber The index of the ingredient to be deleted
     * @return The details of the deleted ingredient
     */
    public String deleteIngredient(int ingredientNumber) {
        String resultMsg;
        Ingredient removedIngredient = ingredientList.remove(ingredientNumber - 1);

        resultMsg = DELETE_MESSAGE + removedIngredient.toString();
        return resultMsg;
    }

    /**
     * Updates the ingredient at the specified index in the list by the amount specified.
     * @param index the index of the ingredient to be updated
     * @param amount the amount to add to the ingredient
     * @return The details of the updated ingredient
     */
    public String updateIngredient(int index, double amount) {
        //TODO: add code to update the ingredient at the particular index by the amount specified

        return ingredientList.get(index - 1).toString();
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
            throw new DukeException("Ingredient number does not exist!");
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
            throw new DukeException("Ingredient number does not exist!");
        }
    }


}
