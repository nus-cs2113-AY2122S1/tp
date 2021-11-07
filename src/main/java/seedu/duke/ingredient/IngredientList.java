//@@author leecheokfeng

package seedu.duke.ingredient;

import java.util.ArrayList;

/**
 * Contains the list of ingredients within the restaurant's inventory.
 * Keeps track of the total number of ingredients within the inventory.
 */
public class IngredientList {
    public ArrayList<Ingredient> ingredientList = new ArrayList<>();
    public int totalIngredients = 0;
}
