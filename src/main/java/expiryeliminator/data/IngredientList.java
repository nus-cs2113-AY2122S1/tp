package expiryeliminator.data;

import java.util.HashMap;

import expiryeliminator.data.exception.DuplicateDataException;

/**
 * Represents the ingredient list and contains methods to add and remove ingredients.
 */
public class IngredientList {
    private final HashMap<String, Ingredient> ingredients;

    /**
     * Constructs IngredientList object with no tasks.
     */
    public IngredientList() {
        ingredients = new HashMap<>();
    }

    /**
     * Adds ingredient to the ingredient list.
     *
     * @param ingredient Ingredient to be added.
     * @throws DuplicateDataException If the ingredient already exists.
     */
    public void add(Ingredient ingredient) throws DuplicateDataException {
        if (contains(ingredient)) {
            throw new DuplicateDataException();
        }
        ingredients.put(ingredient.getName(), ingredient);
    }

    /**
     * Returns {@code true} if the ingredient list contains the specified ingredient.
     *
     * @param ingredient Ingredient whose presence in this list is to be tested.
     * @return {@code true} if the ingredient list contains the specified ingredient.
     */
    public boolean contains(Ingredient ingredient) {
        return ingredients.containsKey(ingredient.getName());
    }

    /**
     * Returns the number of ingredients in the ingredient list.
     *
     * @return The number of ingredients in the ingredient list.
     */
    public int size() {
        return ingredients.size();
    }
}
