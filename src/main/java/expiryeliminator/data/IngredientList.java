package expiryeliminator.data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;

import expiryeliminator.data.exception.DuplicateDataException;
import expiryeliminator.data.exception.NotFoundException;

/**
 * Represents the ingredient list and contains methods to add and remove ingredients.
 */
public class IngredientList {
    private final HashMap<String, Ingredient> ingredients;

    /**
     * Constructs IngredientList object with no ingredients.
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
        assert ingredient != null;
        if (contains(ingredient)) {
            throw new DuplicateDataException();
        }
        ingredients.put(ingredient.getName(), ingredient);
    }

    /**
     * Removes ingredient from the ingredient list.
     *
     * @param ingredientName Name of ingredient to be removed.
     * @return Ingredient that was removed.
     */
    public Ingredient remove(String ingredientName) throws NotFoundException {
        final Ingredient ingredient = ingredients.remove(ingredientName);
        if (ingredient == null) {
            throw new NotFoundException();
        }
        return ingredient;
    }

    public Collection<Ingredient> getIngredients() {
        return ingredients.values();
    }

    /**
     * Returns {@code true} if the ingredient list contains the specified ingredient.
     *
     * @param ingredient Ingredient whose presence in this list is to be tested.
     * @return {@code true} if the ingredient list contains the specified ingredient.
     */
    public boolean contains(Ingredient ingredient) {
        assert ingredient != null;
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

    /**
     * Returns a string representation of the whole list.
     *
     * @return returns a string representing all the ingredients and their specific quantities and expiration dates.
     */
    public String printWholeList() {
        StringBuilder wholeList = new StringBuilder();
        for (Ingredient ingredient : ingredients.values()) {
            wholeList.append(ingredient.toString()).append("\n");
        }
        return wholeList.toString();
    }

    /**
     * Looks for the ingredient that the user is looking for.
     *
     * @param ingredientDescription The ingredient name the user is searching for.
     * @return the ingredient object that the user is searching for.
     */
    public Ingredient find(String ingredientDescription) throws NotFoundException {
        final Ingredient ingredient = ingredients.get(ingredientDescription);
        if (ingredient == null) {
            throw new NotFoundException();
        }
        return ingredient;
    }

    /**
     * Looks for the ingredients that are expiring within the week.
     *
     * @return the string representing the list of expiring ingredients.
     */
    public String findExpiringIngredients() {
        LocalDate currentDate = LocalDate.now();
        LocalDate currentDatePlusAWeek = currentDate.plus(1, ChronoUnit.WEEKS);

        StringBuilder expiringIngredientsList = new StringBuilder();

        for (Ingredient ingredient : ingredients.values()) {
            if (ingredient.getExpiryDate().isAfter(currentDate)
                    && ingredient.getExpiryDate().isBefore(currentDatePlusAWeek)) {
                expiringIngredientsList.append(ingredient).append("\n");
            }
        }
        return expiringIngredientsList.toString();
    }

    /**
     * Looks for the ingredients that have expired.
     *
     * @return the string representing the list of expired ingredients.
     */
    public String findExpiredIngredients() {
        LocalDate currentDate = LocalDate.now();

        StringBuilder expiredIngredientsList = new StringBuilder();

        for (Ingredient ingredient : ingredients.values()) {
            if (ingredient.getExpiryDate().isBefore(currentDate)) {
                expiredIngredientsList.append(ingredient).append("\n");
            }
        }
        return expiredIngredientsList.toString();
    }
}
