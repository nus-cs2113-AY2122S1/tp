package seedu.situs.ingredients;

import java.util.ArrayList;

/**
 * Represents the list of ingredients.
 */
public class IngredientList {

    protected ArrayList<Ingredient> ingredientList;

    public IngredientList() {
        ingredientList = new ArrayList<>(); //This is for v1.0
    }

    /**
     * Adds the new ingredient to the ingredient list.
     * @param ingredient The ingredient object to be added
     * @return Successful Addition Message
     */
    public String addNewIngredient(Ingredient ingredient) {
        // TODO: Append new ingredient to the list
        // We allow duplicate names for v1.0
        return ingredient.toString();
    }

    /**
     * Lists all the ingredients stored in the ingredients list.
     * @return the list of ingredients
     */
    public String listAllIngredients() {
        //TODO: Add code to print all ingredients. Return no ingredients if list is empty,
        // else, return a string consisting of all the ingredients

        // check if list empty
        String listOfIngredients = "";
        // loop through list
        return listOfIngredients;
    }

    /**
     * Deletes an ingredient at the specified index in the list.
     * @param index The index of the ingredient to be deleted
     * @return The details of the deleted ingredient
     */
    public String deleteIngredient(int index) {
        //TODO: add code to delete the ingredient at a particular index, rmb to catch invalid index in main

        String removedIngredient = "placeholder";
        return removedIngredient.toString();
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


}
